package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Cliente;

public interface ClienteDao extends JpaRepository<Cliente, String>{
}
