package com.manejadoresDAO;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entidades.Localidad;
import com.excepciones.PersistenciaException;

@Stateless
@LocalBean
public class LocalidadDAO {

	@PersistenceContext
	private EntityManager em;

	public LocalidadDAO() {
		super();
	}

	public Localidad agregarLocalidad(Localidad _localidad) throws PersistenciaException {

		try {
			Localidad localidad = em.merge(_localidad);
			em.flush();
			return localidad;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo agregar la localidad." + e.getMessage(), e);
		}
		finally { }
	}

	public Localidad borrarLocalidad(Localidad _localidad) throws PersistenciaException {

		Localidad localidad = em.find(Localidad.class, _localidad.getLocalidadId());
		if (localidad == null) {
			throw new PersistenciaException("No existe la localidad de id: " + _localidad.getLocalidadId());
		}
		try {
			em.remove(localidad);
			em.flush();
			return _localidad;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo borrar la localidad de id: " + _localidad.getLocalidadId());
		}
	}

	public Localidad modificarLocalidad(Localidad _localidad) throws PersistenciaException {

		try {
			Localidad localidad = em.merge(_localidad);
			em.flush();
			return localidad;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo modificar la localidad." + e.getMessage(), e);
		}
	}

	public Localidad buscarLocalidad(Long id) {
		Localidad localidad = em.find(Localidad.class, id);
		return localidad;
	}

	public List<Localidad> buscarLocalidades() throws PersistenciaException {
		
		try {
			String query= 	"Select l from Localidad l";
			List<Localidad> resultList = (List<Localidad>) em.createQuery(query, Localidad.class).getResultList();
			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}

	public List<Localidad> seleccionarLocalidades(String criterioDescripcion) throws PersistenciaException {
		
		try {
			String query = "Select l from Localidad l";
			String queryCriterio = "";
			if (criterioDescripcion != null && !criterioDescripcion.contentEquals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " l.descripcion like '%" + criterioDescripcion +"%'";
			}
			if (!queryCriterio.contentEquals("")) {
				query += " where " + queryCriterio;
			}
			List<Localidad> resultList = (List<Localidad>) em.createQuery(query, Localidad.class).getResultList();
			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}
}