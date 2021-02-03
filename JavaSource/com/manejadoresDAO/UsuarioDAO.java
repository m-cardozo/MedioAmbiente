package com.manejadoresDAO;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entidades.Usuario;
import com.excepciones.PersistenciaException;

@Stateless
@LocalBean
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager em;

	public UsuarioDAO() {
		super();
	}

	public Usuario agregarUsuario(Usuario _usuario) throws PersistenciaException {

		try {
			Usuario usuario = em.merge(_usuario);
			em.flush();
			return usuario;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo agregar el usuario." + e.getMessage(), e);
		}
		finally { }
	}

	public Usuario borrarUsuario(Usuario _usuario) throws PersistenciaException {

		Usuario usuario = em.find(Usuario.class, _usuario.getUsuarioId());
		if (usuario == null) {
			throw new PersistenciaException("No existe el usuario de id: " + _usuario.getUsuarioId());
		}
		try {
			em.remove(usuario);
			em.flush();
			return _usuario;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo borrar el usuario de id: " + usuario.getUsuarioId());
		}
	}

	public Usuario modificarUsuario(Usuario _usuario) throws PersistenciaException {

		try {
			Usuario usuario = em.merge(_usuario);
			em.flush();
			return usuario;
		} catch (PersistenceException e) {
			throw new PersistenciaException("No se pudo modificar el usuario." + e.getMessage(), e);
		}
	}

	public Usuario buscarUsuario(Long id) {
		Usuario usuario = em.find(Usuario.class, id);
		return usuario;
	}
	
	public List<Usuario> buscarUsuarios() throws PersistenciaException {

		try {
			String query= 	"Select u from Usuario u";
			List<Usuario> resultList = (List<Usuario>) em.createQuery(query,Usuario.class).getResultList();
			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		}
	}

	public List<Usuario> seleccionarUsuarios(String criterioUsuario, String criterioDocumento) throws PersistenciaException {

		try {
			String query= 	"Select u from Usuario u";
			String queryCriterio = "";
			if (criterioUsuario != null && !criterioUsuario.contentEquals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " u.usuario like '%" + criterioUsuario + "%'";
			} 
			if (criterioDocumento != null && !criterioDocumento.equals("")) {
				queryCriterio += (!queryCriterio.isEmpty() ? " and " : "") + " u.nroDocumento like '%" + criterioDocumento + "%'";
			}
			if (!queryCriterio.contentEquals("")) {
				query += " where " + queryCriterio;
			}
			List<Usuario> resultList = (List<Usuario>) em.createQuery(query, Usuario.class).getResultList();
			return  resultList;
		} catch(PersistenceException e) {
			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(), e);
		}
	}

	public Usuario actualizarSinClave(Usuario _usuario) throws PersistenciaException {

		try{
			@SuppressWarnings("unused")
			int res=em.createQuery("update Usuario set id_rol = ?1, nombre= ?2 , apellido = ?3, mail = ?4 WHERE id= ?5")
			.setParameter(1, _usuario.getRol())
			.setParameter(2, _usuario.getNombre())
			.setParameter(3, _usuario.getApellido())
			.setParameter(4, _usuario.getMail())
			.setParameter(5, _usuario.getUsuarioId())
			.executeUpdate();
			em.flush();

			Usuario usuario = em.find(Usuario.class, _usuario.getUsuarioId());

			return usuario;
		} catch(PersistenceException e){
			throw new PersistenciaException("No se pudo actualizar el usuario.");
		}
	}

	public boolean existeUsuario(String usuario) throws PersistenciaException {
		boolean esta = false;
		TypedQuery<Usuario> usuarios = null;

		try {
			usuarios = em.createQuery("Select u from Usuario u where upper(trim(u.usuario)) = upper(trim(:usuario))", Usuario.class).setParameter("usuario", usuario);

			int cantidad = usuarios.getResultList().size();

			if (cantidad > 0) {
				esta = true;
			} else {
				esta = false;
			}
		} catch(PersistenceException e){
			esta = false;

			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		} finally{ }

		return esta;
	}
	
	public boolean existeUsuario(String usuario, String clave) throws PersistenciaException {
		boolean esta = false;		
		TypedQuery<Usuario> usuarios = null;

		try {
			usuarios = em.createQuery("select u from Usuario u where u.usuario = :usuario and u.clave = :clave", Usuario.class).setParameter("usuario", usuario).setParameter("clave", clave);

			int cantidad = usuarios.getResultList().size();

			if (cantidad > 0)	{
				esta = true;
			} else {
				esta = false;
			}
		} catch(PersistenceException e){
			esta = false;

			throw new PersistenciaException("No se pudo realizar la consulta." + e.getMessage(),e);
		} finally{ }

		return esta;
	}

	public boolean cambiarClave(Long id, String clave) throws PersistenciaException {		
		boolean cambio = false;
		int res = 0;

		try {
			res = em.createNativeQuery("update Usuario set clave = '" + clave + "' where id = " + String.valueOf(id)).executeUpdate();

			if(res > 0) {
				cambio = true;
			}
		} catch(PersistenceException e){
			cambio = false;

			throw new PersistenciaException("No se pudo cambiar la clave." + e.getMessage(), e);
		} finally{ }

		return cambio;
	}
}