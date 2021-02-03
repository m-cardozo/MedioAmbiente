package com.entidades;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

@Entity
@Table(name = "LOCALIDADES")
public class Localidad implements Serializable {	   

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long localidadId;

	@Column(name = "DESCRIPCION", unique = true, length = 50)
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "ID_DEPARTAMENTO")
	private Departamento departamento;

	public Localidad() {
		super();
	}

	public Localidad(Long localidadId, String descripcion, Departamento departamento) {
		super();
		this.localidadId = localidadId;
		this.descripcion = descripcion;
		this.departamento = departamento;
	}

	public Long getLocalidadId() {
		return localidadId;
	}
	public void setLocalidadId(Long localidadId) {
		this.localidadId = localidadId;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
}