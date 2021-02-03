package com.manejadores;

import java.io.Serializable;
import javax.inject.Named;

import java.util.ArrayList;

import javax.inject.Inject;

import com.entidades.Departamento;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaDepartamentoBean;

import javax.enterprise.context.SessionScoped;

@Named("gestionDepartamentos")
@SessionScoped
public class GestionDepartamentosBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	PersistenciaDepartamentoBean persistenciaDepartamentoBean;
	
	private String criterioDescripcion;
	private ArrayList<Departamento> departamentosSeleccionados = new ArrayList<Departamento>();
	private Departamento departamentoSeleccionado;

	public GestionDepartamentosBean() {
		super();
	}

	public String seleccionarDepartamentos() {
		try {
			departamentosSeleccionados = new ArrayList<Departamento>(persistenciaDepartamentoBean.seleccionarDepartamentos(criterioDescripcion));
			
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}

		return "";
	}

	public String verDatosDepartamento() {
		return "DatosDepartamento";
	}

	public PersistenciaDepartamentoBean getPersistenciaDepartamentoBean() {
		return persistenciaDepartamentoBean;
	}
	public void setPersistenciaDepartamentoBean(PersistenciaDepartamentoBean persistenciaDepartamentoBean) {
		this.persistenciaDepartamentoBean= persistenciaDepartamentoBean;
	}
	public String getCriterioDescripcion() {
		return criterioDescripcion;
	}
	public void setCriterioDescripcion(String criterioDescripcion) {
		this.criterioDescripcion = criterioDescripcion;
	}
	public ArrayList<Departamento> getDepartamentosSeleccionados() {
		return departamentosSeleccionados;
	}
	public void setDepartamentosSeleccionados(ArrayList<Departamento> departamentosSeleccionados) {
		this.departamentosSeleccionados= departamentosSeleccionados;
	}
	public Departamento getDepartamentoSeleccionado() {
		return departamentoSeleccionado;
	}
	public void setDepartamentoSeleccionado(Departamento departamentoSeleccionado) {
		this.departamentoSeleccionado = departamentoSeleccionado;
	}

	public void preRenderViewListener() {
		seleccionarDepartamentos();
	}
}