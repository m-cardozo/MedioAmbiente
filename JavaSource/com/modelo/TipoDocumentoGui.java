package com.modelo;

import javax.validation.constraints.NotNull;

public class TipoDocumentoGui {

	@NotNull
	private Long tipoDocumentoId;

	@NotNull
	private String descripcion;

	public TipoDocumentoGui() {
		super();
	}

	public TipoDocumentoGui(@NotNull Long tipoDocumentoId, @NotNull String descripcion) {
		super();
		this.tipoDocumentoId = tipoDocumentoId;
		this.descripcion = descripcion;
	}

	public Long getTipoDocumentoId() {
		return tipoDocumentoId;
	}
	public void setTipoDocumentoId(Long tipoDocumentoId) {
		this.tipoDocumentoId = tipoDocumentoId;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}