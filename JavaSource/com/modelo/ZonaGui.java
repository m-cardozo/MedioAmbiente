package com.modelo;

import javax.validation.constraints.NotNull;

public class ZonaGui {

	@NotNull
	private Long zonaId;

	@NotNull
	private String descripcion;

	public ZonaGui() {
		super();
	}

	public ZonaGui(@NotNull Long zonaId, @NotNull String descripcion) {
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
}