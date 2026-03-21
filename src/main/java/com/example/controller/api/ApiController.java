package com.example.controller.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.ClienteDTO;
import com.example.model.CuentaBancariaDTO;
import com.example.model.MovimientoDTO;
import com.example.service.MainService;

@RestController
public class ApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	private MainService service;
	
	@GetMapping(value="/api/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClienteDTO>> getClientes(){
		try {
			ResponseEntity<List<ClienteDTO>> rpta = new ResponseEntity<List<ClienteDTO>>(service.getClientes(), HttpStatus.OK);
			logger.info("get clientes");
			return rpta;
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value="/api/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveCliente(
			@RequestBody ClienteDTO cliente){
		try {
			service.saveCliente(cliente);
			logger.info("se envío a procesar correctamente");
			return new ResponseEntity<String>("", HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value="/api/cuentas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveCuenta(
			@RequestBody List<CuentaBancariaDTO> ctas){
		try {
			service.saveCuenta(ctas);
			logger.info("se envío a procesar correctamente");
			return new ResponseEntity<String>("", HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value="/api/movimientos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveMovimientos(
			@RequestBody List<MovimientoDTO> mtos){
		try {
			service.saveMovimientos(mtos);
			logger.info("se envío a procesar correctamente");
			return new ResponseEntity<String>("", HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
