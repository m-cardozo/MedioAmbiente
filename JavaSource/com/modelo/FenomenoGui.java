package com.modelo;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

public class FenomenoGui {

	@NotNull
	private Long fenomenoId;

	@NotNull
	private String nombre;

	@NotNull	
	private String descripcion;

	@NotNull
	private String telefonoEmergencia;

	private ArrayList<CaracteristicaGui> caracteristicas;

	public FenomenoGui() {
		super();
	}

	public FenomenoGui(@NotNull Long fenomenoId, @NotNull String nombre, @NotNull String descripcion,
			@NotNull String telefonoEmergencia, ArrayList<CaracteristicaGui> caracteristicas) {
		super();
		this.fenomenoId = fenomenoId;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.telefonoEmergencia = telefonoEmergencia;
		this.caracteristicas = caracteristicas;
	}

	public Long getFenomenoId() {
		return fenomenoId;
	}
	public void setFenomenoId(Long fenomenoId) {
		this.fenomenoId = fenomenoId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTelefonoEmergencia() {
		return telefonoEmergencia;
	}
	public void setTelefonoEmergencia(String telefonoEmergencia) {
		this.telefonoEmergencia = telefonoEmergencia;
	}
	public ArrayList<CaracteristicaGui> getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(ArrayList<CaracteristicaGui> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
}