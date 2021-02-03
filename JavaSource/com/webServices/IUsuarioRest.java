package com.webServices;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.entidades.Usuario;

@Path("/usuarios")
public interface IUsuarioRest {

	@GET
	@Path("echo")
	@Produces({MediaType.TEXT_PLAIN})
	public String echo();

	@GET
	@Path("usuarios")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuarios();

	@POST
	@Path("agregar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Usuario usuario);

	@DELETE
	@Path("eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remove(Usuario usuario);

	@PUT
	@Path("editar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response edit(Usuario usuario);

	@GET
	@Path("usuario/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUsuario(@PathParam("id") Long id);
	
	@GET
	@Path("login/{usuario}/{password}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response login(@PathParam("usuario") String usuario, @PathParam("password") String password);
	
	
}