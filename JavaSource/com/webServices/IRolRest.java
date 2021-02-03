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

import com.entidades.Rol;

@Path("/roles")
public interface IRolRest {

	@GET
	@Path("echo")
	@Produces({MediaType.TEXT_PLAIN})
	public String echo();

	@GET
	@Path("roles")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRoles();

	@POST
	@Path("agregar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Rol rol);

	@DELETE
	@Path("eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remove(Rol rol);

	@PUT
	@Path("editar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response edit(Rol rol);

	@GET
	@Path("rol/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getRol(@PathParam("id") Long id);
}