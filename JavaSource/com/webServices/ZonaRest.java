package com.webServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.entidades.Zona;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaZonaBean;
import com.modelo.ZonaGui;

@Stateless
@LocalBean
public class ZonaRest implements IZonaRest{

	@EJB
	private PersistenciaZonaBean persistenciaZonaBean;

	@Override
	public String echo() {
		return "Servicio Zonas Disponible";
	}

	@Override
	public Response getZonas() {
		
		
		try {
			List<Zona> list = persistenciaZonaBean.getZonas();
			Map<Long, String> map = new HashMap<>();
			for (Zona zona : list) {
		        map.put(zona.getZonaId(), zona.getDescripcion());
		    }
			

			return Response.ok().entity(map.values()).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response add(Zona zona) {
		try {
			ZonaGui ret = persistenciaZonaBean.agregarZonaGui(persistenciaZonaBean.fromZona(zona));

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response remove(Zona zona) {
		try {
			persistenciaZonaBean.borrarZonaGui(persistenciaZonaBean.fromZona(zona));

			return Response.ok().entity(zona).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response edit(Zona zona) {
		try {
			persistenciaZonaBean.modificarZonaGui(persistenciaZonaBean.fromZona(zona));

			return Response.ok().entity(zona).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response getZona(Long id) {

		System.out.println("ZonaREST - ID: "+id);

		ZonaGui ret = persistenciaZonaBean.buscarZona(id);

		return Response.ok().entity(ret).build();
	}
}