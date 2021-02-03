package com.webServices;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.entidades.Fenomeno;
import com.entidades.Localidad;
import com.logicaNegocio.PersistenciaFenomenoBean;
import com.manejadoresDAO.FenomenoDAO;
import com.modelo.FenomenoGui;
import com.modelo.UsuarioGui;

@Stateless
@LocalBean
public class FenomenoRest  implements IFenomenoRest{
	
	@EJB
	private PersistenciaFenomenoBean persistenciaFenomenoBean;
	

	@Override
	public String echo() {
		return "Servicio Fenomenos Disponible";
	}
	
	@Override
	public Response obtenerFenomenos() {
		try {
			List<Fenomeno> list = persistenciaFenomenoBean.getFenomenos();

			List<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
			
			for (Fenomeno fenomeno : list) {
				ArrayList <String> fenomenos = new ArrayList<String>();
				
				fenomenos.add(fenomeno.getFenomenoId().toString());
				fenomenos.add(fenomeno.getDescripcion().toString());
				

				
				lista.add(fenomenos);
			
				}
				
				return Response.ok().entity(lista).build();
		}
	
	catch(Exception e) {
		e.printStackTrace();
		return Response.serverError().build();
		}
		
		
	}	
	
	@Override
	public Response getFenomeno(Long id) {

		

		FenomenoGui ret = persistenciaFenomenoBean.buscarFenomeno(id);

		return Response.ok().entity(ret).build();
	}

	
}
