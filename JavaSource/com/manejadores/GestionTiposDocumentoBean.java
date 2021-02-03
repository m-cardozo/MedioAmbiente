package com.manejadores;

import java.io.Serializable;
import javax.inject.Named;

import java.util.ArrayList;

import javax.inject.Inject;

import com.entidades.TipoDocumento;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaTipoDocumentoBean;

import javax.enterprise.context.SessionScoped;

@Named("gestionTiposDocumento")
@SessionScoped
public class GestionTiposDocumentoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	PersistenciaTipoDocumentoBean persistenciaTipoDocumentoBean;

	private String criterioDescripcion;	
	private ArrayList<TipoDocumento> tiposDocumentoSeleccionados = new ArrayList<TipoDocumento>();
	private TipoDocumento tipoDocumentoSeleccionado;
	
	public GestionTiposDocumentoBean() {
		super();
	}

	public String seleccionarTiposDocumento() {
		
		try {
			tiposDocumentoSeleccionados = new ArrayList<TipoDocumento>(persistenciaTipoDocumentoBean.seleccionarTiposDocumento(criterioDescripcion));
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}

		return "";
	}

	public String verDatosTipoDocumento() {
		return "DatosTipoDocumento";
	}

	public PersistenciaTipoDocumentoBean getPersistenciaTipoDocumentoBean() {
		return persistenciaTipoDocumentoBean;
	}
	public void setPersistenciaTipoDocumentoBean(PersistenciaTipoDocumentoBean persistenciaTipoDocumentoBean) {
		this.persistenciaTipoDocumentoBean = persistenciaTipoDocumentoBean;
	}
	public String getCriterioDescripcion() {
		return criterioDescripcion;
	}
	public void setCriterioDescripcion(String criterioDescripcion) {
		this.criterioDescripcion = criterioDescripcion;
	}
	public ArrayList<TipoDocumento> getTiposDocumentoSeleccionados() {
		return tiposDocumentoSeleccionados;
	}
	public void setTiposDocumentoSeleccionados(ArrayList<TipoDocumento> tiposDocumentoSeleccionados) {
		this.tiposDocumentoSeleccionados = tiposDocumentoSeleccionados;
	}
	public TipoDocumento getTipoDocumentoSeleccionado() {
		return tipoDocumentoSeleccionado;
	}
	public void setTipoDocumentoSeleccionado(TipoDocumento tipoDocumentoSeleccionado) {
		this.tipoDocumentoSeleccionado = tipoDocumentoSeleccionado;
	}

	public void preRenderViewListener() {
		seleccionarTiposDocumento();
	}
}