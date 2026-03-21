package com.example.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDTO implements Serializable {

	private static final long serialVersionUID = 2445247993956960711L;

	private String codigo;
    private String cuenta;
    private double monto;
    private boolean isProcesado;
    private String fecha;
}
