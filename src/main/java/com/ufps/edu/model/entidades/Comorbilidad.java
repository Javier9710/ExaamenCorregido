package com.ufps.edu.model.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cormobilidad")
public class Comorbilidad {

	@Id
	private Long id;
	
	@Column(name = "diabetes")
	private Boolean diabetes;
	
	@Column(name = "cardio")
	private Boolean cardio;
	
	@Column(name = "cerebro")
	private Boolean cerebro;
	
	@Column(name = "vih")
	private Boolean vih;
	
	@Column(name = "cancer")
	private Boolean cancer;
	
	@Column(name = "corticoides")
	private Boolean corticoides;
	
	@Column(name = "epoc")
	private Boolean epoc;
	
	@Column(name = "nutricion")
	private Boolean nutricion;
	
	@Column(name = "fumador")
	private Boolean fumador;
	
	@Column(name = "fechareg")
	private Date fechareg;

	public Long getId() {
		return id;
	}

	public Boolean getDiabetes() {
		return diabetes;
	}

	public Boolean getCardio() {
		return cardio;
	}

	public Boolean getCerebro() {
		return cerebro;
	}

	public Boolean getVih() {
		return vih;
	}

	public Boolean getCancer() {
		return cancer;
	}

	public Boolean getCorticoides() {
		return corticoides;
	}

	public Boolean getEpoc() {
		return epoc;
	}

	public Boolean getNutricion() {
		return nutricion;
	}

	public Boolean getFumador() {
		return fumador;
	}

	public Date getFechareg() {
		return fechareg;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDiabetes(Boolean diabetes) {
		this.diabetes = diabetes;
	}

	public void setCardio(Boolean cardio) {
		this.cardio = cardio;
	}

	public void setCerebro(Boolean cerebro) {
		this.cerebro = cerebro;
	}

	public void setVih(Boolean vih) {
		this.vih = vih;
	}

	public void setCancer(Boolean cancer) {
		this.cancer = cancer;
	}

	public void setCorticoides(Boolean corticoides) {
		this.corticoides = corticoides;
	}

	public void setEpoc(Boolean epoc) {
		this.epoc = epoc;
	}

	public void setNutricion(Boolean nutricion) {
		this.nutricion = nutricion;
	}

	public void setFumador(Boolean fumador) {
		this.fumador = fumador;
	}

	public void setFechareg(Date fechareg) {
		this.fechareg = fechareg;
	}
	
	
}
