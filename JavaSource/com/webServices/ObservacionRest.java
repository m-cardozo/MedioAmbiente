package com.webServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.entidades.Fenomeno;
import com.entidades.Observacion;
import com.entidades.Zona;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaObservacionBean;
import com.manejadores.GestionObservaciones;
import com.manejadoresDAO.ObservacionDAO;
import com.modelo.ObservacionGui;

@Stateless
@LocalBean
public class ObservacionRest implements IObservacionRest{

	@EJB
	private PersistenciaObservacionBean persistenciaObservacionBean;
	
	
	
	
	
	@Override
	public String echo() {
		return "Servicio Observaciones Disponible";
	}
	
	

	@Override
	public Response getObservaciones() {
		
		
		try {
			
		
			List<Observacion> list = persistenciaObservacionBean.getObservaciones();
			List<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
			
			for (Observacion observacion : list) {
				ArrayList <String> observaciones = new ArrayList<String>();
				
				observaciones.add(observacion.getObservacionId().toString() );
				observaciones.add(observacion.getDescripcion());
				observaciones.add(observacion.getFecha().toString());
				observaciones.add(observacion.getNivelCritico());
				observaciones.add(observacion.getFenomeno().getNombre().toString());
				observaciones.add(observacion.getLocalidad().getDescripcion().toString());
				observaciones.add(observacion.getLatitud().toString());
				observaciones.add(observacion.getLongitud().toString());
				observaciones.add(observacion.getAltitud().toString());
				observaciones.add(observacion.getUsuario().getUsuario().toString());

				
				lista.add(observaciones);
			
				}
				
				return Response.ok().entity(lista).build();
		        
		    

		} catch (PersistenciaException e) {
			e.printStackTrace();
		
		}
		return null;
	} 
/*
	@Override
	public Response getObservaciones() {
		try {
			List<Observacion> ret = persistenciaObservacionBean.getObservaciones();
	

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}*/

	@Override
	public Response add(Observacion observacion) {
		try {
			ObservacionGui ret = persistenciaObservacionBean.agregarObservacionGuiPrueba(persistenciaObservacionBean.fromObservacionPrueba(observacion));

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response remove(Long id) {
		try {
			persistenciaObservacionBean.borrarPorIdGui(id);

			return Response.ok().entity(Response.Status.NO_CONTENT).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response edit(Observacion observacion) {
		try {
			persistenciaObservacionBean.modificarObservacionGui(persistenciaObservacionBean.fromObservacionPrueba(observacion));

			return Response.ok().entity(observacion).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}
/*	
	@Override
	public Response getObservacion(Long id) {

		System.out.println("ObservacionREST - ID: "+id);

		ObservacionGui ret = persistenciaObservacionBean.buscarObservacion(id);
		ArrayList <String> observacion = new ArrayList<String>();
		
		observacion.add(ret.getDescripcion());
		observacion.add(ret.getFecha().toString());
		observacion.add(ret.getNivelCritico());
		observacion.add(ret.getFenomenoGui().getNombre().toString());
		observacion.add(ret.getLocalidadGui().getDescripcion().toString());
		observacion.add(ret.getLatitud().toString());
		observacion.add(ret.getLongitud().toString());
		observacion.add(ret.getAltitud().toString());
		observacion.add(ret.getUsuarioGui().getUsuario().toString());
		

		return Response.ok().entity(observacion).build();
	}*/

	@Override
	public Response getObservacion(Long id) {

		System.out.println("ObservacionREST - ID: "+id);

		ObservacionGui ret = persistenciaObservacionBean.buscarObservacion(id);
		

		return Response.ok().entity(ret).build();
	}
}