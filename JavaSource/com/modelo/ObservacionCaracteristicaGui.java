package com.modelo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.entidades.ObservacionCaracteristicaPK;

public class ObservacionCaracteristicaGui {
	
	@NotNull
	private ObservacionCaracteristicaPK id;

	@NotNull
	private ObservacionGui observacionGui;

	@NotNull
	private CaracteristicaGui caracteristicaGui;

	@NotNull
	private Long valorNumerico;

	@NotNull
	private String valorTexto;

	@NotNull
	private Date valorFecha;

	public ObservacionCaracteristicaGui() {
		super();
		id=new ObservacionCaracteristicaPK();
		
		observacionGui=new ObservacionGui();
		caracteristicaGui=new CaracteristicaGui();
		
		id.setCaracteristicaId(caracteristicaGui.getCaracteristicaId());
		id.setObservacionId(observacionGui.getObservacionId());
		
		
	}

	
	public ObservacionCaracteristicaGui(@NotNull ObservacionCaracteristicaPK id, @NotNull ObservacionGui observacionGui,
			@NotNull CaracteristicaGui caracteristicaGui, @NotNull Long valorNumerico, @NotNull String valorTexto,
			@NotNull Date valorFecha) {
		super();
		this.id = id;
		this.observacionGui = observacionGui;
		this.caracteristicaGui = caracteristicaGui;
		this.valorNumerico = valorNumerico;
		this.valorTexto = valorTexto;
		this.valorFecha = valorFecha;
	}

	public ObservacionCaracteristicaPK getId() {
		return id;
	}
	public void setId(ObservacionCaracteristicaPK id) {
		this.id = id;
	}
	public ObservacionGui getObservacionGui() {
		return observacionGui;
	}
	public void setObservacionGui(ObservacionGui observacionGui) {
		this.observacionGui = observacionGui;
	}
	public CaracteristicaGui getCaracteristicaGui() {
		return caracteristicaGui;
	}
	public void setCaracteristicaGui(CaracteristicaGui caracteristicaGui) {
		this.caracteristicaGui = caracteristicaGui;
	}
	public Long getValorNumerico() {
		return valorNumerico;
	}
	public void setValorNumerico(Long valorNumerico) {
		this.valorNumerico = valorNumerico;
	}
	public String getValorTexto() {
		return valorTexto;
	}
	public void setValorTexto(String valorTexto) {
		this.valorTexto = valorTexto;
	}
	public Date getValorFecha() {
		return valorFecha;
	}
	public void setValorFecha(Date valorFecha) {
		this.valorFecha = valorFecha;
	}
}