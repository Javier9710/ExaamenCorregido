package com.ufps.edu.negocio;


public class JsonResultado {

	private Boolean error;
	
	private String documento;
	
	private String nombre;
	
	private Long tipo;
	
	private String tipodescripcion;

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
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

	public Long getTipo() {
		return tipo;
	}

	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}

	public String getTipoDescripcion() {
		return tipodescripcion;
	}

	public void setTipoDescripcion(String tipoDescripcion) {
		this.tipodescripcion = tipoDescripcion;
	}
	
	
}
