package com.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CARACTERISTICAS", schema = "PROYECTO")
public class Caracteristica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long caracteristicaId;

	@Column(name = "NOMBRE", unique = true, length = 50)
	private String nombre;

	@Column(name = "ETIQUETA", length = 50)
	private String etiqueta;

	@Column(name = "TIPO_DATO", length = 50)
	private String tipoDato;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_FENOMENO")
	private Fenomeno fenomeno;

	public Caracteristica() {
		super();
	}

	public Caracteristica(Long caracteristicaId, String nombre, String etiqueta, String tipoDato,
			Fenomeno fenomeno) {
		super();
		this.caracteristicaId = caracteristicaId;
		this.nombre = nombre;
		this.etiqueta = etiqueta;
		this.tipoDato = tipoDato;
		this.fenomeno = fenomeno;
	}

	public Long getCaracteristicaId() {
		return caracteristicaId;
	}
	public void setCaracteristicaId(Long caracteristicaId) {
		this.caracteristicaId = caracteristicaId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public String getTipoDato() {
		return tipoDato;
	}
	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}
	public Fenomeno getFenomeno() {
		return fenomeno;
	}
	public void setFenomeno(Fenomeno fenomeno) {
		this.fenomeno = fenomeno;
	}
}