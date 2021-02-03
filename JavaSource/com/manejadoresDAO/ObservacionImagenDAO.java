package com.manejadoresDAO;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entidades.ObservacionImagen;
import com.excepciones.PersistenciaException;

@Stateless
@LocalBean
public class ObservacionImagenDAO {

	@PersistenceContext
	private EntityManager em;

	public ObservacionImagenDAO() {
		super();
	}

	public ObservacionImagen agregarObservacionImagen(ObservacionImagen _observacionImagen) throws PersistenciaException {
		
		try {
			ObservacionImagen observacionImagen = em.merge(_observacionImagen);
			em.flush();

			return observacionImagen;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo agregar la imagen." + e.getMessage(), e);
		}
		finally { }
	}

	public ObservacionImagen borrarObservacionImagen(ObservacionImagen _observacionImagen) throws PersistenciaException {
		ObservacionImagen observacionImagen = em.find(ObservacionImagen.class, _observacionImagen.getObservacionImagenId());

		if (observacionImagen == null) {
			throw new PersistenciaException("No existe la imagen de id: " + _observacionImagen.getObservacionImagenId());
		}

		try {
			em.remove(observacionImagen);
			em.flush();

			return _observacionImagen;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo borrar la imagen de id: " + _observacionImagen.getObservacionImagenId());
		}
	}

	public ObservacionImagen modificarObservacionImagen(ObservacionImagen _observacionImagen) throws PersistenciaException {
		
		try {
			ObservacionImagen observacionImagen = em.merge(_observacionImagen);
			em.flush();

			return observacionImagen;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo modificar la imagen." + e.getMessage(), e);
		}
	}

	public ObservacionImagen buscarObservacionImagen(Long id) {
		ObservacionImagen oI = em.find(ObservacionImagen.class, id);
		return oI;
	}

	public List<ObservacionImagen> buscarObservacionImagenes() throws PersistenciaException {
		try {
			String query = "Select oI from ObservacionImagen oI";

			List<ObservacionImagen> resultList = (List<ObservacionImagen>) em.createQuery(query,ObservacionImagen.class).getResultList();

			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}

	public List<ObservacionImagen> seleccionarObservacionImagenes(String criterioObservacion) throws PersistenciaException {
		try {
			String query = "Select oI from ObservacionImagen oI";
			String queryCriterio = "";

			if (criterioObservacion!=null && ! criterioObservacion.contentEquals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " oI.observacion.observacionId = '" + criterioObservacion + "'";
			}
			if (!queryCriterio.contentEquals("")) {
				query += " where " + queryCriterio;
			}
			List<ObservacionImagen> resultList = (List<ObservacionImagen>) em.createQuery(query, ObservacionImagen.class).getResultList();

			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}
}