package com.webServices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.entidades.Caracteristica;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaCaracteristicaBean;
import com.modelo.CaracteristicaGui;

@Stateless
@LocalBean
public class CaracteristicaRest implements ICaracteristicaRest{

	@EJB
	private PersistenciaCaracteristicaBean persistenciaCaracteristicaBean;

	@Override
	public String echo() {
		return "Servicio Caracteristicas Disponible";
	}

	@Override
	public Response getCaracteristicas() {
		try {
			List<Caracteristica> ret = persistenciaCaracteristicaBean.getCaracteristicas();

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response add(Caracteristica caracteristica) {
		try {
			CaracteristicaGui ret = persistenciaCaracteristicaBean.agregarCaracteristicaGui(persistenciaCaracteristicaBean.fromCaracteristica(caracteristica));

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response remove(Caracteristica caracteristica) {
		try {
			persistenciaCaracteristicaBean.borrarCaracteristicaGui(persistenciaCaracteristicaBean.fromCaracteristica(caracteristica));

			return Response.ok().entity(caracteristica).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response edit(Caracteristica caracteristica) {
		try {
			persistenciaCaracteristicaBean.modificarCaracteristicaGui(persistenciaCaracteristicaBean.fromCaracteristica(caracteristica));

			return Response.ok().entity(caracteristica).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response getCaracteristica(Long id) {
		CaracteristicaGui ret = persistenciaCaracteristicaBean.buscarCaracteristica(id);
		return Response.ok().entity(ret).build();
	}
}