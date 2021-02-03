package com.webServices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.entidades.ObservacionCaracteristica;
import com.entidades.ObservacionCaracteristicaPK;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaObservacionCaracteristicaBean;
import com.modelo.ObservacionCaracteristicaGui;

@Stateless
@LocalBean
public class ObservacionCaracteristicaRest implements IObservacionCaracteristicaRest{

	@EJB
	private PersistenciaObservacionCaracteristicaBean persistenciaObservacionCaracteristicaBean;

	@Override
	public String echo() {
		return "Servicio Observaciones Caracteristicas Disponible";
	}

	@Override
	public Response getObservacionesCaracteristicas() {
		try {
			List<ObservacionCaracteristica> ret = persistenciaObservacionCaracteristicaBean.getObservacionesCaracteristicas();

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response add(ObservacionCaracteristica observacionCaracteristica) {
		try {
			ObservacionCaracteristicaGui ret = persistenciaObservacionCaracteristicaBean.agregarObservacionCaracteristicaGui(persistenciaObservacionCaracteristicaBean.fromObservacionCaracteristica(observacionCaracteristica));

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response remove(ObservacionCaracteristica observacionCaracteristica) {
		try {
			persistenciaObservacionCaracteristicaBean.borrarObservacionCaracteristicaGui(persistenciaObservacionCaracteristicaBean.fromObservacionCaracteristica(observacionCaracteristica));

			return Response.ok().entity(observacionCaracteristica).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response edit(ObservacionCaracteristica observacionCaracteristica) {
		try {
			persistenciaObservacionCaracteristicaBean.modificarObservacionCaracteristicaGui(persistenciaObservacionCaracteristicaBean.fromObservacionCaracteristica(observacionCaracteristica));

			return Response.ok().entity(observacionCaracteristica).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

/*	@Override
	public Response getObservacionCaracteristica(ObservacionCaracteristicaPK id) {
		ObservacionCaracteristicaGui ret = persistenciaObservacionCaracteristicaBean.buscarObservacionCaracteristica(id);

		return Response.ok().entity(ret).build(); 
	}*/
}