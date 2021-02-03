package com.manejadoresDAO;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entidades.Departamento;
import com.excepciones.PersistenciaException;

@Stateless
@LocalBean
public class DepartamentoDAO {

	@PersistenceContext
	private EntityManager em;

	public DepartamentoDAO() {
		super();
	}

	public Departamento agregarDepartamento(Departamento _departamento) throws PersistenciaException {

		try {
			Departamento departamento = em.merge(_departamento);
			em.flush();
			return departamento;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo agregar el departamento." + e.getMessage(), e);
		}
		finally { }
	}

	public Departamento borrarDepartamento(Departamento _departamento) throws PersistenciaException {

		Departamento departamento = em.find(Departamento.class, _departamento.getDepartamentoId());
		if (departamento == null) {
			throw new PersistenciaException("No existe el departamento de id: " + _departamento.getDepartamentoId());
		}
		try {
			em.remove(departamento);
			em.flush();
			return _departamento;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo borrar el departamento de id: " + _departamento.getDepartamentoId());
		}
	}

	public Departamento modificarDepartamento(Departamento _departamento) throws PersistenciaException {

		try {
			Departamento departamento = em.merge(_departamento);
			em.flush();
			return departamento;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo modificar el departamento." + e.getMessage(), e);
		}
	}

	public Departamento buscarDepartamento(Long id) {
		Departamento departamento = em.find(Departamento.class, id);
		return departamento;
	}

	public List<Departamento> buscarDepartamentos() throws PersistenciaException {
		
		try {
			String query= 	"Select d from Departamento d";
			List<Departamento> resultList = (List<Departamento>) em.createQuery(query, Departamento.class).getResultList();
			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}

	public List<Departamento> seleccionarDepartamentos(String criterioDescripcion) throws PersistenciaException {
		
		try {
			String query = "Select d from Departamento d";
			String queryCriterio = "";
			if (criterioDescripcion != null && !criterioDescripcion.contentEquals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " d.descripcion like '%" + criterioDescripcion +"%'";
			}
			if (!queryCriterio.contentEquals("")) {
				query += " where " + queryCriterio;
			}
			List<Departamento> resultList = (List<Departamento>) em.createQuery(query, Departamento.class).getResultList();
			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}
}