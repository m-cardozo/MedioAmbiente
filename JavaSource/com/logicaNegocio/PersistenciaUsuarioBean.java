package com.logicaNegocio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.entidades.Usuario;
import com.excepciones.PersistenciaException;
import com.manejadoresDAO.UsuarioDAO;
import com.modelo.UsuarioGui;
import com.utils.ConexionLDAP;

@Named(value="persistenciaUsuario")
@Stateless
@LocalBean
public class PersistenciaUsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	UsuarioDAO usuarioPersistenciaDAO;

	@EJB
	PersistenciaTipoDocumentoBean persistenciaTDB;

	@EJB
	PersistenciaRolBean persistenciaRB;

	public PersistenciaUsuarioBean() {
		super();
	}

	public UsuarioGui fromUsuario(Usuario u) {
		UsuarioGui uG = new UsuarioGui();

		if(u.getUsuarioId() != null) {
			uG.setUsuarioId(u.getUsuarioId().longValue());
		}

		uG.setNombre(u.getNombre());
		uG.setApellido(u.getApellido());
		uG.setUsuario(u.getUsuario());
		uG.setClave(u.getClave());
		uG.setTipoDocumentoGui(persistenciaTDB.fromTipoDocumento(u.getTipoDocumento()));
		uG.setNroDocumento(u.getNroDocumento());
		uG.setDireccion(u.getDireccion());
		uG.setMail(u.getMail());
		uG.setRolGui(persistenciaRB.fromRol(u.getRol()));
		uG.setEstado(u.getEstado());

		return uG;
	}

	public Usuario toUsuario(UsuarioGui uG) {
		Usuario usuario = new Usuario();
		usuario.setUsuarioId(uG.getUsuarioId() != null ? uG.getUsuarioId().longValue() : null);
		usuario.setNombre(uG.getNombre());
		usuario.setApellido(uG.getApellido());
		usuario.setUsuario(uG.getUsuario());
		usuario.setClave(uG.getClave());
		usuario.setTipoDocumento(persistenciaTDB.toTipoDocumento(uG.getTipoDocumentoGui()));
		usuario.setNroDocumento(uG.getNroDocumento());
		usuario.setDireccion(uG.getDireccion());
		usuario.setMail(uG.getMail());
		usuario.setRol(persistenciaRB.toRol(uG.getRolGui()));
		usuario.setEstado(uG.getEstado());

		return usuario;
	}

	public List<Usuario> seleccionarUsuarios(String criterioUsuario, String criterioDocumento) throws PersistenciaException {
		return usuarioPersistenciaDAO.seleccionarUsuarios(criterioUsuario, criterioDocumento);
	}

	public UsuarioGui buscarUsuario(Long id) {
		Usuario u = usuarioPersistenciaDAO.buscarUsuario(id);

		return fromUsuario(u);
	}

	public UsuarioGui buscarUsuarioGui(Long id) {
		Usuario u = usuarioPersistenciaDAO.buscarUsuario(id);

		return fromUsuario(u);
	}

	public UsuarioGui agregarUsuarioGui(UsuarioGui usuarioGuiSeleccionado) throws PersistenciaException   {
		Usuario u = usuarioPersistenciaDAO.agregarUsuario(toUsuario(usuarioGuiSeleccionado));

		return fromUsuario(u);
	}

	public void modificarUsuarioGui(UsuarioGui usuarioGuiSeleccionado) throws PersistenciaException   {
		usuarioPersistenciaDAO.modificarUsuario(toUsuario(usuarioGuiSeleccionado));
	}

	public void borrarUsuarioGui(UsuarioGui usuarioGuiSeleccionado) throws PersistenciaException   {
		usuarioPersistenciaDAO.borrarUsuario(toUsuario(usuarioGuiSeleccionado));
	}

	public List<Usuario> getUsuarios() throws PersistenciaException {
		return usuarioPersistenciaDAO.buscarUsuarios();
	}

	public boolean login(String usuario, String password) {
		boolean res = false;

		try {
			if(usuario.contains("@")) {
				res = ConexionLDAP.AutenticarAPP(usuario, password);
			}else {
				res = usuarioPersistenciaDAO.existeUsuario(usuario, password);
			}
		} catch (PersistenciaException e) {
			e.printStackTrace();
			res = false;
		}		

		return res;
	}
}