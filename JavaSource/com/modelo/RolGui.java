package com.modelo;

import javax.validation.constraints.NotNull;

public class RolGui {

	@NotNull
	private Long rolId;

	@NotNull
	private String descripcion;

	public RolGui() {
		super();
	}

	public RolGui(@NotNull Long rolId, @NotNull String descripcion) {
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
}