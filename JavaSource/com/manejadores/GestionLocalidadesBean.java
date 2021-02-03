package com.manejadores;

import java.io.Serializable;
import javax.inject.Named;

import java.util.ArrayList;

import javax.inject.Inject;

import com.entidades.Localidad;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaLocalidadBean;

import javax.enterprise.context.SessionScoped;

@Named("gestionLocalidades")
@SessionScoped
public class GestionLocalidadesBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	PersistenciaLocalidadBean persistenciaLocalidadBean;
	
	private String criterioDescripcion;
	private ArrayList<Localidad> localidadesSeleccionados = new ArrayList<Localidad>();
	private Localidad localidadSeleccionado;

	public GestionLocalidadesBean() {
		super();
	}

	public String seleccionarLocalidades() {
		try {
			localidadesSeleccionados = new ArrayList<Localidad>(persistenciaLocalidadBean.seleccionarLocalidades(criterioDescripcion));			
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}

		return "";
	}

	public String verDatosLocalidad() {
		return "DatosLocalidad";
	}

	public PersistenciaLocalidadBean getPersistenciaLocalidadBean() {
		return persistenciaLocalidadBean;
	}
	public void setPersistenciaLocalidadBean(PersistenciaLocalidadBean persistenciaLocalidadBean) {
		this.persistenciaLocalidadBean= persistenciaLocalidadBean;
	}
	public String getCriterioDescripcion() {
		return criterioDescripcion;
	}
	public void setCriterioDescripcion(String criterioDescripcion) {
		this.criterioDescripcion = criterioDescripcion;
	}
	public ArrayList<Localidad> getLocalidadesSeleccionados() {
		return localidadesSeleccionados;
	}
	public void setLocalidadesSeleccionados(ArrayList<Localidad> localidadesSeleccionados) {
		this.localidadesSeleccionados= localidadesSeleccionados;
	}
	public Localidad getLocalidadSeleccionado() {
		return localidadSeleccionado;
	}
	public void setLocalidadSeleccionado(Localidad localidadSeleccionado) {
		this.localidadSeleccionado = localidadSeleccionado;
	}

	public void preRenderViewListener() {
		seleccionarLocalidades();
	}
}