package com.webServices;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.entidades.Localidad;
import com.entidades.Observacion;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaLocalidadBean;
import com.modelo.LocalidadGui;

@Stateless
@LocalBean
public class LocalidadRest implements ILocalidadRest{

	@EJB
	private PersistenciaLocalidadBean persistenciaLocalidadBean;

	@Override
	public String echo() {
		return "Servicio Localidades Disponible";
	}

	@Override
	public Response getLocalidades() {
		try {
			List<Localidad> list = persistenciaLocalidadBean.getLocalidades();

			List<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
			
			for (Localidad localidad : list) {
				ArrayList <String> localidades = new ArrayList<String>();
				
				localidades.add(localidad.getLocalidadId().toString());
				localidades.add(localidad.getDescripcion().toString());
				

				
				lista.add(localidades);
			
				}
				
				return Response.ok().entity(lista).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response add(Localidad localidad) {
		try {
			LocalidadGui ret = persistenciaLocalidadBean.agregarLocalidadGui(persistenciaLocalidadBean.fromLocalidad(localidad));

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response remove(Localidad localidad) {
		try {
			persistenciaLocalidadBean.borrarLocalidadGui(persistenciaLocalidadBean.fromLocalidad(localidad));

			return Response.ok().entity(localidad).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response edit(Localidad localidad) {
		try {
			persistenciaLocalidadBean.modificarLocalidadGui(persistenciaLocalidadBean.fromLocalidad(localidad));

			return Response.ok().entity(localidad).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response getLocalidad(Long id) {

		System.out.println("LocalidadREST - ID: "+id);

		LocalidadGui ret = persistenciaLocalidadBean.buscarLocalidad(id);

		return Response.ok().entity(ret).build();
	}
}