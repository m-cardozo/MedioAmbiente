package com.logicaNegocio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.entidades.ObservacionImagen;
import com.excepciones.PersistenciaException;
import com.manejadoresDAO.ObservacionImagenDAO;
import com.modelo.ObservacionImagenGui;

@Named(value="persistenciaObservacionImagen")
@Stateless
@LocalBean
public class PersistenciaObservacionImagenBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	ObservacionImagenDAO observacionImagenPersistenciaDAO;
	
	@EJB
	PersistenciaObservacionBean persistenciaOB;

	public PersistenciaObservacionImagenBean() {
		super();
	}

	public ObservacionImagenGui fromObservacionImagen(ObservacionImagen oI) {
		ObservacionImagenGui oIG = new ObservacionImagenGui();

		if(oI.getObservacionImagenId() != null) {
			oIG.setObservacionImagenId(oI.getObservacionImagenId());
		}

		oIG.setPath(oI.getPath());
		oIG.setObservacionGui(persistenciaOB.fromObservacion(oI.getObservacion()));

		return oIG;
	}

	public ObservacionImagen toObservacionImagen (ObservacionImagenGui oIG) {
		ObservacionImagen observacionImagen = new ObservacionImagen();
		observacionImagen.setObservacionImagenId(oIG.getObservacionImagenId() != null ? oIG.getObservacionImagenId().longValue() : null);
		observacionImagen.setPath(oIG.getPath());
		observacionImagen.setObservacion(persistenciaOB.toObservacion(oIG.getObservacionGui()));

		return observacionImagen;
	}

	public List<ObservacionImagen> seleccionarObservacionImagenes(String criterioObservacion) throws PersistenciaException {
		return observacionImagenPersistenciaDAO.seleccionarObservacionImagenes(criterioObservacion);
	}

	public ObservacionImagenGui buscarObservacionImagen(Long id) {
		ObservacionImagen oI = observacionImagenPersistenciaDAO.buscarObservacionImagen(id);

		return fromObservacionImagen(oI);
	}

	public ObservacionImagenGui buscarObservacionImagenGui(Long id) {
		ObservacionImagen oI = observacionImagenPersistenciaDAO.buscarObservacionImagen(id);

		return fromObservacionImagen(oI);
	}

	public ObservacionImagenGui agregarObservacionImagenGui(ObservacionImagenGui observacionImagenGuiSeleccionada) throws PersistenciaException   {
		ObservacionImagen oI = observacionImagenPersistenciaDAO.agregarObservacionImagen(toObservacionImagen(observacionImagenGuiSeleccionada));

		return fromObservacionImagen(oI);
	}

	public void modificarObservacionImagenGui(ObservacionImagenGui observacionImagenGuiSeleccionada) throws PersistenciaException   {
		observacionImagenPersistenciaDAO.modificarObservacionImagen(toObservacionImagen(observacionImagenGuiSeleccionada));
	}

	public void borrarObservacionImagenGui(ObservacionImagenGui observacionImagenGuiSeleccionada) throws PersistenciaException   {
		observacionImagenPersistenciaDAO.borrarObservacionImagen(toObservacionImagen(observacionImagenGuiSeleccionada));
	}

	public List<ObservacionImagen> getObservacionImagenes() throws PersistenciaException {
		return observacionImagenPersistenciaDAO.buscarObservacionImagenes();
	}

	public List<ObservacionImagen> getObservacionImagenesByIdObservacion(Long observacionId) throws PersistenciaException {
		return observacionImagenPersistenciaDAO.seleccionarObservacionImagenes(observacionId.toString());
	}

	
}