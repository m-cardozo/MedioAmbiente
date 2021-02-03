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

import com.entidades.Zona;

@Path("/zonas")
public interface IZonaRest {

	@GET
	@Path("echo")
	@Produces({MediaType.TEXT_PLAIN})
	public String echo();

	@GET
	@Path("zonas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getZonas();

	@POST
	@Path("agregar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Zona zona);

	@DELETE
	@Path("eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remove(Zona zona);

	@PUT
	@Path("editar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response edit(Zona zona);

	@GET
	@Path("zona/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getZona(@PathParam("id") Long id);
}