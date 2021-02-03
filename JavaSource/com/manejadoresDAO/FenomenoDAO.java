package com.manejadoresDAO;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entidades.Fenomeno;
import com.excepciones.PersistenciaException;

@Stateless
@LocalBean
public class FenomenoDAO {

	@PersistenceContext
	private EntityManager em;

	public FenomenoDAO() {
		super();
	}

	public Fenomeno agregarFenomeno(Fenomeno _fenomeno) throws PersistenciaException {

		try {
			Fenomeno fenomeno = em.merge(_fenomeno);
			em.flush();
			return fenomeno;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo agregar el fenomeno." + e.getMessage(), e);
		}
		finally { }
	}

	public Fenomeno borrarFenomeno(Fenomeno _fenomeno) throws PersistenciaException {

		Fenomeno fenomeno = em.find(Fenomeno.class, _fenomeno.getFenomenoId());
		if (fenomeno == null) {
			throw new PersistenciaException("No existe el fenomeno de id: " + _fenomeno.getFenomenoId());
		}
		try {
			em.remove(fenomeno);
			em.flush();
			return fenomeno;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo borrar el fenomeno de id: " + fenomeno.getFenomenoId());
		}
	}

	public Fenomeno modificarFenomeno(Fenomeno _fenomeno) throws PersistenciaException {

		try {
			Fenomeno fenomeno = em.merge(_fenomeno);
			em.flush();
			return fenomeno;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo modificar el fenomeno." + e.getMessage(), e);
		}
	}

	public Fenomeno buscarFenomeno(Long id) {
		Fenomeno fenomeno = em.find(Fenomeno.class, id);
		return fenomeno;
	}

	public List<Fenomeno> buscarFenomenos() throws PersistenciaException {
		
		try {
			String query= 	"Select f from Fenomeno f";
			List<Fenomeno> resultList = (List<Fenomeno>) em.createQuery(query, Fenomeno.class).getResultList();
			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}


	public List<Fenomeno> seleccionarFenomenos(String criterioNombre) throws PersistenciaException {
		
		try {
			String query = "Select f from Fenomeno f";
			String queryCriterio = "";
			if (criterioNombre != null && !criterioNombre.contentEquals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " f.nombre like '%" + criterioNombre + "%'";
			} 
			if (!queryCriterio.contentEquals("")) {
				query += " where " + queryCriterio;
			}
			List<Fenomeno> resultList = (List<Fenomeno>) em.createQuery(query,Fenomeno.class).getResultList();
			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realziar la consulta." + e.getMessage(), e);
		}
	}
}