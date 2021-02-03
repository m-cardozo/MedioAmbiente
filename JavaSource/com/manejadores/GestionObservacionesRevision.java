package com.manejadores;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.logicaNegocio.PersistenciaObservacionRevisionBean;
import com.logicaNegocio.PersistenciaUsuarioBean;
import com.modelo.FenomenoGui;
import com.modelo.LocalidadGui;
import com.modelo.ObservacionCaracteristicaGui;
import com.modelo.ObservacionGui;
import com.modelo.ObservacionImagenGui;
import com.modelo.ObservacionRevisionGui;
import com.modelo.UsuarioGui;
import com.entidades.Fenomeno;
import com.entidades.Localidad;
import com.entidades.ObservacionRevisionPK;
import com.entidades.Usuario;
import com.excepciones.ExceptionsTools;

@Named(value="gestionObservacionRevision")
@SessionScoped
public class GestionObservacionesRevision implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	PersistenciaObservacionRevisionBean persistenciaObservacionRevisionBean;
	
	@EJB
	PersistenciaObservacionBean persistenciaObservacionBean;
	
	@EJB
	PersistenciaUsuarioBean persistenciaUsuarioBean;
	
	

	private ObservacionRevisionGui observacionRevisionGuiSeleccionada;
	private UsuarioGui usuarioGuiSeleccionado;	
	private ObservacionRevisionPK observacionRevisionPK;
	private ObservacionGui observacionGuiSeleccionada;
	
	
	
	private Long observacionId;
	private Long usuarioId;
	
	private String modalidad;	
	private boolean modoEdicion = false;
	
	private Map<String, Object> listaFiabilidad;
	private List<UsuarioGui> listaUsuarios;
	
	
	


	public GestionObservacionesRevision() {
		super();
	}

	@PostConstruct
	public void init() {
		
		
		listaFiabilidad = new LinkedHashMap<String,Object>();
		listaFiabilidad.put("MALO", "0");
		listaFiabilidad.put("BUENO", "1");
		if(observacionId!=null && usuarioId!=null) {
			
			observacionRevisionGuiSeleccionada=persistenciaObservacionRevisionBean.buscarObservacionRevisionGui(new ObservacionRevisionPK(usuarioId, observacionId));
			if(observacionRevisionGuiSeleccionada!=null && observacionRevisionGuiSeleccionada.getId().getObservacionId()!=-1 && observacionRevisionGuiSeleccionada.getId().getUsuarioId()!=0) {
				
				usuarioGuiSeleccionado=observacionRevisionGuiSeleccionada.getUsuarioGui();
				observacionGuiSeleccionada=observacionRevisionGuiSeleccionada.getObservacionGui();
				
				try {
					
					listaUsuarios=new ArrayList<UsuarioGui>();
					for(Usuario u : persistenciaUsuarioBean.getUsuarios()){
						listaUsuarios.add(persistenciaUsuarioBean.fromUsuario(u));
					}
					
				} catch (PersistenciaException e) {
					e.printStackTrace();
				}
			}else {
				
				
				usuarioGuiSeleccionado=new UsuarioGui();
				observacionRevisionGuiSeleccionada =new ObservacionRevisionGui();
				observacionRevisionGuiSeleccionada.setObservacionGui(persistenciaObservacionBean.buscarObservacionGui(observacionId));
				observacionRevisionGuiSeleccionada.setUsuarioGui(usuarioGuiSeleccionado);
				observacionGuiSeleccionada=observacionRevisionGuiSeleccionada.getObservacionGui();
				
				listaUsuarios=new ArrayList<UsuarioGui>();
				try {
					for(Usuario u : persistenciaUsuarioBean.getUsuarios()){
						listaUsuarios.add(persistenciaUsuarioBean.fromUsuario(u));
					}
				} catch (PersistenciaException e) {
					e.printStackTrace();
				}			
			}
		}else {
			if(observacionRevisionGuiSeleccionada!=null && observacionRevisionGuiSeleccionada.getId().getObservacionId()!=-1 && observacionRevisionGuiSeleccionada.getId().getUsuarioId()!=0) {
				
				usuarioGuiSeleccionado=observacionRevisionGuiSeleccionada.getUsuarioGui();
				
				try {
					
					listaUsuarios=new ArrayList<UsuarioGui>();
					for(Usuario u : persistenciaUsuarioBean.getUsuarios()){
						listaUsuarios.add(persistenciaUsuarioBean.fromUsuario(u));
					}
					
				} catch (PersistenciaException e) {
					e.printStackTrace();
				}
			}else {
				
				
				
				observacionRevisionGuiSeleccionada =new ObservacionRevisionGui();
				usuarioGuiSeleccionado=observacionRevisionGuiSeleccionada.getUsuarioGui();
				observacionRevisionPK= observacionRevisionGuiSeleccionada.getId();
				observacionGuiSeleccionada= observacionRevisionGuiSeleccionada.getObservacionGui();
				
				listaUsuarios=new ArrayList<UsuarioGui>();
				try {
					for(Usuario u : persistenciaUsuarioBean.getUsuarios()){
						listaUsuarios.add(persistenciaUsuarioBean.fromUsuario(u));
					}
				} catch (PersistenciaException e) {
					e.printStackTrace();
				}			
			}
		}
		
	}

	public String reset() {
		observacionRevisionGuiSeleccionada = new ObservacionRevisionGui();
		return "";
	}

	
	public ObservacionRevisionGui getObservacionRevisionGuiSeleccionada() {
		return observacionRevisionGuiSeleccionada;
	}

	public void setObservacionRevisionGuiSeleccionada(ObservacionRevisionGui observacionRevisionGuiSeleccionada) {
		this.observacionRevisionGuiSeleccionada = observacionRevisionGuiSeleccionada;
	}

	
	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
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

		if ((usuarioId != null) && (observacionId != null)){
			observacionRevisionGuiSeleccionada = persistenciaObservacionRevisionBean.buscarObservacionRevision(new ObservacionRevisionPK(usuarioId, observacionId));
		} else {
			observacionRevisionGuiSeleccionada = new ObservacionRevisionGui();	
			observacionRevisionPK=new ObservacionRevisionPK();
			observacionRevisionPK.setObservacionId(observacionId);
			observacionRevisionGuiSeleccionada.setId(observacionRevisionPK);
			observacionRevisionGuiSeleccionada.setObservacionGui(persistenciaObservacionBean.buscarObservacion(observacionId));
			
			
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
		return "ObservacionesRevisiones?faces-redirect=true&includeViewParams=true";
	}

	public String salvarCambios() throws CloneNotSupportedException {

		String path = "";

		if (observacionRevisionGuiSeleccionada.getObservacionGui() == null) {

			ObservacionRevisionGui observacionRevisionGuiNueva = null;
			
			try {

				observacionRevisionGuiNueva = (ObservacionRevisionGui) persistenciaObservacionRevisionBean.agregarObservacionRevisionGui(observacionRevisionGuiSeleccionada); 
				path = "ObservacionesRevisiones?faces-redirect=true&includeViewParams=true";

			} catch (PersistenciaException e) {
				Throwable rootException=ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			this.usuarioId = null;
			this.observacionId = null;

			observacionRevisionGuiSeleccionada = null;

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado una revision", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";

		} else if (modalidad.equals("update")) {

			try {
				persistenciaObservacionRevisionBean.modificarObservacionRevisionGui(observacionRevisionGuiSeleccionada);
				path = "ObservacionesRevisiones?faces-redirect=true&includeViewParams=true";


			} catch (PersistenciaException e) {
				Throwable rootException = ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado la revision", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";
		}

		return path;
	}

	public String altaRevision() throws CloneNotSupportedException {

		String path = "";

		ObservacionRevisionGui observacionRevisionGuiNueva = null;

		try {
			 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
			 Date date = new Date();  
			   
			 observacionRevisionGuiSeleccionada.setValorFecha(date);
			 observacionRevisionGuiSeleccionada.setFecha(date);
			 
			observacionRevisionGuiNueva = (ObservacionRevisionGui) persistenciaObservacionRevisionBean.agregarObservacionRevisionGui(observacionRevisionGuiSeleccionada);
			path = "ObservacionesRevisiones?faces-redirect=true&includeViewParams=true";

		} catch (PersistenciaException e) {
			Throwable rootException=ExceptionsTools.getCause(e); 

			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesContext.getCurrentInstance().addMessage(null, ExceptionsTools.NotificarError("Error", "No se pudo realizar la operacion.\n Por favor verifique los datos."));

			e.printStackTrace();
		}			

		this.modalidad = "view";		

		return path;
	}

	public String modificarRevision() throws CloneNotSupportedException {

		String path = "";

		try {
			System.out.println("Imagen: " + observacionRevisionGuiSeleccionada.getId().toString());
			persistenciaObservacionRevisionBean.modificarObservacionRevisionGui(observacionRevisionGuiSeleccionada); 
			path = "ObservacionesRevisiones?faces-redirect=true&includeViewParams=true";

		} catch (PersistenciaException e) {
			Throwable rootException=ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado la revision", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		observacionRevisionGuiSeleccionada = null;

		this.modalidad = "view";

		return path;
	}

	public String bajaRevision() throws CloneNotSupportedException {

		String path = "";

		try {
			//observacionRevisionGuiSeleccionada.setId(observacionRevisionPK);
			
			persistenciaObservacionRevisionBean.borrarObservacionRevisionGui(observacionRevisionGuiSeleccionada);   
			path = "ObservacionesRevisiones?faces-redirect=true&includeViewParams=true";

		} catch (PersistenciaException e) {
			Throwable rootException=ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado la revision", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		this.modalidad = "view";
		this.usuarioId = null;
		this.observacionId = null;

		return path;
	}

	public Long getObservacionId() {
		return observacionId;
	}

	public void setObservacionId(Long observacionId) {
		this.observacionId = observacionId;
		
	}

	public Map<String, Object> getListaFiabilidad() {
		return listaFiabilidad;
	}

	public void setListaFiabilidad(Map<String, Object> listaFiabilidad) {
		this.listaFiabilidad = listaFiabilidad;
	}

	public ObservacionRevisionPK getObservacionRevisionPK() {
		return observacionRevisionPK;
	}

	public void setObservacionRevisionPK(ObservacionRevisionPK observacionRevisionPK) {
		this.observacionRevisionPK = observacionRevisionPK;
	}

	public List<UsuarioGui> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<UsuarioGui> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public UsuarioGui getUsuarioGuiSeleccionado() {
		return usuarioGuiSeleccionado;
	}

	public void setUsuarioGuiSeleccionado(UsuarioGui usuarioGuiSeleccionado) {
		this.usuarioGuiSeleccionado = usuarioGuiSeleccionado;
	}

	public ObservacionGui getObservacionGuiSeleccionada() {
		return observacionGuiSeleccionada;
	}

	public void setObservacionGuiSeleccionada(ObservacionGui observacionGuiSeleccionada) {
		this.observacionGuiSeleccionada = observacionGuiSeleccionada;
	}
	

	
	

}
