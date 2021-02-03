package com.manejadores;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaObservacionBean;
import com.logicaNegocio.PersistenciaObservacionImagenBean;
import com.modelo.ObservacionImagenGui;
import com.excepciones.ExceptionsTools;

@Named(value="gestionObservacionImagen")
@SessionScoped
public class GestionObservacionesImagen implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	PersistenciaObservacionImagenBean persistenciaObservacionImagenBean;
	
	@EJB
	PersistenciaObservacionBean persistenciaObservacionBean;

	private ObservacionImagenGui observacionImagenGuiSeleccionada;

	private Long observacionId;
	private Long id;
	
	private String modalidad;	
	private boolean modoEdicion = false;


	public GestionObservacionesImagen() {
		super();
	}

	@PostConstruct
	public void init() {
		observacionImagenGuiSeleccionada = new ObservacionImagenGui();
		
	}

	public String reset() {
		observacionImagenGuiSeleccionada = new ObservacionImagenGui();
		return "";
	}

	public PersistenciaObservacionImagenBean getPersistenciaObservacionImagenBean() {
		return persistenciaObservacionImagenBean;
	}
	public void setPersistenciaObservacionBean(PersistenciaObservacionImagenBean persistenciaObservacionImagenBean) {
		this.persistenciaObservacionImagenBean = persistenciaObservacionImagenBean;
	}
	public ObservacionImagenGui getObservacionImagenGuiSeleccionada() {
		return observacionImagenGuiSeleccionada;
	}
	public void setObservacionImagenGuiSeleccionada(ObservacionImagenGui observacionImagenGuiSeleccionada) {
		this.observacionImagenGuiSeleccionada = observacionImagenGuiSeleccionada;
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
			observacionImagenGuiSeleccionada = persistenciaObservacionImagenBean.buscarObservacionImagen(id);
		} else {
			observacionImagenGuiSeleccionada = new ObservacionImagenGui();			
		}
		observacionImagenGuiSeleccionada.setObservacionGui(persistenciaObservacionBean.buscarObservacionGui(observacionId));
		

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
		return "ObservacionImagen?faces-redirect=true&includeViewParams=true";
	}

	public String salvarCambios() throws CloneNotSupportedException {

		String path = "";

		if (observacionImagenGuiSeleccionada.getObservacionImagenId() == null) {

			ObservacionImagenGui observacionImagenGuiNueva = null;
			
			try {

				observacionImagenGuiNueva = (ObservacionImagenGui) persistenciaObservacionImagenBean.agregarObservacionImagenGui(observacionImagenGuiSeleccionada); 
				path = "ObservacionImagen?faces-redirect=true&includeViewParams=true";

			} catch (PersistenciaException e) {
				Throwable rootException=ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			this.id = null;

			observacionImagenGuiSeleccionada = null;

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado una imagen", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";

		} else if (modalidad.equals("update")) {

			try {
				persistenciaObservacionImagenBean.modificarObservacionImagenGui(observacionImagenGuiSeleccionada);
				path = "ObservacionImagen?faces-redirect=true&includeViewParams=true";


			} catch (PersistenciaException e) {
				Throwable rootException = ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado la imagen", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";
		}

		return path;
	}

	public String altaImagen() throws CloneNotSupportedException {

		String path = "";

		if (observacionImagenGuiSeleccionada.getObservacionImagenId() == null) {

			ObservacionImagenGui observacionImagenGuiNueva = null;

			try {
				observacionImagenGuiNueva = (ObservacionImagenGui) persistenciaObservacionImagenBean.agregarObservacionImagenGui(observacionImagenGuiSeleccionada);
				path = "ObservacionesImagenes?faces-redirect=true&includeViewParams=true";

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

	public String modificarImagen() throws CloneNotSupportedException {

		String path = "";

		try {
			System.out.println("Imagen: " + observacionImagenGuiSeleccionada.getObservacionImagenId().toString());
			persistenciaObservacionImagenBean.modificarObservacionImagenGui(observacionImagenGuiSeleccionada); 
			path = "ObservacionesImagenes?faces-redirect=true&includeViewParams=true";

		} catch (PersistenciaException e) {
			Throwable rootException=ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado la imagen", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		observacionImagenGuiSeleccionada = null;

		this.modalidad = "view";

		return path;
	}

	public String bajaImagen() throws CloneNotSupportedException {

		String path = "";

		try {
			persistenciaObservacionImagenBean.borrarObservacionImagenGui(observacionImagenGuiSeleccionada);   
			path = "ObservacionesImagenes?faces-redirect=true&includeViewParams=true";

		} catch (PersistenciaException e) {
			Throwable rootException=ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado la imagen", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		this.modalidad = "view";
		this.id = null;

		return path;
	}

	public Long getObservacionId() {
		return observacionId;
	}

	public void setObservacionId(Long observacionId) {
		this.observacionId = observacionId;
		
	}

	
	

}
