package com.modelo;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class ObservacionGui {

	@NotNull
	private Long observacionId;

	@NotNull	
	private String descripcion;

	@NotNull
	private Date fecha;

	private String latitud;

	private String longitud;

	private String altitud;

	@NotNull
	private String nivelCritico;

	@NotNull
	private FenomenoGui fenomenoGui;

	@NotNull
	private UsuarioGui usuarioGui;

	@NotNull
	private LocalidadGui localidadGui;
	
	private ArrayList<ObservacionImagenGui> imagenes;
	
	private ArrayList<ObservacionRevisionGui> revisiones;
	
	private ArrayList<ObservacionCaracteristicaGui> observacionCaracteristicas;

	public ObservacionGui() {
		super();
		descripcion="";
		fenomenoGui=new FenomenoGui();
	}

	public ObservacionGui(@NotNull Long observacionId, @NotNull String descripcion, @NotNull Date fecha, String latitud,
			String longitud, String altitud, @NotNull String nivelCritico, @NotNull FenomenoGui fenomenoGui,
			@NotNull UsuarioGui usuarioGui, @NotNull LocalidadGui localidadGui) {
		super();
		this.observacionId = observacionId;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.latitud = latitud;
		this.longitud = longitud;
		this.altitud = altitud;
		this.nivelCritico = nivelCritico;
		this.fenomenoGui = fenomenoGui;
		this.usuarioGui = usuarioGui;
		this.localidadGui = localidadGui;
	}

	public Long getObservacionId() {
		return observacionId;
	}
	public void setObservacionId(Long observacionId) {
		this.observacionId = observacionId;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public String getAltitud() {
		return altitud;
	}
	public void setAltitud(String altitud) {
		this.altitud = altitud;
	}
	public String getNivelCritico() {
		return nivelCritico;
	}
	public void setNivelCritico(String nivelCritico) {
		this.nivelCritico = nivelCritico;
	}
	public FenomenoGui getFenomenoGui() {
		return fenomenoGui;
	}
	public void setFenomenoGui(FenomenoGui fenomenoGui) {
		this.fenomenoGui = fenomenoGui;
	}
	public UsuarioGui getUsuarioGui() {
		return usuarioGui;
	}
	public void setUsuarioGui(UsuarioGui usuarioGui) {
		this.usuarioGui = usuarioGui;
	}
	public LocalidadGui getLocalidadGui() {
		return localidadGui;
	}
	public void setLocalidadGui(LocalidadGui localidadGui) {
		this.localidadGui = localidadGui;
	}
	public ArrayList<ObservacionImagenGui> getImagenes() {
		return imagenes;
	}
	public void setImagenes(ArrayList<ObservacionImagenGui> imagenes) {
		this.imagenes = imagenes;
	}
	public ArrayList<ObservacionRevisionGui> getRevisiones() {
		return revisiones;
	}
	public void setRevisiones(ArrayList<ObservacionRevisionGui> revisiones) {
		this.revisiones = revisiones;
	}
	public ArrayList<ObservacionCaracteristicaGui> getObservacionCaracteristicas() {
		return observacionCaracteristicas;
	}
	public void setObservacioncaracteristicas(ArrayList<ObservacionCaracteristicaGui> observacionCaracteristicas) {
		this.observacionCaracteristicas = observacionCaracteristicas;
	}
	
	
}