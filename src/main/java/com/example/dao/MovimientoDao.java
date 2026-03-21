package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.Movimiento;

public interface MovimientoDao extends JpaRepository<Movimiento, String>{

    @Query("SELECT s FROM Movimiento s WHERE (s.cuenta = :codCuenta)")
	public List<Movimiento> getMovimientosByCuenta(
			@Param("codCuenta") String codCuenta);
}
