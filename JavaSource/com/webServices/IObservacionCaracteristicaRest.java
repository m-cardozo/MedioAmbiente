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

import com.entidades.ObservacionCaracteristica;
import com.entidades.ObservacionCaracteristicaPK;

@Path("/observacionesCaracteristicas")
public interface IObservacionCaracteristicaRest {

	@GET
	@Path("echo")
	@Produces({MediaType.TEXT_PLAIN})
	public String echo();

	@GET
	@Path("observacionesCaracteristicas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getObservacionesCaracteristicas();

	@POST
	@Path("agregar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(ObservacionCaracteristica observacionCaracteristica);

	@DELETE
	@Path("eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remove(ObservacionCaracteristica observacionCaracteristica);

	@PUT
	@Path("editar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response edit(ObservacionCaracteristica observacionCaracteristica);

/*	@GET
	@Path("observacionCaracteristica/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getObservacionCaracteristica(@PathParam("id") ObservacionCaracteristicaPK id); */
}