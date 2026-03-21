package com.example.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuentaBancariaDTO implements Serializable {

	private static final long serialVersionUID = 2445247993956960711L;

	private String codigo;
	private double saldo;
	private boolean isActivo;
	private String cliente;
	@Singular
	private final List<MovimientoDTO> movimientos = Collections.emptyList();
}
