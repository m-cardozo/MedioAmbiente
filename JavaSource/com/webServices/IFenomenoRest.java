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



@Path("/fenomenos")
public interface IFenomenoRest {

	@GET
	@Path("echo")
	@Produces({MediaType.TEXT_PLAIN})
	public String echo();

	@GET
	@Path("fenomenos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerFenomenos();
	
	@GET
	@Path("fenomeno/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getFenomeno(@PathParam("id") Long id);
}
