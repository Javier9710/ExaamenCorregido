package com.ufps.edu.model.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "registro")
@Entity
public class Registro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "persona")
	private Long persona;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "tos")
	private Boolean tos;
	
	@Column(name = "malestar")
	private Boolean malestar;
	
	@Column(name = "fatiga")
	private Boolean fatiga;
	
	@Column(name = "nasal")
	private Boolean nasal;
	
	@Column(name = "garganta")
	private Boolean garganta;
	
	@Column(name = "dificultad")
	private Boolean dificultad;
	
	@Column(name = "temperatura")
	private Double temperatura;
	
	@Column(name = "fechareg")
	private Date fechareg;
	
	@Column(name = "covid")
	private Boolean covid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPersona() {
		return persona;
	}

	public void setPersona(Long persona) {
		this.persona = persona;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getTos() {
		return tos;
	}

	public void setTos(Boolean tos) {
		this.tos = tos;
	}

	public Boolean getMalestar() {
		return malestar;
	}

	public void setMalestar(Boolean malestar) {
		this.malestar = malestar;
	}

	public Boolean getFatiga() {
		return fatiga;
	}

	public void setFatiga(Boolean fatiga) {
		this.fatiga = fatiga;
	}

	public Boolean getNasal() {
		return nasal;
	}

	public void setNasal(Boolean nasal) {
		this.nasal = nasal;
	}

	public Boolean getGarganta() {
		return garganta;
	}

	public void setGarganta(Boolean garganta) {
		this.garganta = garganta;
	}

	public Boolean getDificultad() {
		return dificultad;
	}

	public void setDificultad(Boolean dificultad) {
		this.dificultad = dificultad;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public Date getFechareg() {
		return fechareg;
	}

	public void setFechareg(Date fechareg) {
		this.fechareg = fechareg;
	}

	public Boolean getCovid() {
		return covid;
	}

	public void setCovid(Boolean covid) {
		this.covid = covid;
	}
	
	
}
