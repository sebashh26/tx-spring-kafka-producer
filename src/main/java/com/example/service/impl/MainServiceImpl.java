package com.example.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ClienteDao;
import com.example.dao.CuentaBancariaDao;
import com.example.dao.MovimientoDao;
import com.example.model.Cliente;
import com.example.model.ClienteDTO;
import com.example.model.CuentaBancariaDTO;
import com.example.model.MovimientoDTO;
import com.example.service.MainService;


@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private ClienteDao clienteDao;

	@Autowired
	private CuentaBancariaDao cuentaBancariaDao;

	@Autowired
	private MovimientoDao movimientoDao;

	// @Autowired
    // private KafkaTemplate<String, List<CuentaBancariaDTO>> kafkaTemplateCuentaBancaria;

	// @Autowired
    // private KafkaTemplate<String, List<MovimientoDTO>> kafkaTemplateMovimientos;

	@Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

	@Value("${app.topic.cuentas}")
    private String topicCuentas;

	@Value("${app.topic.movimientos}")
    private String topicMovimientos;

	@Override
	public List<ClienteDTO> getClientes() {
		List<ClienteDTO> lta = Collections.emptyList();
		this.clienteDao.findAll().forEach(c -> {
			lta.add(ClienteDTO.builder()
				.codigo(c.getCodigo())
				.nombre(c.getNombre())
				.sexo(c.getSexo())
				.edad(c.getEdad())
				.build());
		});
		return lta;
	}

	@Override
	public ClienteDTO getClientewithMovimientos(String codigo) {
		Cliente clienteBD = this.clienteDao.findById(codigo).get();
		ClienteDTO cliente = ClienteDTO.builder()
								.codigo(clienteBD.getCodigo())
								.edad(clienteBD.getEdad())
								.nombre(clienteBD.getNombre())
								.sexo(clienteBD.getSexo())
								.build();

		List<CuentaBancariaDTO> ctas = Collections.emptyList();
		this.cuentaBancariaDao.getCuentasByCliente(codigo).forEach(c -> {
			CuentaBancariaDTO cta = CuentaBancariaDTO.builder()
				.cliente(codigo)
				.codigo(c.getCodigo())
				.isActivo(c.isActivo())
				.saldo(c.getSaldo())
				.build();
			
			this.movimientoDao.getMovimientosByCuenta(c.getCodigo()).forEach(m -> {
				cta.getMovimientos().add(MovimientoDTO.builder()
					.codigo(m.getCodigo())
					.cuenta(c.getCodigo())
					.fecha(m.getFecha())
					.isProcesado(m.isProcesado())
					.monto(m.getMonto())
					.build());
			});

			ctas.add(cta);
		});

		cliente.getCtas().addAll(ctas);
		return cliente;
	}

	@Override
	@Transactional("transactionManager")
	public void saveCliente(ClienteDTO cliente) throws InterruptedException, ExecutionException, TimeoutException {
		Cliente c = Cliente.builder()
						.codigo(cliente.getCodigo())
						.nombre(cliente.getNombre())
						.edad(cliente.getEdad())
						.sexo(cliente.getSexo())
						.build();
		this.clienteDao.save(c);
		cliente.getCtas().forEach(cta -> {
			cta.setCliente(cliente.getCodigo());
		});
		sendCuentaToTopic(cliente.getCtas());
	}

	@Override
	@Transactional("kafkaTransactionManager")
	public void saveCuenta(List<CuentaBancariaDTO> ctas) throws InterruptedException, ExecutionException, TimeoutException {
		sendCuentaToTopic(ctas);
	}

	@Override
	@Transactional("kafkaTransactionManager")
	public void saveMovimientos(List<MovimientoDTO> movimientos) throws InterruptedException, ExecutionException, TimeoutException {
		sendMovimientosToTopic(movimientos);
	}

	@Transactional("kafkaTransactionManager")
	private void sendCuentaToTopic(List<CuentaBancariaDTO> ctas) throws InterruptedException, ExecutionException, TimeoutException {
		if(!ctas.isEmpty()){
			kafkaTemplate.send(topicCuentas, null, ctas).get(1, TimeUnit.SECONDS);
		}
	}

	@Transactional("kafkaTransactionManager")
	private void sendMovimientosToTopic(List<MovimientoDTO> movimientos) throws InterruptedException, ExecutionException, TimeoutException{
		if(!movimientos.isEmpty())
		kafkaTemplate.send(topicMovimientos, null, movimientos).get(1, TimeUnit.SECONDS);
	}

}
