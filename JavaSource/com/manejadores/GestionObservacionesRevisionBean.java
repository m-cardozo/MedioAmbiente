package com.manejadores;
import java.io.Serializable;
import javax.inject.Named;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.entidades.ObservacionImagen;
import com.entidades.ObservacionRevision;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaObservacionImagenBean;
import com.logicaNegocio.PersistenciaObservacionRevisionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

@Named("gestionObservacionRevisiones")
@SessionScoped
public class GestionObservacionesRevisionBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	PersistenciaObservacionRevisionBean persistenciaObservacionRevisionBean;

	private String criterioObservacion;
	
	private String criterioUsuario;
	
	private Long observacionId;
	private Long revisionId;
	
	
	private List<ObservacionRevision> observacionRevisionSeleccionadas = new ArrayList<ObservacionRevision>();
	private ObservacionRevision observacionRevisionSeleccionada;

	public GestionObservacionesRevisionBean() {
		super();		
	}

	public String seleccionarObservacionRevisiones() {
		try {
			observacionRevisionSeleccionadas = new ArrayList<ObservacionRevision>(persistenciaObservacionRevisionBean.seleccionarObservacionesRevisiones(criterioObservacion,criterioUsuario));
			
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}

		return "";
	}

	public String verDatosObservacionRevision() {
		return "DatosObservacionRevision";
	}

	public PersistenciaObservacionRevisionBean getPersistenciaObservacionRevisionBean() {
		return persistenciaObservacionRevisionBean;
	}
	public void setPersistenciaObservacionRevisionBean(PersistenciaObservacionRevisionBean persistenciaObservacionRevisionBean) {
		this.persistenciaObservacionRevisionBean = persistenciaObservacionRevisionBean;
	}
	public String getCriterioObservacion() {
		return criterioObservacion;
	}
	public void setCriterioObservacion(String criterioObservacion) {
		this.criterioObservacion = criterioObservacion;
	}

	public List<ObservacionRevision> observacionRevisionSeleccionadas() {
		return observacionRevisionSeleccionadas;
	}
	
	public List<ObservacionRevision> observacionRevisionSeleccionadasByIdObservacion(Long observacionId ) {
		try {
			return persistenciaObservacionRevisionBean.getObservacionRevisionesByIdObservacion(observacionId);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return observacionRevisionSeleccionadas;		
	}
	
	
	public void setObservacionRevisionSeleccionadas(ArrayList<ObservacionRevision> observacionRevisionSeleccionadas) {
		this.observacionRevisionSeleccionadas = observacionRevisionSeleccionadas;
	}
	public ObservacionRevision getObservacionRevisionSeleccionada() {
		return observacionRevisionSeleccionada;
	}
	public void setObservacionRevisionSeleccionada(ObservacionRevision observacionRevisionSeleccionada) {
		this.observacionRevisionSeleccionada = observacionRevisionSeleccionada;
	}

	public void preRenderViewListener() {
		observacionRevisionSeleccionadas=observacionRevisionSeleccionadasByIdObservacion(observacionId);
	}
	
	public Long getObservacionId() {
		return observacionId;
	}

	public void setObservacionId(Long observacionId) {
		this.observacionId = observacionId;
	}

	public List<ObservacionRevision> getObservacionRevisionSeleccionadas() {
		return observacionRevisionSeleccionadas;
	}

	public void setObservacionRevisionSeleccionadas(List<ObservacionRevision> observacionRevisionSeleccionadas) {
		this.observacionRevisionSeleccionadas = observacionRevisionSeleccionadas;
	}
	
	@PostConstruct
	public void init() {		
	}

	public String getCriterioUsuario() {
		return criterioUsuario;
	}

	public void setCriterioUsuario(String criterioUsuario) {
		this.criterioUsuario = criterioUsuario;
	}

	public Long getRevisionId() {
		return revisionId;
	}

	public void setRevisionId(Long revisionId) {
		this.revisionId = revisionId;
		
		
	}
	
	

}