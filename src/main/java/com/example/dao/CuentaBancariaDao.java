package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.CuentaBancaria;

public interface CuentaBancariaDao extends JpaRepository<CuentaBancaria, String>{

    @Query("SELECT s FROM CuentaBancaria s WHERE (s.cliente = :codCliente) AND (s.isActivo = TRUE )")
	public List<CuentaBancaria> getCuentasByCliente(
			@Param("codCliente") String codCliente);
}
