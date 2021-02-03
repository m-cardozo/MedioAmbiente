package com.entidades;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
public class Rol implements Serializable {	   

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long rolId;

	@Column(name = "DESCRIPCION", unique = true, length = 20)
	private String descripcion;

	@OneToMany(mappedBy = "rol")
	private List<Usuario> usuarios;

	public Rol() {
		super();
	}

	public Rol(Long rolId, String descripcion) {
		super();
		this.rolId = rolId;
		this.descripcion = descripcion;
	}

	public Long getRolId() {
		return rolId;
	}
	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}