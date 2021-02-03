package com.modelo;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

public class DepartamentoGui {

	@NotNull
	private Long departamentoId;

	@NotNull
	private String descripcion;

	@NotNull
	private ZonaGui zonaGui;

	private ArrayList<LocalidadGui> localidades;

	public DepartamentoGui() {
		super();
		zonaGui=new ZonaGui();
	}

	public DepartamentoGui(@NotNull Long departamentoId, @NotNull String descripcion, @NotNull ZonaGui zonaGui) {
		super();
		this.departamentoId = departamentoId;
		this.descripcion = descripcion;
		this.zonaGui = zonaGui;
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

	public ZonaGui getZonaGui() {
		return zonaGui;
	}

	public void setZonaGui(ZonaGui zonaGui) {
		this.zonaGui = zonaGui;
	}

	public ArrayList<LocalidadGui> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(ArrayList<LocalidadGui> localidades) {
		this.localidades = localidades;
	}
}