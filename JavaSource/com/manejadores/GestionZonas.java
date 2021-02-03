package com.manejadores;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaZonaBean;
import com.modelo.ZonaGui;
import com.excepciones.ExceptionsTools;

@Named(value="gestionZona")
@SessionScoped
public class GestionZonas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	PersistenciaZonaBean persistenciaZonaBean;

	private ZonaGui zonaGuiSeleccionado;

	private Long id;
	private String modalidad;	
	private boolean modoEdicion = false;

	public GestionZonas() {
		super();
	}

	@PostConstruct
	public void init() {
		zonaGuiSeleccionado = new ZonaGui();
	}

	public String reset() {
		zonaGuiSeleccionado = new ZonaGui();
		return "";
	}

	public PersistenciaZonaBean getPersistenciaZonaBean() {
		return persistenciaZonaBean;
	}
	public void setPersistenciaZonaBean(PersistenciaZonaBean persistenciaZonaBean) {
		this.persistenciaZonaBean = persistenciaZonaBean;
	}
	public ZonaGui getZonaGuiSeleccionado() {
		return zonaGuiSeleccionado;
	}
	public void setZonaGuiSeleccionado(ZonaGui zonaGuiSeleccionado) {
		this.zonaGuiSeleccionado = zonaGuiSeleccionado;
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
			zonaGuiSeleccionado = persistenciaZonaBean.buscarZonaGui(id);
		} else {
			zonaGuiSeleccionado = new ZonaGui();
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
		return "Zonas?faces-redirect=true&includeViewParams=true";
	}

	public String salvarCambios() throws CloneNotSupportedException {

		String path = "";

		if (zonaGuiSeleccionado.getZonaId() == null) {

			ZonaGui rolGuiNuevo = null;
			
			try {
				rolGuiNuevo = (ZonaGui) persistenciaZonaBean.agregarZonaGui(zonaGuiSeleccionado);
				path = "Zonas?faces-redirect=true&includeViewParams=true";

			} catch (PersistenciaException e) {
				Throwable rootException=ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			this.id = null;

			zonaGuiSeleccionado = null;

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado un nuevo Zona", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";

		} else if (modalidad.equals("update")) {

			try {
				persistenciaZonaBean.modificarZonaGui(zonaGuiSeleccionado);
				path = "Zonas?faces-redirect=true&includeViewParams=true";


			} catch (PersistenciaException e) {
				Throwable rootException = ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado el Zona", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";
		}

		return path;
	}

	public String altaZona() throws CloneNotSupportedException {

		String path = "";

		if (zonaGuiSeleccionado.getZonaId() == null) {

			ZonaGui zonaGuiNuevo = null;

			try {
				zonaGuiNuevo = (ZonaGui) persistenciaZonaBean.agregarZonaGui(zonaGuiSeleccionado);
				path = "Zonas?faces-redirect=true&includeViewParams=true";

			} catch (PersistenciaException e) {
				Throwable rootException = ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesContext.getCurrentInstance().addMessage(null, ExceptionsTools.NotificarError("Error", "No se pudo realizar la operacion. Por favor verifique los datos."));

				e.printStackTrace();
			}			

			this.modalidad = "view";
		}

		return path;
	}

	public String modificarZona() throws CloneNotSupportedException {

		String path = "";

		try {
			System.out.println("Zona: " + zonaGuiSeleccionado.getZonaId().toString() + " " + zonaGuiSeleccionado.getDescripcion());
			persistenciaZonaBean.modificarZonaGui(zonaGuiSeleccionado);
			path = "Zonas?faces-redirect=true&includeViewParams=true";

		} catch (PersistenciaException e) {
			Throwable rootException=ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado el Zona", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		zonaGuiSeleccionado = null;

		this.modalidad = "view";

		return path;
	}

	public String bajaZona() throws CloneNotSupportedException {

		String path = "";

		try {
			persistenciaZonaBean.borrarZonaGui(zonaGuiSeleccionado);
			path = "Zonas?faces-redirect=true&includeViewParams=true";


		} catch (PersistenciaException e) {
			Throwable rootException=ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado el Zona", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		this.modalidad = "view";

		return path;
	}
}