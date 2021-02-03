package com.manejadores;

import java.io.Serializable;
import javax.inject.Named;

import java.util.ArrayList;

import javax.inject.Inject;

import com.entidades.Caracteristica;
import com.entidades.Fenomeno;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaCaracteristicaBean;
import com.logicaNegocio.PersistenciaFenomenoBean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

@Named("gestionCaracteristicas")
@SessionScoped
public class GestionCaracteristicasBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	PersistenciaCaracteristicaBean persistenciaCaracteristicaBean;

	private String criterioNombre;
	private String criterioEtiqueta;
	private ArrayList<Caracteristica> caracteristicasSeleccionadas = new ArrayList<Caracteristica>();
	private Caracteristica caracteristicaSeleccionada;

	public GestionCaracteristicasBean() {
		super();
	}

	public String seleccionarCaracteristicas() {
		try {
			caracteristicasSeleccionadas = new ArrayList<Caracteristica>(persistenciaCaracteristicaBean.seleccionarCaracteristicas(criterioNombre, criterioEtiqueta));
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}

		return "";
	}

	public String verDatosCaracteristica() {
		return "DatosCaracteristica";
	}

	public PersistenciaCaracteristicaBean getPersistenciaCaracteristicaBean() {
		return persistenciaCaracteristicaBean;
	}
	public void setPersistenciaCaracteristicaBean(PersistenciaCaracteristicaBean persistenciaCaracteristicaBean) {
		this.persistenciaCaracteristicaBean = persistenciaCaracteristicaBean;
	}
	public String getCriterioNombre() {
		return criterioNombre;
	}
	public void setCriterioNombre(String criterioNombre) {
		this.criterioNombre= criterioNombre;
	}
	public String getCriterioEtiqueta() {
		return criterioEtiqueta;
	}
	public void setCriterioEtiqueta(String criterioEtiqueta) {
		this.criterioEtiqueta= criterioEtiqueta;
	}
	public ArrayList<Caracteristica> getCaracteristicasSeleccionadas() {
		return caracteristicasSeleccionadas;
	}
	public void setCaracteristicasSeleccionados(ArrayList<Caracteristica> caracteristicasSeleccionadas) {
		this.caracteristicasSeleccionadas= caracteristicasSeleccionadas;
	}
	public Caracteristica getCaracteristicaSeleccionada() {
		return caracteristicaSeleccionada;
	}
	public void setCaracteristicaSeleccionada(Caracteristica caracteristicaSeleccionada) {
		this.caracteristicaSeleccionada = caracteristicaSeleccionada;
	}

	public void preRenderViewListener() {
		seleccionarCaracteristicas();
	}
}