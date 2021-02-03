package com.manejadores;
import java.io.Serializable;
import javax.inject.Named;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.entidades.ObservacionImagen;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaObservacionImagenBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

@Named("gestionObservacionImagenes")
@SessionScoped
public class GestionObservacionesImagenBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	PersistenciaObservacionImagenBean persistenciaObservacionImagenBean;

	private String criterioObservacion;
	
	private Long observacionId;
	private Long imagenId;
	
	
	private List<ObservacionImagen> observacionImagenSeleccionadas = new ArrayList<ObservacionImagen>();
	private ObservacionImagen observacionImagenSeleccionada;

	public GestionObservacionesImagenBean() {
		super();
		System.out.println("**************************************");
		System.out.println("GestionObservacionesImagenBean Imagenes:");
		if(observacionId==null) {
			System.out.println("observacionId nulo");
		}else {
			System.out.println("observacionId: "+observacionId.toString());
		}
		System.out.println("**************************************");
		
	}

	public String seleccionarObservacionImagenes() {
		try {
			observacionImagenSeleccionadas = new ArrayList<ObservacionImagen>(persistenciaObservacionImagenBean.seleccionarObservacionImagenes(criterioObservacion));
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}

		return "";
	}

	public String verDatosObservacionImagen() {
		return "DatosObservacionImagen";
	}

	public PersistenciaObservacionImagenBean getPersistenciaObservacionImagenBean() {
		return persistenciaObservacionImagenBean;
	}
	public void setPersistenciaObservacionImagenBean(PersistenciaObservacionImagenBean persistenciaObservacionImagenBean) {
		this.persistenciaObservacionImagenBean = persistenciaObservacionImagenBean;
	}
	public String getCriterioObservacion() {
		return criterioObservacion;
	}
	public void setCriterioObservacion(String criterioObservacion) {
		this.criterioObservacion = criterioObservacion;
	}

	public List<ObservacionImagen> observacionImagenSeleccionadas() {
		return observacionImagenSeleccionadas;
	}
	
	public List<ObservacionImagen> observacionImagenSeleccionadasByIdObservacion(Long observacionId ) {
		try {
			System.out.println("**************************************");
			System.out.println("observacionImagenSeleccionadasByIdObservacion Imagenes:");
			if(observacionId==null) {
				System.out.println("observacionId nulo");
				return null;
			}else {
				System.out.println("observacionId: "+observacionId.toString());
				return persistenciaObservacionImagenBean.getObservacionImagenesByIdObservacion(observacionId);
			}
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}finally {
			System.out.println("**************************************");
		}
	}
	
	
	public void setObservacionImagenSeleccionadas(ArrayList<ObservacionImagen> observacionImagenSeleccionadas) {
		this.observacionImagenSeleccionadas = observacionImagenSeleccionadas;
	}
	public ObservacionImagen getObservacionImagenSeleccionada() {
		return observacionImagenSeleccionada;
	}
	public void setObservacionImagenSeleccionada(ObservacionImagen observacionImagenSeleccionada) {
		this.observacionImagenSeleccionada = observacionImagenSeleccionada;
	}

	public void preRenderViewListener() {
		System.out.println("**************************************");
		System.out.println("preRenderViewListener Imagenes:");
		if(observacionId==null) {
			System.out.println("observacionId nulo");
		}else {
			System.out.println("observacionId: "+observacionId.toString());
		}
		System.out.println("**************************************");
		
		observacionImagenSeleccionadas=observacionImagenSeleccionadasByIdObservacion(observacionId);
	}
	
	public Long getObservacionId() {
		return observacionId;
	}

	public void setObservacionId(Long observacionId) {
		this.observacionId = observacionId;
	}

	public Long getImagenId() {
		return imagenId;
	}

	public void setImagenId(Long imagenId) {
		this.imagenId = imagenId;
	}

	public List<ObservacionImagen> getObservacionImagenSeleccionadas() {
		return observacionImagenSeleccionadas;
	}

	public void setObservacionImagenSeleccionadas(List<ObservacionImagen> observacionImagenSeleccionadas) {
		this.observacionImagenSeleccionadas = observacionImagenSeleccionadas;
	}
	
	@PostConstruct
	public void init() {
		
		System.out.println("**************************************");
		System.out.println("Init Imagenes:");
		if(observacionId==null) {
			System.out.println("observacionId nulo");
		}else {
			System.out.println("observacionId: "+observacionId.toString());
		}
		System.out.println("**************************************");
	}
	

}