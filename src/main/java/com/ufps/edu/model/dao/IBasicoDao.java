package com.ufps.edu.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufps.edu.model.entidades.Basico;

public interface IBasicoDao extends JpaRepository<Basico, Long>{

	Optional<Basico> findByDocumento(String documento);
}
