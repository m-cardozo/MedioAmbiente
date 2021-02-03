package com.webServices;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.entidades.Fenomeno;
import com.entidades.Usuario;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaUsuarioBean;
import com.modelo.UsuarioGui;

@Stateless
@LocalBean
public class UsuarioRest implements IUsuarioRest{

	@EJB
	private PersistenciaUsuarioBean persistenciaUsuarioBean;

	@Override
	public String echo() {
		return "Servicio Usuarios Disponible";
	}

	@Override
	public Response getUsuarios() {
		try {
			List<Usuario> list = persistenciaUsuarioBean.getUsuarios();

			List<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
			
			for (Usuario usuario : list) {
				ArrayList <String> usuarios = new ArrayList<String>();
				
				usuarios.add(usuario.getUsuarioId().toString());
				usuarios.add(usuario.getUsuario().toString());
				

				
				lista.add(usuarios);
			
				}
				
				return Response.ok().entity(lista).build();
		} 
	
	catch(Exception e) {
		e.printStackTrace();
		return Response.serverError().build();
		}
	}
	@Override
	public Response add(Usuario usuario) {
		try {
			UsuarioGui ret = persistenciaUsuarioBean.agregarUsuarioGui(persistenciaUsuarioBean.fromUsuario(usuario));

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response remove(Usuario usuario) {
		try {
			persistenciaUsuarioBean.borrarUsuarioGui(persistenciaUsuarioBean.fromUsuario(usuario));

			return Response.ok().entity(usuario).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response edit(Usuario usuario) {
		try {
			persistenciaUsuarioBean.modificarUsuarioGui(persistenciaUsuarioBean.fromUsuario(usuario));

			return Response.ok().entity(usuario).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response getUsuario(Long id) {

		System.out.println("UsuarioREST - ID: "+id);

		UsuarioGui ret = persistenciaUsuarioBean.buscarUsuario(id);

		return Response.ok().entity(ret).build();
	}
	
	@Override
	public Response login (String usuario, String password) {
		
		System.out.println("Login: "+ usuario + "  " + password);

		Boolean ret = persistenciaUsuarioBean.login(usuario, password);
		
		System.out.println("Login: "+ ret);
		return Response.ok().entity(ret).build();
	}
	
	
}