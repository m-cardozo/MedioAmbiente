package com.manejadores;

import java.io.Serializable;
import javax.inject.Named;

import java.util.ArrayList;

import javax.inject.Inject;

import com.entidades.Usuario;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaUsuarioBean;

import javax.enterprise.context.SessionScoped;

@Named("gestionUsuarios")
@SessionScoped
public class GestionUsuariosBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	PersistenciaUsuarioBean persistenciaUsuarioBean;

	private String criterioUsuario;
	private String criterioDocumento;
	private ArrayList<Usuario> usuariosSeleccionados = new ArrayList<Usuario>();
	private Usuario usuarioSeleccionado;

	public GestionUsuariosBean() {
		super();
	}

	public String seleccionarUsuarios() {
		try {
			usuariosSeleccionados = new ArrayList<Usuario>(persistenciaUsuarioBean.seleccionarUsuarios(criterioUsuario, criterioDocumento));
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}

		return "";
	}

	public String verDatosUsuario() {
		return "DatosUsuario";
	}

	public PersistenciaUsuarioBean getPersistenciaUsuarioBean() {
		return persistenciaUsuarioBean;
	}
	public void setPersistenciaUsuarioBean(PersistenciaUsuarioBean persistenciaUsuarioBean) {
		this.persistenciaUsuarioBean = persistenciaUsuarioBean;
	}
	public String getCriterioUsuario() {
		return criterioUsuario;
	}
	public void setCriterioUsuario(String criterioUsuario) {
		this.criterioUsuario = criterioUsuario;
	}
	public String getCriterioDocumento() {
		return criterioDocumento;
	}
	public void setCriterioDocumento(String criterioDocumento) {
		this.criterioDocumento = criterioDocumento;
	}
	public ArrayList<Usuario> getUsuariosSeleccionados() {
		return usuariosSeleccionados;
	}
	public void setUsuariosSeleccionados(ArrayList<Usuario> usuariosSeleccionados) {
		this.usuariosSeleccionados = usuariosSeleccionados;
	}
	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}
	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public void preRenderViewListener() {
		seleccionarUsuarios();
	}
	
}