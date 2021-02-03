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

import com.entidades.ObservacionRevision;
import com.entidades.ObservacionRevisionPK;

@Path("/observacionesRevisiones")
public interface IObservacionRevisionRest {

	@GET
	@Path("echo")
	@Produces({MediaType.TEXT_PLAIN})
	public String echo();

	@GET
	@Path("observacionesRevisiones")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getObservacionesRevisiones();

	@POST
	@Path("agregar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(ObservacionRevision observacionRevision);

	@DELETE
	@Path("eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remove(ObservacionRevision observacionRevision);

	@PUT
	@Path("editar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response edit(ObservacionRevision observacionRevision);

/*	@GET
	@Path("observacionRevision/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getObservacionRevision(@PathParam("id") ObservacionRevisionPK id); */
}