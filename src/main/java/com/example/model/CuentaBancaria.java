package com.example.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cuentas")
public class CuentaBancaria implements Serializable{
    private static final long serialVersionUID = 2445247993956960711L;

    @Id
	@Column(name = "codigo", length = 4)
	private String codigo;

	@Column(name = "cliente", length = 8)
	private String cliente;

	@Column(name = "saldo", precision = 2)
	private double saldo;

	@Column(name = "activo")
	private boolean isActivo;
}
