package com.ufps.edu.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufps.edu.model.entidades.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByUsuario(String usuario);
	
}
