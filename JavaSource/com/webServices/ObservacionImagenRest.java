package com.webServices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.entidades.ObservacionImagen;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaObservacionImagenBean;
import com.modelo.ObservacionImagenGui;

@Stateless
@LocalBean
public class ObservacionImagenRest implements IObservacionImagenRest{

	@EJB
	private PersistenciaObservacionImagenBean persistenciaObservacionImagenBean;

	@Override
	public String echo() {
		return "Servicio ObservacionesImagenes Disponible";
	}

	@Override
	public Response getObservacionesImagenes() {
		try {
			List<ObservacionImagen> ret = persistenciaObservacionImagenBean.getObservacionImagenes();

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response add(ObservacionImagen observacionImagen) {
		try {
			ObservacionImagenGui ret = persistenciaObservacionImagenBean.agregarObservacionImagenGui(persistenciaObservacionImagenBean.fromObservacionImagen(observacionImagen));

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response remove(ObservacionImagen observacionImagen) {
		try {
			persistenciaObservacionImagenBean.borrarObservacionImagenGui(persistenciaObservacionImagenBean.fromObservacionImagen(observacionImagen));

			return Response.ok().entity(observacionImagen).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response edit(ObservacionImagen observacionImagen) {
		try {
			persistenciaObservacionImagenBean.modificarObservacionImagenGui(persistenciaObservacionImagenBean.fromObservacionImagen(observacionImagen));

			return Response.ok().entity(observacionImagen).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

/*	@Override
	public Response getObservacionImagen(Long id) {

		System.out.println("ObservacionImagenREST - ID: " + id);

		ObservacionImagenGui ret = persistenciaObservacionImagenBean.buscarObservacionImagen(id);

		return Response.ok().entity(ret).build();
	} */
}