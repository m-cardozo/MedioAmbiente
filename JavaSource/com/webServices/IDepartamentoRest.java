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

import com.entidades.Departamento;

@Path("/departamentos")
public interface IDepartamentoRest {

	@GET
	@Path("echo")
	@Produces({MediaType.TEXT_PLAIN})
	public String echo();

	@GET
	@Path("departamentos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDepartamentos();

	@POST
	@Path("agregar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Departamento departamento);

	@DELETE
	@Path("eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remove(Departamento departamento);

	@PUT
	@Path("editar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response edit(Departamento departamento);

	@GET
	@Path("departamento/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getDepartamento(@PathParam("id") Long id);
}