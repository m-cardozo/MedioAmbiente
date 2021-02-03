package com.modelo;


import javax.validation.constraints.NotNull;

public class LocalidadGui {

	@NotNull
	private Long localidadId;

	@NotNull
	private String descripcion;
	
	@NotNull
	private DepartamentoGui departamentoGui;

	public LocalidadGui() {
		super();
		departamentoGui=new DepartamentoGui();
	}

	public LocalidadGui(@NotNull Long localidadId, @NotNull String descripcion, @NotNull DepartamentoGui departamentoGui) {
		super();
		this.localidadId = localidadId;
		this.descripcion = descripcion;
		this.departamentoGui = departamentoGui;
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
	public DepartamentoGui getDepartamentoGui() {
		return departamentoGui;
	}
	public void setDepartamentoGui(DepartamentoGui departamentoGui) {
		this.departamentoGui = departamentoGui;
	}
}