package com.manejadores;

import java.io.Serializable;
import javax.inject.Named;

import java.util.ArrayList;

import javax.inject.Inject;

import com.entidades.Zona;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaZonaBean;

import javax.enterprise.context.SessionScoped;

@Named("gestionZonas")
@SessionScoped
public class GestionZonasBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	PersistenciaZonaBean persistenciaZonaBean;

	private String criterioDescripcion;
	private ArrayList<Zona> zonasSeleccionadas = new ArrayList<Zona>();
	private Zona zonaSeleccionada;

	public GestionZonasBean() {
		super();
	}

	public String seleccionarZonas() {
		try {
			zonasSeleccionadas = new ArrayList<Zona>(persistenciaZonaBean.seleccionarZonas(criterioDescripcion));
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}

		return "";
	}

	public String verDatosZona() {
		return "DatosZona";
	}

	public PersistenciaZonaBean getPersistenciaZonaBean() {
		return persistenciaZonaBean;
	}
	public void setPersistenciaZonaBean(PersistenciaZonaBean persistenciaZonaBean) {
		this.persistenciaZonaBean = persistenciaZonaBean;
	}
	public String getCriterioDescripcion() {
		return criterioDescripcion;
	}
	public void setCriterioDescripcion(String criterioDescripcion) {
		this.criterioDescripcion = criterioDescripcion;
	}
	public ArrayList<Zona> getZonasSeleccionadas() {
		return zonasSeleccionadas;
	}
	public void setZonasSeleccionadas(ArrayList<Zona> zonasSeleccionadas) {
		this.zonasSeleccionadas = zonasSeleccionadas;
	}
	public Zona getZonaSeleccionada() {
		return zonaSeleccionada;
	}
	public void setZonaSeleccionada(Zona zonaSeleccionada) {
		this.zonaSeleccionada = zonaSeleccionada;
	}

	public void preRenderViewListener() {
		seleccionarZonas();
	}
}