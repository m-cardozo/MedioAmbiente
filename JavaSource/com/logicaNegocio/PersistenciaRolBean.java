package com.logicaNegocio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.entidades.Rol;
import com.excepciones.PersistenciaException;
import com.manejadoresDAO.RolDAO;
import com.modelo.RolGui;

@Named(value="persistenciaRol")
@Stateless
@LocalBean
public class PersistenciaRolBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	RolDAO rolPersistenciaDAO;

	public PersistenciaRolBean() {
		super();
	}

	public RolGui fromRol(Rol r) {
		RolGui rG = new RolGui();

		if(r.getRolId() != null) {
			rG.setRolId(r.getRolId().longValue());
		}

		rG.setDescripcion(r.getDescripcion());

		return rG;
	}

	public Rol toRol(RolGui rG) {
		Rol rol = new Rol();
		rol.setRolId(rG.getRolId() != null ? rG.getRolId().longValue() : null);
		rol.setDescripcion(rG.getDescripcion());

		return rol;
	}

	public List<Rol> seleccionarRoles(String criterioDescripcion) throws PersistenciaException {
		return rolPersistenciaDAO.seleccionarRoles(criterioDescripcion);
	}

	public RolGui buscarRol(Long id) {
		Rol r = rolPersistenciaDAO.buscarRol(id);

		return fromRol(r);
	}

	public RolGui buscarRolGui(Long id) {
		Rol r = rolPersistenciaDAO.buscarRol(id);

		return fromRol(r);
	}

	public RolGui agregarRolGui(RolGui rolGuiSeleccionado) throws PersistenciaException   {
		Rol r = rolPersistenciaDAO.agregarRol(toRol(rolGuiSeleccionado));

		return fromRol(r);
	}

	public void modificarRolGui(RolGui rolGuiSeleccionado) throws PersistenciaException   {
		rolPersistenciaDAO.modificarRol(toRol(rolGuiSeleccionado));
	}

	public void borrarRolGui(RolGui rolGuiSeleccionado) throws PersistenciaException   {
		rolPersistenciaDAO.borrarRol(toRol(rolGuiSeleccionado));
	}

	public List<Rol> getRoles() throws PersistenciaException {
		return rolPersistenciaDAO.buscarRoles();
	}
}