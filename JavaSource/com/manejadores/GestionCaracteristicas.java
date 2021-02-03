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
import com.logicaNegocio.PersistenciaCaracteristicaBean;
import com.logicaNegocio.PersistenciaDepartamentoBean;
import com.logicaNegocio.PersistenciaFenomenoBean;
import com.logicaNegocio.PersistenciaZonaBean;
import com.modelo.CaracteristicaGui;
import com.modelo.DepartamentoGui;
import com.modelo.FenomenoGui;
import com.modelo.ZonaGui;
import com.entidades.Fenomeno;
import com.entidades.Zona;
import com.excepciones.ExceptionsTools;

@Named(value="gestionCaracteristica")
@SessionScoped
public class GestionCaracteristicas implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	PersistenciaCaracteristicaBean persistenciaCaracteristicaBean;

	private CaracteristicaGui caracteristicaGuiSeleccionada;
	
	@EJB
	PersistenciaFenomenoBean persistenciaFenomenoBean;

	
	private List<FenomenoGui> listaFenomenos;
	
	private FenomenoGui fenomenoGuiSeleccionado;
	
	

	
	private Long id;
	private String modalidad;
	private boolean modoEdicion = false;

	public GestionCaracteristicas() {
		super();
		
	}

	@PostConstruct
	public void init() {
		
		
	if(caracteristicaGuiSeleccionada!=null && caracteristicaGuiSeleccionada.getCaracteristicaId()!=-1 && caracteristicaGuiSeleccionada.getCaracteristicaId()!=0) {
				
				fenomenoGuiSeleccionado=caracteristicaGuiSeleccionada.getFenomenoGui();			
				
				try {
					listaFenomenos=new ArrayList<FenomenoGui>();
					for(Fenomeno f : persistenciaFenomenoBean.getFenomenos()){
						listaFenomenos.add(persistenciaFenomenoBean.fromFenomeno(f));
					}			
					
				} catch (PersistenciaException e) {
					e.printStackTrace();
				}
			}else {
				caracteristicaGuiSeleccionada = new CaracteristicaGui();
				
			 	listaFenomenos=new ArrayList<FenomenoGui>();
				try {
					for(Fenomeno f : persistenciaFenomenoBean.getFenomenos()){
						listaFenomenos.add(persistenciaFenomenoBean.fromFenomeno(f));
					}
				} catch (PersistenciaException e) {
					e.printStackTrace();
				}
				
			}
	}

	public String reset() {
		caracteristicaGuiSeleccionada = new CaracteristicaGui();
		return "";
	}

	public PersistenciaCaracteristicaBean getPersistenciaFenomenoBean() {
		return persistenciaCaracteristicaBean;
	}
	public void setPersistenciaCaracteristicaBean(PersistenciaCaracteristicaBean persistenciaCaracteristicaBean) {
		this.persistenciaCaracteristicaBean= persistenciaCaracteristicaBean;
	}
	public CaracteristicaGui getCaracteristicaGuiSeleccionado() {
		return caracteristicaGuiSeleccionada;
	}
	public void setCaracteristicaGuiSeleccionada(CaracteristicaGui caracteristicaGuiSeleccionada) {
		this.caracteristicaGuiSeleccionada = caracteristicaGuiSeleccionada;
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
			
			caracteristicaGuiSeleccionada = persistenciaCaracteristicaBean.buscarCaracteristicaGui(id);
			fenomenoGuiSeleccionado=caracteristicaGuiSeleccionada.getFenomenoGui();			
		} else {
			
			
			caracteristicaGuiSeleccionada = new CaracteristicaGui();
			fenomenoGuiSeleccionado=new FenomenoGui();
			caracteristicaGuiSeleccionada.setFenomenoGui(fenomenoGuiSeleccionado);
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
		return "Caracteristicas?faces-redirect=true&includeViewParams=true";
	}

	public String salvarCambios() throws CloneNotSupportedException {

		String path = "";
		
		if (caracteristicaGuiSeleccionada.getCaracteristicaId() == null) {

			CaracteristicaGui caracteristicaGuiNuevo = null;

			try {

				caracteristicaGuiNuevo = (CaracteristicaGui) persistenciaCaracteristicaBean.agregarCaracteristicaGui(caracteristicaGuiSeleccionada);
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

			caracteristicaGuiSeleccionada = null;

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado una nueva caracteristica.", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";

		} else if (modalidad.equals("update")) {

			try {
				persistenciaCaracteristicaBean.modificarCaracteristicaGui(caracteristicaGuiSeleccionada);
				path = "Caracteristicas?faces-redirect=true&includeViewParams=true";


			} catch (PersistenciaException e) {
				Throwable rootException = ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado la caracteristica.", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";
		}

		return path;
	}

	public String altaCaracteristica() throws CloneNotSupportedException {

		String path = "";
		

		if (caracteristicaGuiSeleccionada.getCaracteristicaId() == null) {

			CaracteristicaGui caracteristicaGuiNuevo = null;

			try {
				caracteristicaGuiNuevo = (CaracteristicaGui) persistenciaCaracteristicaBean.agregarCaracteristicaGui(caracteristicaGuiSeleccionada);
				path = "Caracteristicas?faces-redirect=true&includeViewParams=true";

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

	public String modificarCaracteristica() throws CloneNotSupportedException {

		String path = "";

		try {
			
			persistenciaCaracteristicaBean.modificarCaracteristicaGui(caracteristicaGuiSeleccionada);
			path = "Caracteristicas?faces-redirect=true&includeViewParams=true";

		} catch (PersistenciaException e) {
			Throwable rootException=ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado la caracteristica.", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		caracteristicaGuiSeleccionada = null;

		this.modalidad = "view";

		return path;
	}

	public String bajaCaracteristica() throws CloneNotSupportedException {

		String path = "";

		try {
			persistenciaCaracteristicaBean.borrarCaracteristicaGui(caracteristicaGuiSeleccionada);
			path = "Caracteristicas?faces-redirect=true&includeViewParams=true";


		} catch (PersistenciaException e) {
			Throwable rootException=ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado la caracteristica.", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		this.modalidad = "view";

		return path;
	}

	public CaracteristicaGui getCaracteristicaGuiSeleccionada() {
		return caracteristicaGuiSeleccionada;
	}

	public List<FenomenoGui> getListaFenomenos() {
		return listaFenomenos;
	}

	public void setListaFenomenos(List<FenomenoGui> listaFenomenos) {
		this.listaFenomenos = listaFenomenos;
	}

	public FenomenoGui getFenomenoGuiSeleccionado() {
		return fenomenoGuiSeleccionado;
	}

	public void setFenomenoGuiSeleccionado(FenomenoGui fenomenoGuiSeleccionado) {
		this.fenomenoGuiSeleccionado = fenomenoGuiSeleccionado;
	}

	
		
	
}