package com.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "OBSERVACIONES_CARACTERISTICAS")
public class ObservacionCaracteristica implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ObservacionCaracteristicaPK id = new ObservacionCaracteristicaPK();

	@MapsId("caracteristicaId")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CARACTERISTICA")
	Caracteristica caracteristica;

	@MapsId("observacionId")
	@ManyToOne
	@JoinColumn(name = "ID_OBSERVACION")
	Observacion observacion;

	@Column(name = "VALOR_TEXTO")
	private String valorTexto;

	@Column(name = "VALOR_NUMERICO")
	private Long valorNumerico;

	@Column(name = "VALOR_FECHA")
	private Date valorFecha;

	public ObservacionCaracteristica() {
		super();
	}

	public ObservacionCaracteristica(Caracteristica caracteristica,	Observacion observacion, String valorTexto,
			Long valorNumerico, Date valorFecha) {
		super();
		this.caracteristica = caracteristica;
		this.observacion = observacion;
		this.valorTexto = valorTexto;
		this.valorNumerico = valorNumerico;
		this.valorFecha = valorFecha;
	}

	public ObservacionCaracteristicaPK getId() {
		return id;
	}
	public void setId(ObservacionCaracteristicaPK id) {
		this.id = id;
	}
	public Caracteristica getCaracteristica() {
		return caracteristica;
	}
	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}
	public Observacion getObservacion() {
		return observacion;
	}
	public void setObservacion(Observacion observacion) {
		this.observacion = observacion;
	}
	public String getValorTexto() {
		return valorTexto;
	}
	public void setValorTexto(String valorTexto) {
		this.valorTexto = valorTexto;
	}
	public Long getValorNumerico() {
		return valorNumerico;
	}
	public void setValorNumerico(Long valorNumerico) {
		this.valorNumerico = valorNumerico;
	}
	public Date getValorFecha() {
		return valorFecha;
	}
	public void setValorFecha(Date valorFecha) {
		this.valorFecha = valorFecha;
	}
}