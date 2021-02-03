package com.logicaNegocio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.entidades.ObservacionImagen;
import com.entidades.ObservacionRevision;
import com.entidades.ObservacionRevisionPK;
import com.excepciones.PersistenciaException;
import com.manejadoresDAO.ObservacionRevisionDAO;
import com.modelo.CaracteristicaGui;
import com.modelo.ObservacionGui;
import com.modelo.ObservacionRevisionGui;
import com.modelo.UsuarioGui;

@Named(value = "persistenciaObservacionRevision")
@Stateless
@LocalBean
public class PersistenciaObservacionRevisionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	ObservacionRevisionDAO observacionRevisionPersistenciaDAO;

	@EJB
	PersistenciaObservacionBean persistenciaOB;
	
	@EJB
	PersistenciaObservacionRevisionBean persistenciaORB;
	
	@EJB
	PersistenciaUsuarioBean persistenciaUB;

	public PersistenciaObservacionRevisionBean() {
		super();
	}

	public ObservacionRevisionGui fromObservacionRevision(ObservacionRevision oR) {
		ObservacionRevisionGui oRG = new ObservacionRevisionGui();

		if (oR.getId() != null) {
			oRG.setId(oR.getId());
		}

		ObservacionGui oG=persistenciaOB.buscarObservacionGui(oR.getId().getObservacionId());
		oRG.setObservacionGui(oG);
		
		UsuarioGui uG=persistenciaUB.buscarUsuarioGui(oR.getId().getUsuarioId());
		oRG.setUsuarioGui(uG);
		
		
		oRG.setFiabilidad(oR.getFiabilidad());
		oRG.setComentario(oR.getComentario());
		oRG.setValorFecha(oR.getValorFecha());
		oRG.setFecha(oR.getFecha());

		return oRG;
	}

	public ObservacionRevision toObservacionRevision(ObservacionRevisionGui oRG) {
		ObservacionRevision observacionRevision = new ObservacionRevision();
		observacionRevision.setObservacion(persistenciaOB.toObservacion(oRG.getObservacionGui()) != null ? persistenciaOB.toObservacion(oRG.getObservacionGui()) : null);
		observacionRevision.setUsuario(persistenciaUB.toUsuario(oRG.getUsuarioGui()) != null ? persistenciaUB.toUsuario(oRG.getUsuarioGui()) : null);
		observacionRevision.setId(oRG.getId());
		observacionRevision.setFiabilidad(oRG.getFiabilidad());
		observacionRevision.setComentario(oRG.getComentario());
		observacionRevision.setValorFecha(oRG.getValorFecha());
		observacionRevision.setFecha(oRG.getFecha());
		

		return observacionRevision;
	}

	public List<ObservacionRevision> seleccionarObservacionesRevisiones(String criterioIdObservacion, String criterioIdUsuario) throws PersistenciaException {
		return observacionRevisionPersistenciaDAO.seleccionarObservacionesRevisiones(criterioIdObservacion, criterioIdUsuario);
	}

	public ObservacionRevisionGui buscarObservacionRevision(ObservacionRevisionPK id) {
		ObservacionRevision oR = observacionRevisionPersistenciaDAO.buscarObservacionRevision(id);

		return fromObservacionRevision(oR);
	}

	public ObservacionRevisionGui buscarObservacionRevisionGui(ObservacionRevisionPK id) {
		ObservacionRevision oR = observacionRevisionPersistenciaDAO.buscarObservacionRevision(id);

		return fromObservacionRevision(oR);
	}

	public ObservacionRevisionGui agregarObservacionRevisionGui(ObservacionRevisionGui observacionRevisionGuiSeleccionado) throws PersistenciaException {
		ObservacionRevision oR = observacionRevisionPersistenciaDAO.agregarObservacionRevision(toObservacionRevision(observacionRevisionGuiSeleccionado));

		return fromObservacionRevision(oR);
	}

	public void modificarObservacionRevisionGui(ObservacionRevisionGui observacionRevisionGuiSeleccionado) throws PersistenciaException {
		observacionRevisionPersistenciaDAO.modificarObservacionRevision(toObservacionRevision(observacionRevisionGuiSeleccionado));
	}

	public void borrarObservacionRevisionGui(ObservacionRevisionGui observacionRevisionGuiSeleccionado) throws PersistenciaException {
		observacionRevisionPersistenciaDAO.borrarObservacionRevision(toObservacionRevision(observacionRevisionGuiSeleccionado));
	}

	public List<ObservacionRevision> getObservacionesRevisiones() throws PersistenciaException {
		return observacionRevisionPersistenciaDAO.getObservacionesRevisiones();
	}
	
	public List<ObservacionRevision> getObservacionRevisionesByIdObservacion(Long observacionId) throws PersistenciaException {
		return observacionRevisionPersistenciaDAO.seleccionarObservacionRevisiones(observacionId.toString());
	}
}