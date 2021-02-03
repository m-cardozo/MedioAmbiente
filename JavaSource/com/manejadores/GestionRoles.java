package com.manejadores;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaRolBean;
import com.modelo.RolGui;
import com.excepciones.ExceptionsTools;

@Named(value="gestionRol")
@SessionScoped
public class GestionRoles implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	PersistenciaRolBean persistenciaRolBean;
	
	private RolGui rolGuiSeleccionado;

	private Long id;
	private String modalidad;	
	private boolean modoEdicion = false;


	public GestionRoles() {
		super();
	}

	@PostConstruct
	public void init() {
		rolGuiSeleccionado = new RolGui();
	}

	public String reset() {
		rolGuiSeleccionado = new RolGui();
		return "";
	}

	public PersistenciaRolBean getPersistenciaRolBean() {
		return persistenciaRolBean;
	}
	public void setPersistenciaRolBean(PersistenciaRolBean persistenciaRolBean) {
		this.persistenciaRolBean = persistenciaRolBean;
	}
	public RolGui getRolGuiSeleccionado() {
		return rolGuiSeleccionado;
	}
	public void setRolGuiSeleccionado(RolGui rolGuiSeleccionado) {
		this.rolGuiSeleccionado = rolGuiSeleccionado;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public boolean isModoEdicion() {
		return modoEdicion;
	}

	public void setModoEdicion(boolean modoEdicion) {
		this.modoEdicion = modoEdicion;
	}

	public void preRenderViewListener() {

		modoEdicion = false;

		if (id != null){
			rolGuiSeleccionado = persistenciaRolBean.buscarRolGui(id);
		} else {
			rolGuiSeleccionado = new RolGui();
		}

		if (modalidad.contentEquals("update")) {
			modoEdicion = true;
		}else if (modalidad.contentEquals("insert")) {
			modoEdicion = true;
		}else {
			modoEdicion = false;
			modalidad = "view";
		}
	}

	public String cambiarModalidadUpdate() throws CloneNotSupportedException {
		return "Roles?faces-redirect=true&includeViewParams=true";
	}

	public String salvarCambios() throws CloneNotSupportedException {

		String path = "";

		if (rolGuiSeleccionado.getRolId() == null) {

			RolGui rolGuiNuevo = null;

			try {

				rolGuiNuevo = (RolGui) persistenciaRolBean.agregarRolGui(rolGuiSeleccionado);
				path = "Roles?faces-redirect=true&includeViewParams=true";

			} catch (PersistenciaException e) {
				Throwable rootException=ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			this.id = null;

			rolGuiSeleccionado = null;

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado un nuevo Rol", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";

		} else if (modalidad.equals("update")) {

			try {
				persistenciaRolBean.modificarRolGui(rolGuiSeleccionado);
				path = "Roles?faces-redirect=true&includeViewParams=true";


			} catch (PersistenciaException e) {
				Throwable rootException = ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado el Rol", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";
		}

		return path;
	}

	public String altaRol() throws CloneNotSupportedException {

		String path = "";

		if (rolGuiSeleccionado.getRolId() == null) {

			RolGui rolGuiNuevo = null;

			try {
				rolGuiNuevo = (RolGui) persistenciaRolBean.agregarRolGui(rolGuiSeleccionado);
				path = "Roles?faces-redirect=true&includeViewParams=true";

			} catch (PersistenciaException e) {
				Throwable rootException=ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesContext.getCurrentInstance().addMessage(null, ExceptionsTools.NotificarError("Error", "No se pudo realizar la operacion.\n Por favor verifique los datos."));

				e.printStackTrace();
			}			

			this.modalidad = "view";
		}

		return path;
	}

	public String modificarRol() throws CloneNotSupportedException {

		String path = "";

		try {
			System.out.println("Rol: " + rolGuiSeleccionado.getRolId().toString() + " " + rolGuiSeleccionado.getDescripcion());
			persistenciaRolBean.modificarRolGui(rolGuiSeleccionado);
			path = "Roles?faces-redirect=true&includeViewParams=true";

		} catch (PersistenciaException e) {
			Throwable rootException=ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado el Rol", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		rolGuiSeleccionado = null;

		this.modalidad = "view";

		return path;
	}

	public String bajaRol() throws CloneNotSupportedException {

		String path = "";

		try {
			persistenciaRolBean.borrarRolGui(rolGuiSeleccionado);
			path = "Roles?faces-redirect=true&includeViewParams=true";


		} catch (PersistenciaException e) {
			Throwable rootException = ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado el Rol", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		this.modalidad = "view";

		return path;
	}
}