package com.manejadoresDAO;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entidades.ObservacionImagen;
import com.entidades.ObservacionRevision;
import com.entidades.ObservacionRevisionPK;
import com.excepciones.PersistenciaException;

@Stateless
@LocalBean
public class ObservacionRevisionDAO {

	@PersistenceContext
	private EntityManager em;

	public ObservacionRevisionDAO() {
		super();
	}

	public ObservacionRevision agregarObservacionRevision(ObservacionRevision oR) throws PersistenciaException {
		try {
			ObservacionRevision oRAgregada = em.merge(oR);
			em.flush();

			return oRAgregada;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo agregar la Observacion Revision." + e.getMessage(), e);
		}
		finally {}
	}

	public ObservacionRevision borrarObservacionRevision(ObservacionRevision oR) throws PersistenciaException {
		ObservacionRevision oRABorrar = em.find(ObservacionRevision.class, oR.getId());

		if (oRABorrar == null) {
			throw new PersistenciaException("No existe la Observacion Revision de id " + oR.getId().getObservacionId()+" - "+ oR.getId().getUsuarioId());
		}

		try {
			em.remove(oRABorrar);
			em.flush();

			return oR;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo borrar la Observacion Revision de id " + oR.getId().getObservacionId()+" - "+ oR.getId().getUsuarioId());
		}
	}

	public ObservacionRevision modificarObservacionRevision(ObservacionRevision oR) throws PersistenciaException {
		try {
			ObservacionRevision oRAModificar = em.merge(oR);
			em.flush();

			return oRAModificar;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo modificar la Observacion Revision." + e.getMessage(), e);
		}
	}

	public ObservacionRevision buscarObservacionRevision(ObservacionRevisionPK oRPK) {
		ObservacionRevision oR = em.find(ObservacionRevision.class, oRPK);

		return oR;
	}

	public List<ObservacionRevision> buscarObservacionRevisiones() throws PersistenciaException {
		try {
			String query= 	"Select oR from ObservacionRevision oR";

			List<ObservacionRevision> resultList = (List<ObservacionRevision>) em.createQuery(query,ObservacionRevision.class).getResultList();

			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}

	public List<ObservacionRevision> seleccionarObservacionesRevisiones(String criterioIdObservacion, String criterioIdUsuario) throws PersistenciaException {
		try {
			String query = "Select oR from ObservacionRevision oR ";
			String queryCriterio = "";

			if (criterioIdObservacion!=null && ! criterioIdObservacion.contentEquals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " oR.id_observacion = '" + criterioIdObservacion + "' ";
			}

			if (criterioIdUsuario!=null && ! criterioIdUsuario.contentEquals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " oR.id_usuario = '" + criterioIdUsuario + "' ";
			} 

			if (!queryCriterio.contentEquals("")) {
				query += " where " + queryCriterio;
			}

			List<ObservacionRevision> resultList = (List<ObservacionRevision>) em.createQuery(query,ObservacionRevision.class).getResultList();

			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}

	public List<ObservacionRevision> getObservacionesRevisiones() throws PersistenciaException {
		try {
			String query = "select oR from ObservacionRevision oR";

			List<ObservacionRevision> resultList = (List<ObservacionRevision>) em.createQuery(query,ObservacionRevision.class).getResultList();

			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}

	public List<ObservacionRevision> seleccionarObservacionRevisiones(String criterioObservacion) throws PersistenciaException {
		try {
			String query = "Select o from ObservacionRevision o";
			String queryCriterio = "";

			if (criterioObservacion!=null && ! criterioObservacion.contentEquals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " o.id.observacionId = '" + criterioObservacion.trim() + "'";
			}
			if (!queryCriterio.contentEquals("")) {
				query += " where " + queryCriterio;
			}
			List<ObservacionRevision> resultList = (List<ObservacionRevision>) em.createQuery(query, ObservacionRevision.class).getResultList();

			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}
}