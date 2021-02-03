package com.manejadoresDAO;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entidades.Fenomeno;
import com.entidades.Localidad;
import com.entidades.Observacion;
import com.entidades.Usuario;
import com.excepciones.PersistenciaException;

@Stateless
@LocalBean
public class ObservacionDAO {
	


	@PersistenceContext
	private EntityManager em;

	public ObservacionDAO() {
		super();
	}
	
/*	public Observacion agregarObservacionPrueba (Observacion _observacion) throws PersistenciaException {

		try {
			Observacion observacion = new Observacion ();
			
			Fenomeno fenomeno = em.getReference(Fenomeno.class, 1L);
			observacion.setFenomeno(fenomeno);	
			
			Usuario usuario = em.getReference(Usuario.class, 1L);
			observacion.setUsuario(usuario);
			
			Localidad localidad = em.getReference(Localidad.class, 1L);
			observacion.setLocalidad(localidad);
			
			observacion = em.merge(observacion);
			em.flush();
			return observacion;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo agregar la observacion." + e.getMessage(), e);
		}
		finally { }
	}*/

	public Observacion agregarObservacion(Observacion _observacion) throws PersistenciaException {

		try {
			Observacion observacion = em.merge(_observacion);
			em.flush();
			return observacion;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo agregar la observacion." + e.getMessage(), e);
		}
		finally { }
	}

	public Observacion borrarObservacion(Observacion _observacion) throws PersistenciaException {

		Observacion observacion = em.find(Observacion.class, _observacion.getObservacionId());
		if (observacion == null) {
			throw new PersistenciaException("No existe la observacion de id: " + _observacion.getObservacionId());
		}
		try {
			em.remove(observacion);
			em.flush();
			return observacion;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo borrar la observacion de id: " + observacion.getObservacionId());
		}
	}

	public Observacion modificarObservacion(Observacion _observacion) throws PersistenciaException {

		try {
			Observacion observacion = em.merge(_observacion);
			em.flush();
			return observacion;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo modificar la observacion." + e.getMessage(), e);
		}
	}

	public Observacion borrarPorId(Long id) throws PersistenciaException {
		Observacion observacion = em.find(Observacion.class, id);
		if (observacion == null) {
			throw new PersistenciaException("No existe la observacion " );
		}
		try {
			em.remove(observacion);
			em.flush();
			return observacion;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo borrar la observacion de id: " + observacion.getObservacionId());
		}
	}
		
	
	
	public Observacion buscarObservacion(Long id) {
		Observacion observacion = em.find(Observacion.class, id);
		return observacion;
	}

	public List<Observacion> buscarObservaciones() throws PersistenciaException {
		
		try {
			String query= 	"Select o from Observacion o order by o.observacionId desc ";
			List<Observacion> resultList = (List<Observacion>) em.createQuery(query, Observacion.class).getResultList();
			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}


	public List<Observacion> seleccionarObservaciones(String criterioDescripcion) throws PersistenciaException {
		
		try {
			String query = "Select o from Observacion o";
			String queryCriterio = "";
			if (criterioDescripcion != null && !criterioDescripcion.contentEquals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " o.descripcion like '%" + criterioDescripcion + "%'";
			} 
			if (!queryCriterio.contentEquals("")) {
				query += " where " + queryCriterio;
			}
			List<Observacion> resultList = (List<Observacion>) em.createQuery(query,Observacion.class).getResultList();
			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realziar la consulta." + e.getMessage(), e);
		}
	}
}