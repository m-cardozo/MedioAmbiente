package com.webServices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.entidades.ObservacionRevision;
import com.entidades.ObservacionRevisionPK;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaObservacionRevisionBean;
import com.modelo.ObservacionRevisionGui;

@Stateless
@LocalBean
public class ObservacionRevisionRest implements IObservacionRevisionRest{

	@EJB
	private PersistenciaObservacionRevisionBean persistenciaObservacionRevisionBean;

	@Override
	public String echo() {
		return "Servicio Observaciones Revisiones Disponible";
	}

	@Override
	public Response getObservacionesRevisiones() {
		try {
			List<ObservacionRevision> ret = persistenciaObservacionRevisionBean.getObservacionesRevisiones();

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response add(ObservacionRevision observacionRevision) {
		try {
			ObservacionRevisionGui ret = persistenciaObservacionRevisionBean.agregarObservacionRevisionGui(persistenciaObservacionRevisionBean.fromObservacionRevision(observacionRevision));

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response remove(ObservacionRevision observacionRevision) {
		try {
			persistenciaObservacionRevisionBean.borrarObservacionRevisionGui(persistenciaObservacionRevisionBean.fromObservacionRevision(observacionRevision));

			return Response.ok().entity(observacionRevision).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response edit(ObservacionRevision observacionRevision) {
		try {
			persistenciaObservacionRevisionBean.modificarObservacionRevisionGui(persistenciaObservacionRevisionBean.fromObservacionRevision(observacionRevision));

			return Response.ok().entity(observacionRevision).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

/*	@Override
	public Response getObservacionRevision(ObservacionRevisionPK id) {
		ObservacionRevisionGui ret = persistenciaObservacionRevisionBean.buscarObservacionRevision(id);

		return Response.ok().entity(ret).build();
	}*/
}