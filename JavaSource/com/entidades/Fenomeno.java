package com.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FENOMENOS")
public class Fenomeno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long fenomenoId;

	@Column(name = "NOMBRE", unique = true, length = 50)
	private String nombre;

	@Column(name = "DESCRIPCION", length = 200)
	private String descripcion;

	@Column(name = "TELEFONO_EMERGENCIA", length = 20)
	private String telefonoEmergencia;
	
	@OneToMany(mappedBy = "fenomeno")
	private List<Observacion> observaciones;

	@OneToMany(mappedBy = "fenomeno")
	private List<Caracteristica> caracteristicas;

	public Fenomeno() {
		super();
	}

	public Fenomeno(Long fenomenoId, String nombre, String descripcion, String telefonoEmergencia) {
		super();
		this.fenomenoId = fenomenoId;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.telefonoEmergencia = telefonoEmergencia;
	}

	public Long getFenomenoId() {
		return fenomenoId;
	}
	public void setFenomenoId(Long fenomenoId) {
		this.fenomenoId = fenomenoId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTelefonoEmergencia() {
		return telefonoEmergencia;
	}
	public void setTelefonoEmergencia(String telefonoEmergencia) {
		this.telefonoEmergencia = telefonoEmergencia;
	}
	public List<Observacion> getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(List<Observacion> observaciones) {
		this.observaciones = observaciones;
	}
	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
}