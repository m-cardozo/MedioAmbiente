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

import com.entidades.ObservacionImagen;

@Path("/observacionesImagenes")
public interface IObservacionImagenRest {

	@GET
	@Path("echo")
	@Produces({MediaType.TEXT_PLAIN})
	public String echo();

	@GET
	@Path("observacionesImagenes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getObservacionesImagenes();

	@POST
	@Path("agregar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(ObservacionImagen observacionImagen);

	@DELETE
	@Path("eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remove(ObservacionImagen observacionImagen);

	@PUT
	@Path("editar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response edit(ObservacionImagen observacionImagen);

	/*@GET
	@Path("observacionImagen/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getObservacionImagen(@PathParam("id") Long id); */
}