package com.ufps.edu.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufps.edu.model.entidades.Registro;

public interface IRegistroDao extends JpaRepository<Registro, Long>{
	
	public Optional<Registro> findByPersona(Long persona);
	

}
