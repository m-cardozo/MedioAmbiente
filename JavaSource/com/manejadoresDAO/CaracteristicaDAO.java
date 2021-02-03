package com.manejadoresDAO;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entidades.Caracteristica;
import com.excepciones.PersistenciaException;

@Stateless
@LocalBean
public class CaracteristicaDAO {

	@PersistenceContext
	private EntityManager em;

	public CaracteristicaDAO() {
		super();
	}

	public Caracteristica agregarCaracteristica(Caracteristica _caracteristica) throws PersistenciaException {

		try {
			Caracteristica caracteristica = em.merge(_caracteristica);
			em.flush();
			return caracteristica;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo agregar la caracteristica." + e.getMessage(), e);
		}
		finally { }
	}

	public Caracteristica borrarCaracteristica(Caracteristica _caracteristica) throws PersistenciaException {

		Caracteristica caracteristica = em.find(Caracteristica.class, _caracteristica.getCaracteristicaId());
		if (caracteristica == null) {
			throw new PersistenciaException("No existe la caracteristica de id: " + _caracteristica.getCaracteristicaId());
		}
		try {
			em.remove(caracteristica);
			em.flush();
			return caracteristica;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo borrar la caracteristica de id: " + caracteristica.getCaracteristicaId());
		}
	}

	public Caracteristica modificarCaracteristica(Caracteristica _caracteristica) throws PersistenciaException {

		try {
			Caracteristica caracteristica = em.merge(_caracteristica);
			em.flush();
			return caracteristica;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo modificar la caracteristica." + e.getMessage(), e);
		}
	}

	public Caracteristica buscarCaracteristica(Long id) {
		Caracteristica caracteristica = em.find(Caracteristica.class, id);
		return caracteristica;
	}

	public List<Caracteristica> buscarCaracteristicas() throws PersistenciaException {
		
		try {
			String query= 	"Select c from Caracteristica c";
			List<Caracteristica> resultList = (List<Caracteristica>) em.createQuery(query,Caracteristica.class).getResultList();
			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}

	public List<Caracteristica> seleccionarCaracteristicas(String criterioNombre, String criterioEtiqueta) throws PersistenciaException {
		
		try {
			String query= 	"Select c from Caracteristica c";
			String queryCriterio = "";
			if (criterioNombre != null && !criterioNombre.contentEquals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " c.nombre like '%" + criterioNombre + "%'";
			} 
			if (criterioEtiqueta != null && !criterioEtiqueta.equals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " c.etiqueta like '%" + criterioEtiqueta + "%'";
			}
			if (!queryCriterio.contentEquals("")) {
				query += " where " + queryCriterio;
			}
			List<Caracteristica> resultList = (List<Caracteristica>) em.createQuery(query, Caracteristica.class).getResultList();
			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(), e);
		}
	}
}