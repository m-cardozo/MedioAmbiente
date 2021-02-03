package com.manejadoresDAO;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entidades.ObservacionCaracteristica;
import com.entidades.ObservacionCaracteristicaPK;
import com.excepciones.PersistenciaException;

@Stateless
@LocalBean
public class ObservacionCaracteristicaDAO {

	@PersistenceContext
	private EntityManager em;

	public ObservacionCaracteristicaDAO() {
		super();
	}

	public ObservacionCaracteristica agregarObservacionCaracteristica(ObservacionCaracteristica oC) throws PersistenciaException {
		try {
			ObservacionCaracteristica oCAgregada = em.merge(oC);
			em.flush();

			return oCAgregada;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo agregar la Observacion Caracterstica." + e.getMessage(), e);
		}
		finally {}
	}

	public ObservacionCaracteristica borrarObservacionCaracteristica(ObservacionCaracteristica oC) throws PersistenciaException {
		ObservacionCaracteristica oCABorrar = em.find(ObservacionCaracteristica.class, oC.getObservacion().getObservacionId());

		if (oCABorrar == null) {
			throw new PersistenciaException("No existe la Observacion Caracteristica de id " + oC.getObservacion().getObservacionId());
		}

		try {
			em.remove(oCABorrar);
			em.flush();

			return oC;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo borrar la Observacion Caracteristica de id " + oC.getObservacion().getObservacionId());
		}
	}

	public ObservacionCaracteristica modificarObservacionCaracteristica(ObservacionCaracteristica oC) throws PersistenciaException {
		try {
			ObservacionCaracteristica oCAModificar = em.merge(oC);
			em.flush();

			return oCAModificar;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo modificar la Observacion Caracteristica." + e.getMessage(), e);
		}
	}

	public ObservacionCaracteristica buscarObservacionCaracteristica(ObservacionCaracteristicaPK oCPK) {
		ObservacionCaracteristica oC = em.find(ObservacionCaracteristica.class, oCPK);

		return oC;
	}

	public List<ObservacionCaracteristica> buscarObservacionCaracteristicas() throws PersistenciaException {
		try {
			String query= 	"Select oC from ObservacionCaracteristica oC";

			List<ObservacionCaracteristica> resultList = (List<ObservacionCaracteristica>) em.createQuery(query,ObservacionCaracteristica.class).getResultList();

			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}

	public List<ObservacionCaracteristica> seleccionarObservacionesCaracteristicas(String criterioIdObservacion, String criterioIdCaracteristica) throws PersistenciaException {
		try {
			String query = "Select oC from ObservacionCaracteristica oC ";
			String queryCriterio = "";

			if (criterioIdObservacion!=null && ! criterioIdObservacion.contentEquals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " oC.id_observacion = '" + criterioIdObservacion + "' ";
			}

			if (criterioIdCaracteristica!=null && ! criterioIdCaracteristica.contentEquals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " oC.id_caracteristica= '" + criterioIdCaracteristica + "' ";
			} 

			if (!queryCriterio.contentEquals("")) {
				query += " where " + queryCriterio;
			}

			List<ObservacionCaracteristica> resultList = (List<ObservacionCaracteristica>) em.createQuery(query,ObservacionCaracteristica.class).getResultList();

			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}

	public List<ObservacionCaracteristica> getObservacionesCaracteristicas() throws PersistenciaException {
		try {
			String query = "select oC from ObservacionCaracteristica oC";

			List<ObservacionCaracteristica> resultList = (List<ObservacionCaracteristica>) em.createQuery(query,ObservacionCaracteristica.class).getResultList();

			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}

	public List<ObservacionCaracteristica> findByObservacionCaracteristica_Caracteristica_Fenomeno_IdFenomeno(final int idFenomeno) throws PersistenciaException {
		try {
			String query = "select oC from ObservacionCaracteristica oC where oC.caractersitica.fenomeno.fenomenoid = '"+idFenomeno+"'";

			List<ObservacionCaracteristica> resultList = (List<ObservacionCaracteristica>) em.createQuery(query,ObservacionCaracteristica.class).getResultList();

			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}

}