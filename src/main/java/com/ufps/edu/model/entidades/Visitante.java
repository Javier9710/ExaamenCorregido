package com.ufps.edu.model.entidades;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity(name = "visitante")
public class Visitante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "documento")
	private String documento;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "fechanacimiento")
	private LocalDate fechaNacimiento;
	
	@Column(name = "eps")
	private Long eps;
	
	@Column(name = "genero")
	private String genero;
	
	@Column(name = "empresa")
	private Long empresa;
	
	@Column(name = "telefono")
	private String telefono;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechNacimiento) {
		this.fechaNacimiento = fechNacimiento;
	}

	public Long getEps() {
		return eps;
	}

	public void setEps(Long eps) {
		this.eps = eps;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Long getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Long empresa) {
		this.empresa = empresa;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	

}
