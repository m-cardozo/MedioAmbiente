package com.manejadoresDAO;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entidades.Zona;
import com.excepciones.PersistenciaException;

@Stateless
@LocalBean
public class ZonaDAO {

	@PersistenceContext
	private EntityManager em;

	public ZonaDAO() {
		super();
	}

	public Zona agregarZona(Zona _zona) throws PersistenciaException {

		try {
			Zona zona = em.merge(_zona);
			em.flush();
			return zona;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo agregar la zona." + e.getMessage(), e);
		}
		finally { }
	}

	public Zona borrarZona(Zona _zona) throws PersistenciaException {

		Zona zona = em.find(Zona.class, _zona.getZonaId());
		if (zona == null) {
			throw new PersistenciaException("No existe la zona de id: " + _zona.getZonaId());
		}
		try {
			em.remove(zona);
			em.flush();
			return _zona;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo borrar la zona de id: " + _zona.getZonaId());
		}
	}

	public Zona modificarZona(Zona _zona) throws PersistenciaException {

		try {
			Zona zona = em.merge(_zona);
			em.flush();
			return zona;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo modificar la zona." + e.getMessage(), e);
		}
	}

	public Zona buscarZona(Long id) {
		Zona zona = em.find(Zona.class, id);
		return zona;
	}

	public List<Zona> buscarZonas() throws PersistenciaException {
		
		try {
			String query= 	"Select z from Zona z";
			List<Zona> resultList = (List<Zona>) em.createQuery(query, Zona.class).getResultList();
			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}

	public List<Zona> seleccionarZonas(String criterioDescripcion) throws PersistenciaException {
		
		try {
			String query = "Select z from Zona z";
			String queryCriterio = "";
			if (criterioDescripcion != null && !criterioDescripcion.contentEquals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " z.descripcion like '%" + criterioDescripcion +"%'";
			}
			if (!queryCriterio.contentEquals("")) {
				query += " where " + queryCriterio;
			}
			List<Zona> resultList = (List<Zona>) em.createQuery(query, Zona.class).getResultList();
			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}
}