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
import com.logicaNegocio.PersistenciaLocalidadBean;
import com.modelo.DepartamentoGui;
import com.modelo.LocalidadGui;
import com.modelo.ZonaGui;
import com.entidades.Departamento;
import com.entidades.Zona;
import com.excepciones.ExceptionsTools;

@Named(value="gestionLocalidad")
@SessionScoped
public class GestionLocalidades implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	PersistenciaLocalidadBean persistenciaLocalidadBean;
	
	@EJB
	PersistenciaDepartamentoBean persistenciaDepartamentoBean;
	

	private LocalidadGui localidadGuiSeleccionada;
	
	private List<DepartamentoGui> listaDepartamentos;
	
	private DepartamentoGui departamentoGuiSeleccionado;
	

	private Long id;
	private String modalidad;	
	private boolean modoEdicion = false;

	public GestionLocalidades() {
		super();
	}

	@PostConstruct
	public void init() {
		
		//localidadGuiSeleccionada = new LocalidadGui();
		if(localidadGuiSeleccionada!=null && localidadGuiSeleccionada.getLocalidadId()!=-1 && localidadGuiSeleccionada.getLocalidadId()!=0) {
			
			departamentoGuiSeleccionado=localidadGuiSeleccionada.getDepartamentoGui();			
			
			try {
				listaDepartamentos=new ArrayList<DepartamentoGui>();
				for(Departamento d: persistenciaDepartamentoBean.getDepartamentos()){
					listaDepartamentos.add(persistenciaDepartamentoBean.fromDepartamento(d));
				}			
				
			} catch (PersistenciaException e) {
				e.printStackTrace();
			}
		}else {
			localidadGuiSeleccionada= new LocalidadGui();
			
		 	listaDepartamentos=new ArrayList<DepartamentoGui>();
			try {
				for(Departamento d : persistenciaDepartamentoBean.getDepartamentos()){
					listaDepartamentos.add(persistenciaDepartamentoBean.fromDepartamento(d));
				}
			} catch (PersistenciaException e) {
				e.printStackTrace();
			}
			
		}
	}

	public String reset() {
		localidadGuiSeleccionada = new LocalidadGui();
		return "";
	}

	public PersistenciaLocalidadBean getPersistenciaLocalidadBean() {
		return persistenciaLocalidadBean;
	}
	public void setPersistenciaLocalidadBean(PersistenciaLocalidadBean persistenciaLocalidadBean) {
		this.persistenciaLocalidadBean= persistenciaLocalidadBean;
	}
	public LocalidadGui getLocalidadGuiSeleccionado() {
		return localidadGuiSeleccionada;
	}
	public void setLocalidadGuiSeleccionado(LocalidadGui localidadGuiSeleccionado) {
		this.localidadGuiSeleccionada = localidadGuiSeleccionado;
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
			localidadGuiSeleccionada = persistenciaLocalidadBean.buscarLocalidadGui(id);
			departamentoGuiSeleccionado=localidadGuiSeleccionada.getDepartamentoGui();
		} else {
			localidadGuiSeleccionada = new LocalidadGui();
			departamentoGuiSeleccionado= new DepartamentoGui();
			localidadGuiSeleccionada.setDepartamentoGui(departamentoGuiSeleccionado);
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
		return "Localidades?faces-redirect=true&includeViewParams=true";
	}

	public String salvarCambios() throws CloneNotSupportedException {

		String path = "";

		if (localidadGuiSeleccionada.getLocalidadId() == null) {

			LocalidadGui localidadGuiNuevo = null;

			try {

				localidadGuiNuevo = (LocalidadGui) persistenciaLocalidadBean.agregarLocalidadGui(localidadGuiSeleccionada);
				path = "Localidades?faces-redirect=true&includeViewParams=true";

			} catch (PersistenciaException e) {
				Throwable rootException=ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			this.id = null;

			localidadGuiSeleccionada = null;

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado un nueva localidad", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";

		} else if (modalidad.equals("update")) {

			try {
				persistenciaLocalidadBean.modificarLocalidadGui(localidadGuiSeleccionada);
				path = "Localidades?faces-redirect=true&includeViewParams=true";


			} catch (PersistenciaException e) {
				Throwable rootException = ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado la localidad", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";
		}

		return path;
	}

	public String altaLocalidad() throws CloneNotSupportedException {

		String path = "";

		if (localidadGuiSeleccionada.getLocalidadId() == null) {

			LocalidadGui localidadGuiNuevo = null;

			try {
				localidadGuiNuevo = (LocalidadGui) persistenciaLocalidadBean.agregarLocalidadGui(localidadGuiSeleccionada);
				path = "Localidades?faces-redirect=true&includeViewParams=true";

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

	public String modificarLocalidad() throws CloneNotSupportedException {

		String path = "";

		try {

			persistenciaLocalidadBean.modificarLocalidadGui(localidadGuiSeleccionada);
			path = "Localidades?faces-redirect=true&includeViewParams=true";

		} catch (PersistenciaException e) {
			Throwable rootException=ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado la localidad", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		localidadGuiSeleccionada = null;

		this.modalidad = "view";

		return path;
	}

	public String bajaLocalidad() throws CloneNotSupportedException {

		String path = "";

		try {
			persistenciaLocalidadBean.borrarLocalidadGui(localidadGuiSeleccionada);
			path = "Localidades?faces-redirect=true&includeViewParams=true";


		} catch (PersistenciaException e) {
			Throwable rootException=ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado la localidad", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		this.modalidad = "view";

		return path;
	}

	public PersistenciaDepartamentoBean getPersistenciaDepartamentoBean() {
		return persistenciaDepartamentoBean;
	}

	public void setPersistenciaDepartamentoBean(PersistenciaDepartamentoBean persistenciaDepartamentoBean) {
		this.persistenciaDepartamentoBean = persistenciaDepartamentoBean;
	}

	public LocalidadGui getLocalidadGuiSeleccionada() {
		return localidadGuiSeleccionada;
	}

	public void setLocalidadGuiSeleccionada(LocalidadGui localidadGuiSeleccionada) {
		this.localidadGuiSeleccionada = localidadGuiSeleccionada;
	}

	public List<DepartamentoGui> getListaDepartamentos() {
		return listaDepartamentos;
	}

	public void setListaDepartamentos(List<DepartamentoGui> listaDepartamentos) {
		this.listaDepartamentos = listaDepartamentos;
	}

	public DepartamentoGui getDepartamentoGuiSeleccionado() {
		return departamentoGuiSeleccionado;
	}

	public void setDepartamentoGuiSeleccionado(DepartamentoGui departamentoGuiSeleccionado) {
		this.departamentoGuiSeleccionado = departamentoGuiSeleccionado;
	}
	
	
}