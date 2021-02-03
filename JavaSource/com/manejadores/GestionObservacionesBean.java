package com.manejadores;

import java.io.Serializable;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.entidades.Observacion;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaObservacionBean;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

@Named("gestionObservaciones")
@SessionScoped
public class GestionObservacionesBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	PersistenciaObservacionBean persistenciaObservacionBean;

	private String criterioObservacion;
	private ArrayList<Observacion> observacionesSeleccionadas = new ArrayList<Observacion>();
	private Observacion observacionSeleccionado;

	public GestionObservacionesBean() {
		super();
	}

	public String seleccionarObservaciones() {
		try {
			observacionesSeleccionadas = new ArrayList<Observacion>(persistenciaObservacionBean.seleccionarObservaciones(criterioObservacion));
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}

		return "";
	}

	public String verDatosObservacion() {
		return "DatosObservacion";
	}

	public PersistenciaObservacionBean getPersistenciaObservacionBean() {
		return persistenciaObservacionBean;
	}
	public void setPersistenciaObservacionBean(PersistenciaObservacionBean persistenciaObservacionBean) {
		this.persistenciaObservacionBean = persistenciaObservacionBean;
	}
	public String getCriterioObservacion() {
		return criterioObservacion;
	}
	public void setCriterioObservacion(String criterioObservacion) {
		this.criterioObservacion = criterioObservacion;
	}
	public ArrayList<Observacion> getObservacionesSeleccionados() {
		return observacionesSeleccionadas;
	}
	public void setObservacionesSeleccionados(ArrayList<Observacion> observacionesSeleccionados) {
		this.observacionesSeleccionadas = observacionesSeleccionados;
	}
	public Observacion getObservacionSeleccionado() {
		return observacionSeleccionado;
	}
	public void setObservacionSeleccionado(Observacion observacionSeleccionado) {
		this.observacionSeleccionado = observacionSeleccionado;
	}

	public void preRenderViewListener() {
		seleccionarObservaciones();
	}

	public ArrayList<Observacion> getObservacionesSeleccionadas() {
		return observacionesSeleccionadas;
	}

	public void setObservacionesSeleccionadas(ArrayList<Observacion> observacionesSeleccionadas) {
		this.observacionesSeleccionadas = observacionesSeleccionadas;
	}
	private String result="";
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void eventoListener(AjaxBehaviorEvent event) {
		result = "Prueba";
	}
}