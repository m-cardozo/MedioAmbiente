package com.entidades;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ZONAS")
public class Zona implements Serializable {	   

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long zonaId;

	@Column(name = "DESCRIPCION", unique = true, length = 50)
	private String descripcion;

	@OneToMany(mappedBy = "zona", fetch = FetchType.EAGER)
	private List<Departamento> departamentos;

	public Zona() {
		super();
	}

	public Zona(Long zonaId, String descripcion) {
		super();
		this.zonaId = zonaId;
		this.descripcion = descripcion;
	}

	public Long getZonaId() {
		return zonaId;
	}
	public void setZonaId(Long zonaId) {
		this.zonaId = zonaId;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Departamento> getDepartamentos() {
		return departamentos;
	}
	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}
}