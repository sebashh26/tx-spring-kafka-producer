package com.example.service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import com.example.model.ClienteDTO;
import com.example.model.CuentaBancariaDTO;
import com.example.model.MovimientoDTO;


public interface MainService {
	List<ClienteDTO> getClientes();
	ClienteDTO getClientewithMovimientos(String codigo);
	void saveCliente(ClienteDTO cliente) throws InterruptedException, ExecutionException, TimeoutException;
	void saveCuenta(List<CuentaBancariaDTO> ctas) throws InterruptedException, ExecutionException, TimeoutException;
	void saveMovimientos(List<MovimientoDTO> movimientos) throws InterruptedException, ExecutionException, TimeoutException;
}
