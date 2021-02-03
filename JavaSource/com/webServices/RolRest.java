package com.webServices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.entidades.Rol;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaRolBean;
import com.modelo.RolGui;

@Stateless
@LocalBean
public class RolRest implements IRolRest{

	@EJB
	private PersistenciaRolBean persistenciaRolBean;

	@Override
	public String echo() {
		return "Servicio Roles Disponible";
	}

	@Override
	public Response getRoles() {
		try {
			List<Rol> ret = persistenciaRolBean.getRoles();

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response add(Rol rol) {
		try {
			RolGui ret = persistenciaRolBean.agregarRolGui(persistenciaRolBean.fromRol(rol));

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response remove(Rol rol) {
		try {
			persistenciaRolBean.borrarRolGui(persistenciaRolBean.fromRol(rol));

			return Response.ok().entity(rol).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response edit(Rol rol) {
		try {
			persistenciaRolBean.modificarRolGui(persistenciaRolBean.fromRol(rol));

			return Response.ok().entity(rol).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response getRol(Long id) {

		System.out.println("RolREST - ID: "+id);

		RolGui ret = persistenciaRolBean.buscarRol(id);

		return Response.ok().entity(ret).build();
	}
}