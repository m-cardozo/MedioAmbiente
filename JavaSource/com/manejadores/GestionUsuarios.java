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
import javax.faces.event.AjaxBehaviorEvent;

import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaRolBean;
import com.logicaNegocio.PersistenciaTipoDocumentoBean;
import com.logicaNegocio.PersistenciaUsuarioBean;
import com.modelo.DepartamentoGui;
import com.modelo.LocalidadGui;
import com.modelo.RolGui;
import com.modelo.TipoDocumentoGui;
import com.modelo.UsuarioGui;
import com.entidades.Departamento;
import com.entidades.Rol;
import com.entidades.TipoDocumento;
import com.excepciones.ExceptionsTools;

@Named(value="gestionUsuario")
@SessionScoped
public class GestionUsuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	PersistenciaTipoDocumentoBean persistenciaTipoDocumentoBean;
	@EJB
	PersistenciaRolBean persistenciaRolBean;
	@EJB
	PersistenciaUsuarioBean persistenciaUsuarioBean;

	private List<TipoDocumentoGui> listaTiposDocumentos;
	private List<RolGui> listaRoles;
	private List<String> listaEstados;
	
	private UsuarioGui usuarioGuiSeleccionado;
	private TipoDocumentoGui tipoDocumentoGuiSeleccionado;
	private RolGui rolGuiSeleccionado;

	private Long id;
	private String modalidad;
	private boolean modoEdicion = false;

	public GestionUsuarios() {
		super();
	}

	@PostConstruct
	public void init() {
		
		System.out.println("init");
		//localidadGuiSeleccionada = new LocalidadGui();
		if(usuarioGuiSeleccionado!=null && usuarioGuiSeleccionado.getUsuarioId()!=-1 && usuarioGuiSeleccionado.getUsuarioId()!=0) {
			
			
			tipoDocumentoGuiSeleccionado=usuarioGuiSeleccionado.getTipoDocumentoGui();			
			rolGuiSeleccionado=usuarioGuiSeleccionado.getRolGui();
			
			try 
			{
				listaTiposDocumentos=new ArrayList<TipoDocumentoGui>();
				for(TipoDocumento tD: persistenciaTipoDocumentoBean.getTiposDocumento()){
					listaTiposDocumentos.add(persistenciaTipoDocumentoBean.fromTipoDocumento(tD));
				}
				
				listaEstados=new ArrayList<String>();
				listaEstados.add("ACTIVO");
				listaEstados.add("INACTIVO");
				
				
				listaRoles=new ArrayList<RolGui>();
				for(Rol r: persistenciaRolBean.getRoles()){
					listaRoles.add(persistenciaRolBean.fromRol(r));
				}
				
			} catch (PersistenciaException e) {
				e.printStackTrace();
			}
		}else 
		{
			usuarioGuiSeleccionado= new UsuarioGui();
			
			try 
			{
				listaTiposDocumentos=new ArrayList<TipoDocumentoGui>();
				for(TipoDocumento tD: persistenciaTipoDocumentoBean.getTiposDocumento()){
					listaTiposDocumentos.add(persistenciaTipoDocumentoBean.fromTipoDocumento(tD));
				}
				
				listaEstados=new ArrayList<String>();
				listaEstados.add("ACTIVO");
				listaEstados.add("INACTIVO");
				
				
				listaRoles=new ArrayList<RolGui>();
				for(Rol r: persistenciaRolBean.getRoles()){
					listaRoles.add(persistenciaRolBean.fromRol(r));
				}
				
			} catch (PersistenciaException e) {
				e.printStackTrace();
			}
		}	
	}

	public String reset() {
		usuarioGuiSeleccionado = new UsuarioGui();

		return "";
	}

	public PersistenciaUsuarioBean getPersistenciaUsuarioBean() {
		return persistenciaUsuarioBean;
	}
	public void setPersistenciaUsuarioBean(PersistenciaUsuarioBean persistenciaUsuarioBean) {
		this.persistenciaUsuarioBean = persistenciaUsuarioBean;
	}
	public UsuarioGui getUsuarioGuiSeleccionado() {
		return usuarioGuiSeleccionado;
	}
	public void setUsuarioGuiSeleccionado(UsuarioGui usuarioGuiSeleccionado) {
		this.usuarioGuiSeleccionado = usuarioGuiSeleccionado;
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

		
		System.out.println("preRenderViewListener");
		System.out.println(id);
		modoEdicion = false;

		if (id != null){
			usuarioGuiSeleccionado = persistenciaUsuarioBean.buscarUsuarioGui(id);
			tipoDocumentoGuiSeleccionado=usuarioGuiSeleccionado.getTipoDocumentoGui();
			rolGuiSeleccionado=usuarioGuiSeleccionado.getRolGui();			
		} else {
			usuarioGuiSeleccionado = new UsuarioGui();
			tipoDocumentoGuiSeleccionado=new TipoDocumentoGui();
			rolGuiSeleccionado=new RolGui();
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
		return "Usuarios?faces-redirect=true&includeViewParams=true";
	}

	public String salvarCambios() throws CloneNotSupportedException {

		String path = "";

		if (usuarioGuiSeleccionado.getUsuarioId() == null) {

			UsuarioGui usuarioGuiNuevo = null;

			try {
				usuarioGuiNuevo = (UsuarioGui) persistenciaUsuarioBean.agregarUsuarioGui(usuarioGuiSeleccionado);
				path = "Usuarios?faces-redirect=true&includeViewParams=true";
			} catch (PersistenciaException e) {
				Throwable rootException = ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			this.id = null;

			usuarioGuiSeleccionado = null;

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado un nuevo Usuario", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";

		} else if (modalidad.equals("update")) {

			try {
				persistenciaUsuarioBean.modificarUsuarioGui(usuarioGuiSeleccionado);
				path = "Usuarios?faces-redirect=true&includeViewParams=true";
			} catch (PersistenciaException e) {
				Throwable rootException = ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado el Usuario", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";
		}

		return path;
	}

	public String altaUsuario() throws CloneNotSupportedException {

		String path = "";

		if (usuarioGuiSeleccionado.getUsuarioId() == null) {

			UsuarioGui usuarioGuiNuevo = null;

			try {
				usuarioGuiNuevo = (UsuarioGui) persistenciaUsuarioBean.agregarUsuarioGui(usuarioGuiSeleccionado);
				path = "Usuarios?faces-redirect=true&includeViewParams=true";
			} catch (PersistenciaException e) {
				Throwable rootException = ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesContext.getCurrentInstance().addMessage(null, ExceptionsTools.NotificarError("Error", "No se pudo realizar la operacion.\n Por favor verifique los datos."));

				e.printStackTrace();
			}			

			this.modalidad = "view";
		}

		return path;
	}

	public String modificarUsuario() throws CloneNotSupportedException {

		String path = "";

		try {
			
			persistenciaUsuarioBean.modificarUsuarioGui(usuarioGuiSeleccionado);
			path = "Usuarios?faces-redirect=true&includeViewParams=true";
		} catch (PersistenciaException e) {
			Throwable rootException = ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado el Usuario", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		usuarioGuiSeleccionado = null;

		this.modalidad = "view";

		return path;
	}

	public String bajaUsuario() throws CloneNotSupportedException {

		String path = "";

		try {
			persistenciaUsuarioBean.borrarUsuarioGui(usuarioGuiSeleccionado);
			path = "Usuarios?faces-redirect=true&includeViewParams=true";
		} catch (PersistenciaException e) {
			Throwable rootException = ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado el Usuario", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		this.modalidad = "view";

		return path;
	}

	private String usuario;
	private String clave;

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}

	public String login() throws CloneNotSupportedException {

		String path = "";
		boolean res = false;
		try {
			res=persistenciaUsuarioBean.login(usuario, clave);
			if(res){
				
				path = "index?faces-redirect=true&includeViewParams=true";
			}			
		} catch (Exception e) {
			Throwable rootException = ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado el Usuario", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		this.modalidad = "view";

		return path;
	}

	public void fireSelection(AjaxBehaviorEvent event) {
		System.out.println("Evento disparado 2 ");
	}

	public TipoDocumentoGui getTipoDocumentoGuiSeleccionado() {
		return tipoDocumentoGuiSeleccionado;
	}

	public void setTipoDocumentoGuiSeleccionado(TipoDocumentoGui tipoDocumentoGuiSeleccionado) {
		this.tipoDocumentoGuiSeleccionado = tipoDocumentoGuiSeleccionado;
	}

	public RolGui getRolGuiSeleccionado() {
		return rolGuiSeleccionado;
	}

	public void setRolGuiSeleccionado(RolGui rolGuiSeleccionado) {
		this.rolGuiSeleccionado = rolGuiSeleccionado;
	}

	public List<TipoDocumentoGui> getListaTiposDocumentos() {
		return listaTiposDocumentos;
	}

	public void setListaTiposDocumentos(List<TipoDocumentoGui> listaTiposDocumentos) {
		this.listaTiposDocumentos = listaTiposDocumentos;
	}

	public List<RolGui> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<RolGui> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public List<String> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<String> listaEstados) {
		this.listaEstados = listaEstados;
	}
	
	
}