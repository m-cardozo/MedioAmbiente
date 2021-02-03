package com.manejadores;

import java.io.Serializable;
import javax.inject.Named;

import java.util.ArrayList;

import javax.inject.Inject;
import com.entidades.Fenomeno;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaFenomenoBean;
import javax.enterprise.context.SessionScoped;

@Named("gestionFenomenos")
@SessionScoped
public class GestionFenomenosBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	PersistenciaFenomenoBean persistenciaFenomenoBean;

	private String criterioDescripcion;
	private ArrayList<Fenomeno> fenomenosSeleccionados = new ArrayList<Fenomeno>();
	private Fenomeno fenomenoSeleccionado;

	public GestionFenomenosBean() {
		super();
	}

	public String seleccionarFenomenos() {
		try {
			fenomenosSeleccionados = new ArrayList<Fenomeno>(persistenciaFenomenoBean.seleccionarFenomenos(criterioDescripcion));
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}

		return "";
	}

	public String verDatosFenomeno() {
		return "DatosFenomeno";
	}

	public PersistenciaFenomenoBean getPersistenciaFenomenoBean() {
		return persistenciaFenomenoBean;
	}
	public void setPersistenciaFenomenoBean(PersistenciaFenomenoBean persistenciaFenomenoBean) {
		this.persistenciaFenomenoBean = persistenciaFenomenoBean;
	}
	public String getCriterioDescripcion() {
		return criterioDescripcion;
	}
	public void setCriterioDescripcion(String criterioDescripcion) {
		this.criterioDescripcion = criterioDescripcion;
	}
	public ArrayList<Fenomeno> getFenomenosSeleccionados() {
		return fenomenosSeleccionados;
	}
	public void setFenomenosSeleccionados(ArrayList<Fenomeno> fenomenosSeleccionados) {
		this.fenomenosSeleccionados = fenomenosSeleccionados;
	}
	public Fenomeno getFenomenoSeleccionado() {
		return fenomenoSeleccionado;
	}
	public void setFenomenoSeleccionada(Fenomeno fenomenoSeleccionado) {
		this.fenomenoSeleccionado = fenomenoSeleccionado;
	}

	public void preRenderViewListener() {
		seleccionarFenomenos();
	}
}