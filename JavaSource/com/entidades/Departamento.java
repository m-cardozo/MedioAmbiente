package com.entidades;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento implements Serializable {	   

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long departamentoId;

	@Column(name = "DESCRIPCION", unique = true, length = 50)
	private String descripcion;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ZONA")
	private Zona zona;

	@OneToMany(mappedBy = "departamento")
	private List<Localidad> localidades;

	public Departamento() {
		super();
	}

	public Departamento(Long departamentoId, String descripcion, Zona zona) {
		super();
		this.departamentoId = departamentoId;
		this.descripcion = descripcion;
		this.zona = zona;
	}

	public Long getDepartamentoId() {
		return departamentoId;
	}
	public void setDepartamentoId(Long departamentoId) {
		this.departamentoId = departamentoId;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	public List<Localidad> getLocalidades() {
		return localidades;
	}
	public void setLocalidades(List<Localidad> localidades) {
		this.localidades = localidades;
	}
}