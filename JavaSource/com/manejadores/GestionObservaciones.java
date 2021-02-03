package com.manejadores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.component.selectonelistbox.SelectOneListbox;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaCaracteristicaBean;
import com.logicaNegocio.PersistenciaFenomenoBean;
import com.logicaNegocio.PersistenciaLocalidadBean;
import com.logicaNegocio.PersistenciaObservacionBean;
import com.logicaNegocio.PersistenciaObservacionCaracteristicaBean;
import com.logicaNegocio.PersistenciaUsuarioBean;
import com.modelo.CaracteristicaGui;
import com.modelo.FenomenoGui;
import com.modelo.LocalidadGui;
import com.modelo.ObservacionCaracteristicaGui;
import com.modelo.ObservacionGui;
import com.modelo.UsuarioGui;
import com.entidades.Caracteristica;
import com.entidades.Fenomeno;
import com.entidades.Localidad;
import com.entidades.ObservacionCaracteristica;
import com.entidades.Usuario;
import com.excepciones.ExceptionsTools;

@Named(value="gestionObservacion")
@SessionScoped
public class GestionObservaciones implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	PersistenciaObservacionBean persistenciaObservacionBean;
	@EJB
	PersistenciaFenomenoBean persistenciaFenomenoBean;	
	@EJB
	PersistenciaLocalidadBean persistenciaLocalidadBean;	
	@EJB
	PersistenciaUsuarioBean persistenciaUsuarioBean;	
	@EJB
	PersistenciaObservacionCaracteristicaBean persistenciaObservacionCaracteristicaBean;
	@EJB
	PersistenciaCaracteristicaBean persistenciaCaracteristicaBean;
	
	
	private List<FenomenoGui> listaFenomenos;
	private List<LocalidadGui> listaLocalidades;
	private List<UsuarioGui> listaUsuarios;
	private Map<String, Object> listaNivelesCriticos;
	private List<ObservacionCaracteristicaGui> listaObservacionesCaracteristicasDisponibles;
	private List<ObservacionCaracteristicaGui> listaObservacionesCaracteristicasSeleccionadas;
	private List<CaracteristicaGui> listaCaracteristicasSeleccionadas;
	private List<CaracteristicaGui> listaCaracteristicasDisponibles;
	private List<SelectItem> selectObsCaracDisp;
	private List<SelectItem> selectObsCaracSelec;
	
	private List<SelectItem> selectCaracDisp;
	private List<SelectItem> selectCaracSelec;
	
	
	
	private ObservacionGui observacionGuiSeleccionada;	
	private FenomenoGui fenomenoGuiSeleccionado;	
	private LocalidadGui localidadGuiSeleccionada;
	private UsuarioGui usuarioGuiSeleccionado;
	private ObservacionCaracteristicaGui observacionCaracteristicaGuiDisponibleSeleccionada;
	private ObservacionCaracteristicaGui observacionCaracteristicaGuiSeleccionadaSeleccionada;
	private CaracteristicaGui caracteristicaGuiSeleccionada;

	private String valorTexto;
	private Date valorFecha;
	private long valorNumero;
	
	private Long id;
	private String modalidad;
	private boolean modoEdicion = false;

	public GestionObservaciones() {
		super();
	}

	@PostConstruct
	public void init() {
		
		
		
		listaNivelesCriticos = new LinkedHashMap<String,Object>();
		listaNivelesCriticos.put("ALTO", "ALTO"); 
		listaNivelesCriticos.put("BAJO", "BAJO");
		listaNivelesCriticos.put("MEDIO", "MEDIO");
		
		if(observacionGuiSeleccionada!=null && observacionGuiSeleccionada.getObservacionId()!=-1 && observacionGuiSeleccionada.getObservacionId()!=0) {
			
			fenomenoGuiSeleccionado=observacionGuiSeleccionada.getFenomenoGui();			
			localidadGuiSeleccionada=observacionGuiSeleccionada.getLocalidadGui();
			usuarioGuiSeleccionado=observacionGuiSeleccionada.getUsuarioGui();
			observacionCaracteristicaGuiSeleccionadaSeleccionada=observacionGuiSeleccionada.getObservacionCaracteristicas().get(0);
			
			
			
			try {
				listaFenomenos=new ArrayList<FenomenoGui>();
				for(Fenomeno f : persistenciaFenomenoBean.getFenomenos()){
					listaFenomenos.add(persistenciaFenomenoBean.fromFenomeno(f));
				}			
				
				listaLocalidades=new ArrayList<LocalidadGui>();
				for(Localidad l : persistenciaLocalidadBean.getLocalidades()){
					listaLocalidades.add(persistenciaLocalidadBean.fromLocalidad(l));
				}
				
				listaUsuarios=new ArrayList<UsuarioGui>();
				for(Usuario u : persistenciaUsuarioBean.getUsuarios()){
					listaUsuarios.add(persistenciaUsuarioBean.fromUsuario(u));
				}
				
//				listaObservacionesCaracteristicasDisponibles=new ArrayList<ObservacionCaracteristicaGui>();
//				listaObservacionesCaracteristicasSeleccionadas=new ArrayList<ObservacionCaracteristicaGui>();
//				
//				for(ObservacionCaracteristica oC : persistenciaObservacionCaracteristicaBean.getObservacionesCaracteristicas()) {
//					if(!persistenciaObservacionCaracteristicaBean.inListaObservacionesCaracteristicas(observacionGuiSeleccionada.getObservacionCaracteristicas(),oC)) {
//						listaObservacionesCaracteristicasDisponibles.add(persistenciaObservacionCaracteristicaBean.fromObservacionCaracteristica(oC));
//						observacionCaracteristicaGuiDisponibleSeleccionada=listaObservacionesCaracteristicasDisponibles.get(0);
//					}else {
//						listaObservacionesCaracteristicasSeleccionadas.add(persistenciaObservacionCaracteristicaBean.fromObservacionCaracteristica(oC));
//						
//					}
//					
//				}
//				selectObsCaracDisp=new ArrayList<SelectItem>();
//				for(ObservacionCaracteristicaGui oCG :listaObservacionesCaracteristicasDisponibles) {
//					selectObsCaracDisp.add(new SelectItem(oCG, oCG.getCaracteristicaGui().getNombre()));
//				}
//				
//				selectObsCaracSelec=new ArrayList<SelectItem>();
//				for(ObservacionCaracteristicaGui oCG :listaObservacionesCaracteristicasSeleccionadas) {
//					selectObsCaracSelec.add(new SelectItem(oCG, oCG.getCaracteristicaGui().getNombre()));
//				}
				
				
			} catch (PersistenciaException e) {
				e.printStackTrace();
			}
		}else {
			
			observacionGuiSeleccionada=new ObservacionGui();
			fenomenoGuiSeleccionado = new FenomenoGui();
			observacionCaracteristicaGuiDisponibleSeleccionada=new ObservacionCaracteristicaGui();
			observacionCaracteristicaGuiSeleccionadaSeleccionada=new ObservacionCaracteristicaGui();
			
//			observacionCaracteristicaGuiSeleccionadaSeleccionada=new ObservacionCaracteristicaGui();
//			observacionCaracteristicaGuiDisponibleSeleccionada=new ObservacionCaracteristicaGui();
			
			
		 	listaFenomenos=new ArrayList<FenomenoGui>();
			try {
				for(Fenomeno f : persistenciaFenomenoBean.getFenomenos()){
					listaFenomenos.add(persistenciaFenomenoBean.fromFenomeno(f));
				}
			} catch (PersistenciaException e) {
				e.printStackTrace();
			}
			
			listaLocalidades=new ArrayList<LocalidadGui>();
			try {
				for(Localidad l : persistenciaLocalidadBean.getLocalidades()){
					listaLocalidades.add(persistenciaLocalidadBean.fromLocalidad(l));
				}
			} catch (PersistenciaException e) {
				e.printStackTrace();
			}
			
			listaUsuarios=new ArrayList<UsuarioGui>();
			try {
				for(Usuario u : persistenciaUsuarioBean.getUsuarios()){
					listaUsuarios.add(persistenciaUsuarioBean.fromUsuario(u));
				}
			} catch (PersistenciaException e) {
				e.printStackTrace();
			}
			
//			listaObservacionesCaracteristicasDisponibles=new ArrayList<ObservacionCaracteristicaGui>();
//			listaObservacionesCaracteristicasSeleccionadas=new ArrayList<ObservacionCaracteristicaGui>();
//			try {
//				
//				for(ObservacionCaracteristica oC : persistenciaObservacionCaracteristicaBean.getObservacionesCaracteristicas()) {
//					if(!persistenciaObservacionCaracteristicaBean.inListaObservacionesCaracteristicas(observacionGuiSeleccionada.getObservacionCaracteristicas(),oC)) {
//						listaObservacionesCaracteristicasDisponibles.add(persistenciaObservacionCaracteristicaBean.fromObservacionCaracteristica(oC));
//						observacionCaracteristicaGuiDisponibleSeleccionada=listaObservacionesCaracteristicasDisponibles.get(0);
//					}else {
//						listaObservacionesCaracteristicasSeleccionadas.add(persistenciaObservacionCaracteristicaBean.fromObservacionCaracteristica(oC));						
//					}
//					
//				}
//				selectObsCaracDisp=new ArrayList<SelectItem>();
//				for(ObservacionCaracteristicaGui oCG :listaObservacionesCaracteristicasDisponibles) {
//					selectObsCaracDisp.add(new SelectItem(oCG, oCG.getCaracteristicaGui().getNombre()));
//				}
//				
//				selectObsCaracSelec=new ArrayList<SelectItem>();
//				for(ObservacionCaracteristicaGui oCG :listaObservacionesCaracteristicasSeleccionadas) {
//					selectObsCaracSelec.add(new SelectItem(oCG, oCG.getCaracteristicaGui().getNombre()));
//				}
//			} catch (PersistenciaException e) {				
//				e.printStackTrace();
//			}
			
		}
	}

	public String reset() {
		observacionGuiSeleccionada = new ObservacionGui();

		return "";
	}

	public PersistenciaObservacionBean getPersistenciaObservacionBean() {
		return persistenciaObservacionBean;
	}
	
	public void setPersistenciaObservacionBean(PersistenciaObservacionBean persistenciaObservacionBean) {
		this.persistenciaObservacionBean = persistenciaObservacionBean;
	}
	
	public ObservacionGui getObservacionGuiSeleccionada() {
		return observacionGuiSeleccionada;
	}
	
	public void setObservacionGuiSeleccionada(ObservacionGui observacionGuiSeleccionada) {
		this.observacionGuiSeleccionada = observacionGuiSeleccionada;
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

	
	
	public String getValorTexto() {
		return valorTexto;
	}

	public void setValorTexto(String valorTexto) {
		this.valorTexto = valorTexto;
	}

	public Date getValorFecha() {
		return valorFecha;
	}

	public void setValorFecha(Date valorFecha) {
		this.valorFecha = valorFecha;
	}

	public long getValorNumero() {
		return valorNumero;
	}

	public void setValorNumero(long valorNumero) {
		this.valorNumero = valorNumero;
	}

	public void preRenderViewListener() {

		
		
		modoEdicion = false;

		if (id != null){
			
			observacionGuiSeleccionada = persistenciaObservacionBean.buscarObservacionGui(id);
			fenomenoGuiSeleccionado=observacionGuiSeleccionada.getFenomenoGui();			
			localidadGuiSeleccionada=observacionGuiSeleccionada.getLocalidadGui();
			usuarioGuiSeleccionado=observacionGuiSeleccionada.getUsuarioGui();
			
			
		} else {
			
			
			observacionGuiSeleccionada = new ObservacionGui();
			fenomenoGuiSeleccionado=new FenomenoGui();
			localidadGuiSeleccionada=new LocalidadGui();
			usuarioGuiSeleccionado=new UsuarioGui();
			observacionGuiSeleccionada.setFenomenoGui(fenomenoGuiSeleccionado);
			observacionGuiSeleccionada.setLocalidadGui(localidadGuiSeleccionada);
			observacionGuiSeleccionada.setUsuarioGui(usuarioGuiSeleccionado);
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
		return "Observaciones?faces-redirect=true&includeViewParams=true";
	}

	public String salvarCambios() throws CloneNotSupportedException {

		String path = "";

		if (observacionGuiSeleccionada.getObservacionId() == null) {

			ObservacionGui observacionGuiNuevo = null;

			try {
				observacionGuiNuevo = (ObservacionGui) persistenciaObservacionBean.agregarObservacionGui(observacionGuiSeleccionada);
				path = "Observaciones?faces-redirect=true&includeViewParams=true";
			} catch (PersistenciaException e) {
				Throwable rootException = ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			this.id = null;

			observacionGuiSeleccionada = null;

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado una nueva Observacion", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";

		} else if (modalidad.equals("update")) {

			try {
				persistenciaObservacionBean.modificarObservacionGui(observacionGuiSeleccionada);
				path = "Observaciones?faces-redirect=true&includeViewParams=true";
			} catch (PersistenciaException e) {
				Throwable rootException = ExceptionsTools.getCause(e); 

				String msg1 = e.getMessage(); 
				String msg2 = ExceptionsTools.formatedMsg(rootException);

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

				e.printStackTrace();
			}

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado la Observacion", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			this.modalidad = "view";
		}

		return path;
	}

	public String altaObservacion() throws CloneNotSupportedException {

		String path = "";

		if (observacionGuiSeleccionada.getObservacionId() == null) {

			ObservacionGui observacionGuiNuevo = null;

			try {
				observacionGuiNuevo = (ObservacionGui) persistenciaObservacionBean.agregarObservacionGui(observacionGuiSeleccionada);
				path = "Observaciones?faces-redirect=true&includeViewParams=true";
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

	public String modificarObservacion() throws CloneNotSupportedException {

		String path = "";

		try {
			
			persistenciaObservacionBean.modificarObservacionGui(observacionGuiSeleccionada);
			path = "Observaciones?faces-redirect=true&includeViewParams=true";
		} catch (PersistenciaException e) {
			Throwable rootException = ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado la Observacion", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		observacionGuiSeleccionada = null;

		this.modalidad = "view";

		return path;
	}

	public String bajaObservacion() throws CloneNotSupportedException {

		String path = "";

		try {
			persistenciaObservacionBean.borrarObservacionGui(observacionGuiSeleccionada);
			path = "Observaciones?faces-redirect=true&includeViewParams=true";
		} catch (PersistenciaException e) {
			Throwable rootException = ExceptionsTools.getCause(e); 
			String msg1 = e.getMessage(); 
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado la Observacion", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		this.modalidad = "view";

		return path;
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

	public List<LocalidadGui> getListaLocalidades() {
		return listaLocalidades;
	}

	public void setListaLocalidades(List<LocalidadGui> listaLocalidades) {
		this.listaLocalidades = listaLocalidades;
	}

	public LocalidadGui getLocalidadGuiSeleccionada() {
		return localidadGuiSeleccionada;
	}

	public void setLocalidadGuiSeleccionada(LocalidadGui localidadGuiSeleccionada) {
		this.localidadGuiSeleccionada = localidadGuiSeleccionada;
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

	public Map<String, Object> getListaNivelesCriticos() {
		return listaNivelesCriticos;
	}

	public void setListaNivelesCriticos(Map<String, Object> listaNivelesCriticos) {
		this.listaNivelesCriticos = listaNivelesCriticos;
	}

	
	public List<ObservacionCaracteristicaGui> getListaObservacionesCaracteristicasDisponibles() {
		return listaObservacionesCaracteristicasDisponibles;
	}

	public void setListaObservacionesCaracteristicasDisponibles(
			List<ObservacionCaracteristicaGui> listaObservacionesCaracteristicasDisponibles) {
		this.listaObservacionesCaracteristicasDisponibles = listaObservacionesCaracteristicasDisponibles;
	}

	public ObservacionCaracteristicaGui getObservacionCaracteristicaGuiDisponibleSeleccionada() {
		return observacionCaracteristicaGuiDisponibleSeleccionada;
	}

	public void setObservacionCaracteristicaGuiDisponibleSeleccionada(
			ObservacionCaracteristicaGui observacionCaracteristicaGuiDisponibleSeleccionada) {
		this.observacionCaracteristicaGuiDisponibleSeleccionada = observacionCaracteristicaGuiDisponibleSeleccionada;
	}

	public List<ObservacionCaracteristicaGui> getListaObservacionesCaracteristicasSeleccionadas() {
		return listaObservacionesCaracteristicasSeleccionadas;
	}

	public void setListaObservacionesCaracteristicasSeleccionadas(
			List<ObservacionCaracteristicaGui> listaObservacionesCaracteristicasSeleccionadas) {
		this.listaObservacionesCaracteristicasSeleccionadas = listaObservacionesCaracteristicasSeleccionadas;
	}

	public ObservacionCaracteristicaGui getObservacionCaracteristicaGuiSeleccionadaSeleccionada() {
		return observacionCaracteristicaGuiSeleccionadaSeleccionada;
	}

	public void setObservacionCaracteristicaGuiSeleccionadaSeleccionada(
			ObservacionCaracteristicaGui observacionCaracteristicaGuiSeleccionadaSeleccionada) {
		this.observacionCaracteristicaGuiSeleccionadaSeleccionada = observacionCaracteristicaGuiSeleccionadaSeleccionada;
	}

	public List<SelectItem> getSelectObsCaracDisp() {
		return selectObsCaracDisp;
	}

	public void setSelectObsCaracDisp(List<SelectItem> selectObsCaracDisp) {
		this.selectObsCaracDisp = selectObsCaracDisp;
	}

	public List<SelectItem> getSelectObsCaracSelec() {
		return selectObsCaracSelec;
	}

	public void setSelectObsCaracSelec(List<SelectItem> selectObsCaracSelec) {
		this.selectObsCaracSelec = selectObsCaracSelec;
	}
	
	
	public void establecerListaCaracteristicasByFenomeno(String idFenomeno){
		
		
		try {
			
			listaObservacionesCaracteristicasDisponibles=new ArrayList<ObservacionCaracteristicaGui>();
			listaObservacionesCaracteristicasSeleccionadas=new ArrayList<ObservacionCaracteristicaGui>();
			
			listaCaracteristicasDisponibles=new ArrayList<CaracteristicaGui>();
			listaCaracteristicasSeleccionadas=new ArrayList<CaracteristicaGui>();
			
			for(Caracteristica c : persistenciaCaracteristicaBean.getCaracteristicas()) {
				listaCaracteristicasDisponibles.add(persistenciaCaracteristicaBean.fromCaracteristica(c));
			}
			
			List<CaracteristicaGui> lTemp=new ArrayList<CaracteristicaGui>();
			for(CaracteristicaGui cD :listaCaracteristicasDisponibles){
				if(cD.getFenomenoGui().getFenomenoId().toString().compareTo(idFenomeno)==0) {
					lTemp.add(cD);
				}
			}
			listaCaracteristicasDisponibles=lTemp;
			
			caracteristicaGuiSeleccionada=listaCaracteristicasDisponibles.get(0);
			selectCaracDisp=new ArrayList<SelectItem>();
			for(CaracteristicaGui cG :listaCaracteristicasDisponibles) {
				selectCaracDisp.add(new SelectItem(cG, cG.getNombre()));
			}
			
			
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//selectCaracSelec=new ArrayList<SelectItem>();
		//for(CaracteristicaGui cG :listaCaracteristicasSeleccionadas) {
		//	selectCaracSelec.add(new SelectItem(cG, cG.getNombre()));
		//}
		
				
	}

	public List<CaracteristicaGui> getListaCaracteristicasSeleccionadas() {
		return listaCaracteristicasSeleccionadas;
	}

	public void setListaCaracteristicasSeleccionadas(List<CaracteristicaGui> listaCaracteristicasSeleccionadas) {
		this.listaCaracteristicasSeleccionadas = listaCaracteristicasSeleccionadas;
	}

	public CaracteristicaGui getCaracteristicaGuiSeleccionada() {
		return caracteristicaGuiSeleccionada;
	}

	public void setCaracteristicaGuiSeleccionada(CaracteristicaGui caracteristicaGuiSeleccionada) {
		this.caracteristicaGuiSeleccionada = caracteristicaGuiSeleccionada;
	}

	public List<SelectItem> getSelectCaracDisp() {
		return selectCaracDisp;
	}

	public void setSelectCaracDisp(List<SelectItem> selectCaracDisp) {
		this.selectCaracDisp = selectCaracDisp;
	}

	public List<SelectItem> getSelectCaracSelec() {
		return selectCaracSelec;
	}

	public void setSelectCaracSelec(List<SelectItem> selectCaracSelec) {
		this.selectCaracSelec = selectCaracSelec;
	}

	public List<CaracteristicaGui> getListaCaracteristicasDisponibles() {
		return listaCaracteristicasDisponibles;
	}

	public void setListaCaracteristicasDisponibles(List<CaracteristicaGui> listaCaracteristicasDisponibles) {
		this.listaCaracteristicasDisponibles = listaCaracteristicasDisponibles;
	}

	public void cambioCaracteristicaAltaListener(AjaxBehaviorEvent event) {		
		
	}
	
	public void cambioFenomenoAltaListener(AjaxBehaviorEvent event) {	
		try {			
			System.out.println("cambioFenomenoAltaListener");
			establecerListaCaracteristicasByFenomeno(observacionGuiSeleccionada.getFenomenoGui().getFenomenoId().toString());
			caracteristicaGuiSeleccionada=listaCaracteristicasDisponibles.get(0);
			System.out.println(caracteristicaGuiSeleccionada.getNombre()+" "+caracteristicaGuiSeleccionada.getFenomenoGui().getNombre());
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	public void eventoCaracteristicaListener(AjaxBehaviorEvent event) {
		try {
			System.out.println("eventoCaracteristicaListener");
			System.out.println(caracteristicaGuiSeleccionada.getNombre()+" "+caracteristicaGuiSeleccionada.getFenomenoGui().getNombre());
			System.out.println("Valor enviado:");
			if(((javax.faces.component.html.HtmlSelectOneMenu)event.getSource()).getSubmittedValue()!=null) {
				caracteristicaGuiSeleccionada= persistenciaCaracteristicaBean.buscarCaracteristicaGui(Long.parseLong(((javax.faces.component.html.HtmlSelectOneMenu)event.getSource()).getSubmittedValue().toString()));
			}else {
				caracteristicaGuiSeleccionada=new CaracteristicaGui();
			}
			System.out.println(caracteristicaGuiSeleccionada.getNombre()+" "+caracteristicaGuiSeleccionada.getFenomenoGui().getNombre());
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	public void asignarValorCaracteristicaListener(AjaxBehaviorEvent event) {
		try {
			System.out.println("asignarValorCaracteristicaListener");
			if(observacionCaracteristicaGuiDisponibleSeleccionada==null) {
				observacionCaracteristicaGuiDisponibleSeleccionada=new ObservacionCaracteristicaGui();
			}
			observacionCaracteristicaGuiDisponibleSeleccionada.setCaracteristicaGui(caracteristicaGuiSeleccionada);
			switch (caracteristicaGuiSeleccionada.getTipoDato()) {
			case "TEXTO":
				observacionCaracteristicaGuiDisponibleSeleccionada.setValorTexto(valorTexto);
				break;
			case "NUMERO":
				observacionCaracteristicaGuiDisponibleSeleccionada.setValorNumerico(valorNumero);
				break;
			case "FECHA":
				observacionCaracteristicaGuiDisponibleSeleccionada.setValorFecha(valorFecha);
				break;
			

			default:
				observacionCaracteristicaGuiDisponibleSeleccionada.setValorTexto(valorTexto);
				break;
			}
			
			System.out.println(observacionCaracteristicaGuiDisponibleSeleccionada.getCaracteristicaGui().getNombre()+" "+observacionCaracteristicaGuiDisponibleSeleccionada.getCaracteristicaGui().getFenomenoGui().getNombre()+ "->"+observacionCaracteristicaGuiDisponibleSeleccionada.getValorTexto()+ "->"+observacionCaracteristicaGuiDisponibleSeleccionada.getValorNumerico()+ "->"+observacionCaracteristicaGuiDisponibleSeleccionada.getValorFecha());
			
			System.out.println("Tipo de datos:" + caracteristicaGuiSeleccionada.getTipoDato());
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}	
	
}