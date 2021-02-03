package com.manejadores;

import java.io.Serializable;
import javax.inject.Named;

import java.util.ArrayList;

import javax.inject.Inject;

import com.entidades.Rol;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaRolBean;

import javax.enterprise.context.SessionScoped;

@Named("gestionRoles")
@SessionScoped
public class GestionRolesBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	PersistenciaRolBean persistenciaRolBean;

	private String criterioDescripcion;	
	private ArrayList<Rol> rolesSeleccionados = new ArrayList<Rol>();
	private Rol rolSeleccionado;

	public GestionRolesBean() {
		super();
	}

	public String seleccionarRoles() {
		
		try {
			rolesSeleccionados = new ArrayList<Rol>(persistenciaRolBean.seleccionarRoles(criterioDescripcion));
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}

		return "";
	}

	public String verDatosRol() {
		return "DatosRol";
	}

	public PersistenciaRolBean getPersistenciaRolBean() {
		return persistenciaRolBean;
	}
	public void setPersistenciaRolBean(PersistenciaRolBean persistenciaRolBean) {
		this.persistenciaRolBean = persistenciaRolBean;
	}
	public String getCriterioDescripcion() {
		return criterioDescripcion;
	}
	public void setCriterioDescripcion(String criterioDescripcion) {
		this.criterioDescripcion = criterioDescripcion;
	}
	public ArrayList<Rol> getRolesSeleccionados() {
		return rolesSeleccionados;
	}
	public void setRolesSeleccionados(ArrayList<Rol> rolesSeleccionados) {
		this.rolesSeleccionados = rolesSeleccionados;
	}
	public Rol getRolSeleccionado() {
		return rolSeleccionado;
	}
	public void setRolSeleccionado(Rol rolSeleccionado) {
		this.rolSeleccionado = rolSeleccionado;
	}

	public void preRenderViewListener() {
		seleccionarRoles();
	}
}