package com.manejadores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaDepartamentoBean;
import com.logicaNegocio.PersistenciaZonaBean;
import com.modelo.DepartamentoGui;
import com.modelo.ZonaGui;
import com.entidades.Zona;
import com.excepciones.ExceptionsTools;

@Named(value="gestionDepartamento")
@SessionScoped
public class GestionDepartamentos implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	PersistenciaDepartamentoBean persistenciaDepartamentoBean;
	
	@EJB
	PersistenciaZonaBean persistenciaZonaBean;

	private DepartamentoGui departamentoGuiSeleccionado;
	
	private List<ZonaGui> listaZonas;
	
	private ZonaGui zonaGuiSeleccionada;
	
	private Zona zonaSeleccionada;
	
	private Long id;
	private String modalidad;
	private boolean modoEdicion = false;

	public GestionDepartamentos() {
		super();
		
	}

	@PostConstruct
	public void init() {
		
		
		if(departamentoGuiSeleccionado!=null && departamentoGuiSeleccionado.getDepartamentoId()!=-1 && departamentoGuiSeleccionado.getDepartamentoId()!=0) {
			
			zonaGuiSeleccionada=departamentoGuiSeleccionado.getZonaGui();			
			
			try {
				listaZonas=new ArrayList<ZonaGui>();
				for(Zona z : persistenciaZonaBean.getZonas()){
					listaZonas.add(persistenciaZonaBean.fromZona(z));
				}			
				
			} catch (PersistenciaException e) {
				e.printStackTrace();
			}
		}else {
			departamentoGuiSeleccionado = new DepartamentoGui();
			
		 	listaZonas=new ArrayList<ZonaGui>();
			try {
				for(Zona z : persistenciaZonaBean.getZonas()){
					listaZonas.add(persistenciaZonaBean.fromZona(z));
				}
			} catch (PersistenciaException e) {
				e.printStackTrace();
			}
			
		}
	}

	public String reset() {
		departamentoGuiSeleccionado = new DepartamentoGui();
		return "";
	}

	public PersistenciaDepartamentoBean getPersistenciaDepartamentoBean() {
		return persistenciaDepartamentoBean;
	}
	public void setPersistenciaDepartamentoBean(PersistenciaDepartamentoBean persistenciaDepartamentoBean) {
		this.persistenciaDepartamentoBean= persistenciaDepartamentoBean;
	}
	public DepartamentoGui getDepartamentoGuiSeleccionado() {
		return departamentoGuiSeleccionado;
	}
	public void setDepartamentoGuiSeleccionado(DepartamentoGui departamentoGuiSeleccionado) {
		this.departamentoGuiSeleccionado = departamentoGuiSeleccionado;
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
			
			departamentoGuiSeleccionado = persistenciaDepartamentoBean.buscarDepartamentoGui(id);
			zonaGuiSeleccionada=departamentoGuiSeleccionado.getZonaGui();			
		} else {
			
			
			departamentoGuiSeleccionado = new DepartamentoGui();
			zonaGuiSeleccionada=new ZonaGui();
			departamentoGuiSeleccionado.setZonaGui(zonaGuiSeleccionada);
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
		return "Departamentos?faces-redirect=true&includeViewParams=true";
	}

	public String salvarCambios() throws CloneNotSupportedException {

		String path = "";
		
		if (departamentoGuiSeleccionado.getDepartamentoId() == null) {

			DepartamentoGui departamentoGuiNuevo = null;

			try {

				departamentoGuiNuevo = (DepartamentoGui) persistenciaDepartamentoBean.agregarDepartamentoGui(departamentoGuiSeleccionado);
				path = "Departamentos?faces-redirect=true&includeViewParams=true";

			} catch (PersistenciaException e) {
				Throwable rootException=ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			this.id = null;

			departamentoGuiSeleccionado = null;

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado un nueva departamento", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";

		} else if (modalidad.equals("update")) {

			try {
				persistenciaDepartamentoBean.modificarDepartamentoGui(departamentoGuiSeleccionado);
				path = "Departamentos?faces-redirect=true&includeViewParams=true";


			} catch (PersistenciaException e) {
				Throwable rootException = ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado el departamento", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";
		}

		return path;
	}

	public String altaDepartamento() throws CloneNotSupportedException {

		String path = "";
		

		if (departamentoGuiSeleccionado.getDepartamentoId() == null) {

			DepartamentoGui departamentoGuiNuevo = null;

			try {
				departamentoGuiNuevo = (DepartamentoGui) persistenciaDepartamentoBean.agregarDepartamentoGui(departamentoGuiSeleccionado);
				path = "Departamentos?faces-redirect=true&includeViewParams=true";

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

	public String modificarDepartamento() throws CloneNotSupportedException {

		String path = "";

		try {
			
			persistenciaDepartamentoBean.modificarDepartamentoGui(departamentoGuiSeleccionado);
			path = "Departamentos?faces-redirect=true&includeViewParams=true";

		} catch (PersistenciaException e) {
			Throwable rootException=ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado el departamento", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		departamentoGuiSeleccionado = null;

		this.modalidad = "view";

		return path;
	}

	public String bajaDepartamento() throws CloneNotSupportedException {

		String path = "";

		try {
			persistenciaDepartamentoBean.borrarDepartamentoGui(departamentoGuiSeleccionado);
			path = "Departamentos?faces-redirect=true&includeViewParams=true";


		} catch (PersistenciaException e) {
			Throwable rootException=ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado el departamento", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		this.modalidad = "view";

		return path;
	}

	public List<ZonaGui> getListaZonas() {
		return listaZonas;
	}

	public void setListaZonas(List<ZonaGui> listaZonas) {
		this.listaZonas = listaZonas;
	}

	public ZonaGui getZonaGuiSeleccionada() {
		return zonaGuiSeleccionada;
	}

	public void setZonaGuiSeleccionada(ZonaGui zonaGuiSeleccionada) {
		this.zonaGuiSeleccionada = zonaGuiSeleccionada;
	}

	public Zona getZonaSeleccionada() {
		return zonaSeleccionada;
	}

	public void setZonaSeleccionada(Zona zonaSeleccionada) {
		this.zonaSeleccionada = zonaSeleccionada;
	}

	
	
}