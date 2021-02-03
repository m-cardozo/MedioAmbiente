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

import com.entidades.TipoDocumento;

@Path("/tiposDocumento")
public interface ITipoDocumentoRest {

	@GET
	@Path("echo")
	@Produces({MediaType.TEXT_PLAIN})
	public String echo();

	@GET
	@Path("tiposDocumento")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTiposDocumento();

	@POST
	@Path("agregar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(TipoDocumento tipoDocumento);

	@DELETE
	@Path("eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remove(TipoDocumento tipoDocumento);

	@PUT
	@Path("editar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response edit(TipoDocumento tipoDocumento);

	@GET
	@Path("tipoDocumento/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getTipoDocumento(@PathParam("id") Long id);
}