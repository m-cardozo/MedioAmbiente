package com.logicaNegocio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.entidades.Observacion;
import com.excepciones.PersistenciaException;
import com.manejadoresDAO.ObservacionDAO;
import com.modelo.ObservacionGui;

@Named(value="persistenciaObservacion")
@Stateless
@LocalBean
public class PersistenciaObservacionBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static PersistenciaObservacionBean repository = new
			PersistenciaObservacionBean();
		
			public static PersistenciaObservacionBean getInstance() {
			return repository;
			}


	@EJB
	ObservacionDAO observacionPersistenciaDAO;
	
	@EJB
	PersistenciaFenomenoBean persistenciaFB;
	
	@EJB
	PersistenciaLocalidadBean persistenciaLB;
	
	@EJB
	PersistenciaUsuarioBean persistenciaUB;

	public PersistenciaObservacionBean() {
		super();
	}

	public ObservacionGui fromObservacion(Observacion o) {
		ObservacionGui oG = new ObservacionGui();

		if(o.getObservacionId() != null) {
			oG.setObservacionId(o.getObservacionId().longValue());
		}

		oG.setDescripcion(o.getDescripcion());
		oG.setFecha(o.getFecha());
		oG.setFenomenoGui(persistenciaFB.fromFenomeno(o.getFenomeno()));
		oG.setLocalidadGui(persistenciaLB.fromLocalidad(o.getLocalidad()));
		oG.setUsuarioGui(persistenciaUB.fromUsuario(o.getUsuario()));
		oG.setAltitud(o.getAltitud());
		oG.setLatitud(o.getLatitud());
		oG.setLongitud(o.getLongitud());
		oG.setNivelCritico(o.getNivelCritico());

		return oG;
	}
	
	public ObservacionGui fromObservacionPrueba(Observacion o) {
		ObservacionGui oG = new ObservacionGui();

		if(o.getObservacionId() != null) {
			oG.setObservacionId(o.getObservacionId().longValue());
		}

		oG.setDescripcion(o.getDescripcion());
		oG.setFecha(o.getFecha());
		oG.setFenomenoGui(persistenciaFB.buscarFenomeno((o.getFenomeno().getFenomenoId())));
		oG.setLocalidadGui(persistenciaLB.buscarLocalidad(o.getLocalidad().getLocalidadId()));
		oG.setUsuarioGui(persistenciaUB.buscarUsuario(o.getUsuario().getUsuarioId()));
		oG.setAltitud(o.getAltitud());
		oG.setLatitud(o.getLatitud());
		oG.setLongitud(o.getLongitud());
		oG.setNivelCritico(o.getNivelCritico());

		return oG;
	}

	public Observacion toObservacion(ObservacionGui oG) {
		Observacion observacion= new Observacion();
		observacion.setObservacionId(oG.getObservacionId() != null ? oG.getObservacionId().longValue() : null);

		observacion.setDescripcion(oG.getDescripcion());
		observacion.setFecha(oG.getFecha());
		observacion.setFenomeno(persistenciaFB.toFenomeno(oG.getFenomenoGui()));
		observacion.setLocalidad(persistenciaLB.toLocalidad(oG.getLocalidadGui()));
		observacion.setUsuario(persistenciaUB.toUsuario(oG.getUsuarioGui()));
		observacion.setAltitud(oG.getAltitud());
		observacion.setLatitud(oG.getLatitud());
		observacion.setLongitud(oG.getLongitud());
		observacion.setNivelCritico(oG.getNivelCritico());

		return observacion;
	}
	

	public Observacion toObservacionPrueba(ObservacionGui oG) {
		Observacion observacion= new Observacion();
		observacion.setObservacionId(oG.getObservacionId() != null ? oG.getObservacionId().longValue() : null);
		observacion.setFenomeno(persistenciaFB.toFenomeno(oG.getFenomenoGui()));
		observacion.setLocalidad(persistenciaLB.toLocalidad(oG.getLocalidadGui()));
		observacion.setUsuario(persistenciaUB.toUsuario(oG.getUsuarioGui()));
		observacion.setDescripcion(oG.getDescripcion());
		observacion.setFecha(oG.getFecha());
		observacion.setAltitud(oG.getAltitud());
		observacion.setLatitud(oG.getLatitud());
		observacion.setLongitud(oG.getLongitud());
		observacion.setNivelCritico(oG.getNivelCritico());

		return observacion;
	}
	
	

	public List<Observacion> seleccionarObservaciones(String criterioDescripcion) throws PersistenciaException {
		return observacionPersistenciaDAO.seleccionarObservaciones(criterioDescripcion);
	}

	public ObservacionGui buscarObservacion(Long id) {
		Observacion o = observacionPersistenciaDAO.buscarObservacion(id);

		return fromObservacion(o);
	}

	public ObservacionGui buscarObservacionGui(Long id) {
		Observacion o = observacionPersistenciaDAO.buscarObservacion(id);

		return fromObservacion(o);
	}

	public ObservacionGui agregarObservacionGuiPrueba(ObservacionGui observacionGuiSeleccionado) throws PersistenciaException   {
		Observacion o = observacionPersistenciaDAO.agregarObservacion(toObservacionPrueba(observacionGuiSeleccionado));

		return fromObservacionPrueba(o);
	}
	
	public ObservacionGui agregarObservacionGui(ObservacionGui observacionGuiSeleccionado) throws PersistenciaException   {
		Observacion o = observacionPersistenciaDAO.agregarObservacion(toObservacion(observacionGuiSeleccionado));

		return fromObservacion(o);
	}
	
	

	public void modificarObservacionGui(ObservacionGui observacionGuiSeleccionado) throws PersistenciaException   {
		observacionPersistenciaDAO.modificarObservacion(toObservacion(observacionGuiSeleccionado));
	}

	public void borrarPorIdGui(Long id) throws PersistenciaException   {
		observacionPersistenciaDAO.borrarPorId(id);
	}
	
	public void borrarObservacionGui(ObservacionGui observacionGuiSeleccionado) throws PersistenciaException   {
		observacionPersistenciaDAO.borrarObservacion(toObservacion(observacionGuiSeleccionado));
	}

	public List<Observacion> getObservaciones() throws PersistenciaException {
		return observacionPersistenciaDAO.buscarObservaciones();
	}
}