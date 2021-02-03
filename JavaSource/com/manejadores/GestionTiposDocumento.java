package com.manejadores;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaTipoDocumentoBean;
import com.modelo.TipoDocumentoGui;
import com.excepciones.ExceptionsTools;

@Named(value="gestionTipoDocumento")
@SessionScoped
public class GestionTiposDocumento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	PersistenciaTipoDocumentoBean persistenciaTipoDocumentoBean;
	private TipoDocumentoGui tipoDocumentoGuiSeleccionado;

	private Long id;
	private String modalidad;	
	private boolean modoEdicion = false;

	public GestionTiposDocumento() {
		super();
	}

	@PostConstruct
	public void init() {
		tipoDocumentoGuiSeleccionado = new TipoDocumentoGui();
	}

	public String reset() {
		tipoDocumentoGuiSeleccionado = new TipoDocumentoGui();
		return "";
	}

	public PersistenciaTipoDocumentoBean getPersistenciaTipoDocumentoBean() {
		return persistenciaTipoDocumentoBean;
	}
	public void setPersistenciaTipoDocumentoBean(PersistenciaTipoDocumentoBean persistenciaTipoDocumentoBean) {
		this.persistenciaTipoDocumentoBean = persistenciaTipoDocumentoBean;
	}
	public TipoDocumentoGui getTipoDocumentoGuiSeleccionado() {
		return tipoDocumentoGuiSeleccionado;
	}
	public void setTipoDocumentoGuiSeleccionado(TipoDocumentoGui tipoDocumentoGuiSeleccionado) {
		this.tipoDocumentoGuiSeleccionado = tipoDocumentoGuiSeleccionado;
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
			tipoDocumentoGuiSeleccionado = persistenciaTipoDocumentoBean.buscarTipoDocumentoGui(id);
		} else {
			tipoDocumentoGuiSeleccionado = new TipoDocumentoGui();
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
		return "TiposDocumento?faces-redirect=true&includeViewParams=true";
	}

	public String salvarCambios() throws CloneNotSupportedException {

		String path = "";

		if (tipoDocumentoGuiSeleccionado.getTipoDocumentoId() == null) {

			TipoDocumentoGui tipoDocumentoGuiNuevo = null;

			try {

				tipoDocumentoGuiNuevo = (TipoDocumentoGui) persistenciaTipoDocumentoBean.agregarTipoDocumentoGui(tipoDocumentoGuiSeleccionado);
				path = "TiposDocumento?faces-redirect=true&includeViewParams=true";

			} catch (PersistenciaException e) {
				Throwable rootException=ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			this.id = null;

			tipoDocumentoGuiSeleccionado = null;

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado un nuevo Tipo Documento", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";

		} else if (modalidad.equals("update")) {

			try {
				persistenciaTipoDocumentoBean.modificarTipoDocumentoGui(tipoDocumentoGuiSeleccionado);
				path = "TiposDocumento?faces-redirect=true&includeViewParams=true";


			} catch (PersistenciaException e) {
				Throwable rootException = ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado el Tipo Documento", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";
		}

		return path;
	}

	public String altaTipoDocumento() throws CloneNotSupportedException {

		String path = "";

		if (tipoDocumentoGuiSeleccionado.getTipoDocumentoId() == null) {

			TipoDocumentoGui tipoDocumentoGuiNuevo = null;

			try {
				tipoDocumentoGuiNuevo = (TipoDocumentoGui) persistenciaTipoDocumentoBean.agregarTipoDocumentoGui(tipoDocumentoGuiSeleccionado);
				path = "TiposDocumento?faces-redirect=true&includeViewParams=true";

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

	public String modificarTipoDocumento() throws CloneNotSupportedException {

		String path = "";

		try {
			System.out.println("TipoDocumento: " + tipoDocumentoGuiSeleccionado.getTipoDocumentoId().toString() + " " + tipoDocumentoGuiSeleccionado.getDescripcion());
			persistenciaTipoDocumentoBean.modificarTipoDocumentoGui(tipoDocumentoGuiSeleccionado);
			path = "TiposDocumento?faces-redirect=true&includeViewParams=true";

		} catch (PersistenciaException e) {
			Throwable rootException=ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado el Tipo Documento", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		tipoDocumentoGuiSeleccionado = null;

		this.modalidad = "view";

		return path;
	}

	public String bajaTipoDocumento() throws CloneNotSupportedException {

		String path = "";

		try {
			persistenciaTipoDocumentoBean.borrarTipoDocumentoGui(tipoDocumentoGuiSeleccionado);
			path = "TiposDocumento?faces-redirect=true&includeViewParams=true";
		} catch (PersistenciaException e) {
			Throwable rootException=ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado el Tipo Documento", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		this.modalidad = "view";

		return path;
	}
}