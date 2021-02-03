package com.manejadores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaDepartamentoBean;
import com.logicaNegocio.PersistenciaFenomenoBean;
import com.logicaNegocio.PersistenciaZonaBean;
import com.modelo.DepartamentoGui;
import com.modelo.FenomenoGui;
import com.modelo.ZonaGui;
import com.entidades.Zona;
import com.excepciones.ExceptionsTools;

@Named(value="gestionFenomeno")
@SessionScoped
public class GestionFenomenos implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	PersistenciaFenomenoBean persistenciaFenomenoBean;

	private FenomenoGui fenomenoGuiSeleccionado;

	
	private Long id;
	private String modalidad;
	private boolean modoEdicion = false;

	public GestionFenomenos() {
		super();
		
	}

	@PostConstruct
	public void init() {
		
		
		fenomenoGuiSeleccionado=new FenomenoGui();
	}

	public String reset() {
		fenomenoGuiSeleccionado = new FenomenoGui();
		return "";
	}

	public PersistenciaFenomenoBean getPersistenciaFenomenoBean() {
		return persistenciaFenomenoBean;
	}
	public void setPersistenciaFenomenoBean(PersistenciaFenomenoBean persistenciaFenomenoBean) {
		this.persistenciaFenomenoBean= persistenciaFenomenoBean;
	}
	public FenomenoGui getFenomenoGuiSeleccionado() {
		return fenomenoGuiSeleccionado;
	}
	public void setFenomenoGuiSeleccionado(FenomenoGui fenomenoGuiSeleccionado) {
		this.fenomenoGuiSeleccionado = fenomenoGuiSeleccionado;
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
			fenomenoGuiSeleccionado = persistenciaFenomenoBean.buscarFenomenoGui(id);
		} else {
			fenomenoGuiSeleccionado = new FenomenoGui();
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
		return "Fenomenos?faces-redirect=true&includeViewParams=true";
	}

	public String salvarCambios() throws CloneNotSupportedException {

		String path = "";
		
		if (fenomenoGuiSeleccionado.getFenomenoId() == null) {

			FenomenoGui fenomenoGuiNuevo = null;

			try {

				fenomenoGuiNuevo = (FenomenoGui) persistenciaFenomenoBean.agregarFenomenoGui(fenomenoGuiSeleccionado);
				path = "Fenomenos?faces-redirect=true&includeViewParams=true";

			} catch (PersistenciaException e) {
				Throwable rootException=ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			this.id = null;

			fenomenoGuiSeleccionado = null;

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado un nuevo fenomeno.", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";

		} else if (modalidad.equals("update")) {

			try {
				persistenciaFenomenoBean.modificarFenomenoGui(fenomenoGuiSeleccionado);
				path = "Fenomenos?faces-redirect=true&includeViewParams=true";


			} catch (PersistenciaException e) {
				Throwable rootException = ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado el fenomeno.", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";
		}

		return path;
	}

	public String altaFenomeno() throws CloneNotSupportedException {

		String path = "";
		

		if (fenomenoGuiSeleccionado.getFenomenoId() == null) {

			FenomenoGui fenomenoGuiNuevo = null;

			try {
				fenomenoGuiNuevo = (FenomenoGui) persistenciaFenomenoBean.agregarFenomenoGui(fenomenoGuiSeleccionado);
				path = "Fenomenos?faces-redirect=true&includeViewParams=true";

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

	public String modificarFenomeno() throws CloneNotSupportedException {

		String path = "";

		try {
			
			persistenciaFenomenoBean.modificarFenomenoGui(fenomenoGuiSeleccionado);
			path = "Fenomenos?faces-redirect=true&includeViewParams=true";

		} catch (PersistenciaException e) {
			Throwable rootException=ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado el fenomeno", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		fenomenoGuiSeleccionado = null;

		this.modalidad = "view";

		return path;
	}

	public String bajaFenomeno() throws CloneNotSupportedException {

		String path = "";

		try {
			persistenciaFenomenoBean.borrarFenomenoGui(fenomenoGuiSeleccionado);
			path = "Fenomenos?faces-redirect=true&includeViewParams=true";


		} catch (PersistenciaException e) {
			Throwable rootException=ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado el fenomeno", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		this.modalidad = "view";

		return path;
	}

			
	
}