package com.manejadoresDAO;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entidades.TipoDocumento;
import com.excepciones.PersistenciaException;

@Stateless
@LocalBean
public class TipoDocumentoDAO {

	@PersistenceContext
	private EntityManager em;

	public TipoDocumentoDAO() {
		super();
	}

	public TipoDocumento agregarTipoDocumento(TipoDocumento _tipoDocumento) throws PersistenciaException {

		try {
			TipoDocumento tipoDocumento = em.merge(_tipoDocumento);
			em.flush();
			return tipoDocumento;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo agregar el tipo documento." + e.getMessage(), e);
		}
		finally { }
	}

	public TipoDocumento borrarTipoDocumento(TipoDocumento _tipoDocumento) throws PersistenciaException {

		TipoDocumento tipoDocumento = em.find(TipoDocumento.class, _tipoDocumento.getTipoDocumentoId());
		if (tipoDocumento == null) {
			throw new PersistenciaException("No existe el tipo documento de id: " + _tipoDocumento.getTipoDocumentoId());
		}
		try {
			em.remove(tipoDocumento);
			em.flush();
			return _tipoDocumento;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo borrar el tipo documento de id: " + _tipoDocumento.getTipoDocumentoId());
		}
	}

	public TipoDocumento modificarTipoDocumento(TipoDocumento _tipoDocumento) throws PersistenciaException {

		try {
			TipoDocumento tipoDocumento = em.merge(_tipoDocumento);
			em.flush();
			return tipoDocumento;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo modificar el tipo documento." + e.getMessage(), e);
		}
	}

	public TipoDocumento buscarTipoDocumento(Long id) {
		TipoDocumento tipoDocumento = em.find(TipoDocumento.class, id);
		return tipoDocumento;
	}

	public List<TipoDocumento> buscarTiposDocumento() throws PersistenciaException {
		
		try {
			String query= 	"Select tD from TipoDocumento tD";
			List<TipoDocumento> resultList = (List<TipoDocumento>) em.createQuery(query, TipoDocumento.class).getResultList();
			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}

	public List<TipoDocumento> seleccionarTiposDocumento(String criterioDescripcion) throws PersistenciaException {
		
		try {
			String query = "Select tD from TipoDocumento tD";
			String queryCriterio = "";
			if (criterioDescripcion != null && !criterioDescripcion.contentEquals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " tD.descripcion like '%" + criterioDescripcion +"%'";
			}
			if (!queryCriterio.contentEquals("")) {
				query += " where " + queryCriterio;
			}
			List<TipoDocumento> resultList = (List<TipoDocumento>) em.createQuery(query, TipoDocumento.class).getResultList();
			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}
}