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
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 2445247993956960711L;

	private String codigo;
	private String nombre;
	private Integer edad;
	private String sexo;

	@Singular
	private final List<CuentaBancariaDTO> ctas = Collections.emptyList();
}
