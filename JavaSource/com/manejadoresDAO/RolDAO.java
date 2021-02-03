package com.manejadoresDAO;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entidades.Rol;
import com.excepciones.PersistenciaException;

@Stateless
@LocalBean
public class RolDAO {

	@PersistenceContext
	private EntityManager em;

	public RolDAO() {
		super();
	}

	public Rol agregarRol(Rol _rol) throws PersistenciaException {

		try {
			Rol rol = em.merge(_rol);
			em.flush();
			return rol;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo agregar el rol." + e.getMessage(), e);
		}
		finally { }
	}

	public Rol borrarRol(Rol _rol) throws PersistenciaException {

		Rol rol = em.find(Rol.class, _rol.getRolId());
		if (rol == null) {
			throw new PersistenciaException("No existe el rol de id: " + _rol.getRolId());
		}
		try {
			em.remove(rol);
			em.flush();
			return _rol;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo borrar el rol de id: " + _rol.getRolId());
		}
	}

	public Rol modificarRol(Rol _rol) throws PersistenciaException {

		try {
			Rol rol = em.merge(_rol);
			em.flush();
			return rol;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo modificar el rol." + e.getMessage(), e);
		}
	}

	public Rol buscarRol(Long id) {
		Rol rol = em.find(Rol.class, id);
		return rol;
	}

	public List<Rol> buscarRoles() throws PersistenciaException {
		
		try {
			String query= 	"Select r from Rol r";
			List<Rol> resultList = (List<Rol>) em.createQuery(query, Rol.class).getResultList();
			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}

	public List<Rol> seleccionarRoles(String criterioDescripcion) throws PersistenciaException {
		
		try {
			String query = "Select r from Rol r";
			String queryCriterio = "";
			if (criterioDescripcion != null && !criterioDescripcion.contentEquals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " r.descripcion like '%" + criterioDescripcion +"%'";
			}
			if (!queryCriterio.contentEquals("")) {
				query += " where " + queryCriterio;
			}
			List<Rol> resultList = (List<Rol>) em.createQuery(query, Rol.class).getResultList();
			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}
}