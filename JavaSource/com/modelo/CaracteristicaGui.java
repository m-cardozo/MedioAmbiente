package com.modelo;

import javax.validation.constraints.NotNull;

public class CaracteristicaGui {

	@NotNull
	private Long caracteristicaId;

	@NotNull
	private String nombre;

	@NotNull	
	private String etiqueta;

	@NotNull
	private String tipoDato;

	@NotNull
	private FenomenoGui fenomenoGui;

	public CaracteristicaGui() {
		super();
		fenomenoGui=new FenomenoGui();
	}

	public CaracteristicaGui(@NotNull Long caracteristicaId, @NotNull String nombre, @NotNull String etiqueta,
			@NotNull String tipoDato, @NotNull FenomenoGui fenomenoGui) {
		super();
		this.caracteristicaId = caracteristicaId;
		this.nombre = nombre;
		this.etiqueta = etiqueta;
		this.tipoDato = tipoDato;
		this.fenomenoGui = fenomenoGui;
	}

	public Long getCaracteristicaId() {
		return caracteristicaId;
	}
	public void setCaracteristicaId(Long caracteristicaId) {
		this.caracteristicaId = caracteristicaId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public String getTipoDato() {
		return tipoDato;
	}
	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}
	public FenomenoGui getFenomenoGui() {
		return fenomenoGui;
	}
	public void setFenomenoGui(FenomenoGui fenomenoGui) {
		this.fenomenoGui = fenomenoGui;
	}
}