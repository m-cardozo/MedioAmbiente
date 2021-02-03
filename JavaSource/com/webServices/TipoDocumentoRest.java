package com.webServices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.entidades.TipoDocumento;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaTipoDocumentoBean;
import com.modelo.TipoDocumentoGui;

@Stateless
@LocalBean
public class TipoDocumentoRest implements ITipoDocumentoRest{

	@EJB
	private PersistenciaTipoDocumentoBean persistenciaTipoDocumentoBean;

	@Override
	public String echo() {
		return "Servicio Tipos Documento Disponible";
	}

	@Override
	public Response getTiposDocumento() {
		try {
			List<TipoDocumento> ret = persistenciaTipoDocumentoBean.getTiposDocumento();

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response add(TipoDocumento tipoDocumento) {
		try {
			TipoDocumentoGui ret = persistenciaTipoDocumentoBean.agregarTipoDocumentoGui(persistenciaTipoDocumentoBean.fromTipoDocumento(tipoDocumento));

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response remove(TipoDocumento tipoDocumento) {
		try {
			persistenciaTipoDocumentoBean.borrarTipoDocumentoGui(persistenciaTipoDocumentoBean.fromTipoDocumento(tipoDocumento));

			return Response.ok().entity(tipoDocumento).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response edit(TipoDocumento tipoDocumento) {
		try {
			persistenciaTipoDocumentoBean.modificarTipoDocumentoGui(persistenciaTipoDocumentoBean.fromTipoDocumento(tipoDocumento));

			return Response.ok().entity(tipoDocumento).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response getTipoDocumento(Long id) {

		System.out.println("TipoDocumentoREST - ID: "+id);

		TipoDocumentoGui ret = persistenciaTipoDocumentoBean.buscarTipoDocumento(id);

		return Response.ok().entity(ret).build();
	}
}