package com.logicaNegocio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.entidades.TipoDocumento;
import com.excepciones.PersistenciaException;
import com.manejadoresDAO.TipoDocumentoDAO;
import com.modelo.TipoDocumentoGui;

@Named(value="persistenciaTipoDocumento")
@Stateless
@LocalBean
public class PersistenciaTipoDocumentoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	TipoDocumentoDAO tipoDocumentoPersistenciaDAO;

	public PersistenciaTipoDocumentoBean() {
		super();
	}

	public TipoDocumentoGui fromTipoDocumento(TipoDocumento tD) {
		TipoDocumentoGui tDG = new TipoDocumentoGui();

		if(tD.getTipoDocumentoId() != null) {
			tDG.setTipoDocumentoId(tD.getTipoDocumentoId().longValue());
		}

		tDG.setDescripcion(tD.getDescripcion());

		return tDG;
	}

	public TipoDocumento toTipoDocumento(TipoDocumentoGui tDG) {
		TipoDocumento tipoDocumento = new TipoDocumento();
		tipoDocumento.setTipoDocumentoId(tDG.getTipoDocumentoId() != null ? tDG.getTipoDocumentoId().longValue() : null);
		tipoDocumento.setDescripcion(tDG.getDescripcion());

		return tipoDocumento;
	}

	public List<TipoDocumento> seleccionarTiposDocumento(String criterioDescripcion) throws PersistenciaException {
		return tipoDocumentoPersistenciaDAO.seleccionarTiposDocumento(criterioDescripcion);
	}

	public TipoDocumentoGui buscarTipoDocumento(Long id) {
		TipoDocumento tD = tipoDocumentoPersistenciaDAO.buscarTipoDocumento(id);

		return fromTipoDocumento(tD);
	}

	public TipoDocumentoGui buscarTipoDocumentoGui(Long id) {
		TipoDocumento tD = tipoDocumentoPersistenciaDAO.buscarTipoDocumento(id);

		return fromTipoDocumento(tD);
	}

	public TipoDocumentoGui agregarTipoDocumentoGui(TipoDocumentoGui tipoDocumentoGuiSeleccionado) throws PersistenciaException   {
		TipoDocumento tD = tipoDocumentoPersistenciaDAO.agregarTipoDocumento(toTipoDocumento(tipoDocumentoGuiSeleccionado));

		return fromTipoDocumento(tD);
	}

	public void modificarTipoDocumentoGui(TipoDocumentoGui tipoDocumentoGuiSeleccionado) throws PersistenciaException   {
		tipoDocumentoPersistenciaDAO.modificarTipoDocumento(toTipoDocumento(tipoDocumentoGuiSeleccionado));
	}

	public void borrarTipoDocumentoGui(TipoDocumentoGui tipoDocumentoGuiSeleccionado) throws PersistenciaException   {
		tipoDocumentoPersistenciaDAO.borrarTipoDocumento(toTipoDocumento(tipoDocumentoGuiSeleccionado));
	}

	public List<TipoDocumento> getTiposDocumento() throws PersistenciaException {
		return tipoDocumentoPersistenciaDAO.buscarTiposDocumento();
	}
}