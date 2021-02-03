package com.logicaNegocio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.entidades.Fenomeno;
import com.excepciones.PersistenciaException;
import com.manejadoresDAO.FenomenoDAO;
import com.modelo.FenomenoGui;

@Named(value="persistenciaFenomeno")
@Stateless
@LocalBean
public class PersistenciaFenomenoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	FenomenoDAO fenomenoPersistenciaDAO;

	public PersistenciaFenomenoBean() {
		super();
	}

	public FenomenoGui fromFenomeno(Fenomeno f) {
		FenomenoGui fG = new FenomenoGui();

		if(f.getFenomenoId() != null) {
			fG.setFenomenoId(f.getFenomenoId().longValue());
		}

		fG.setNombre(f.getNombre());
		fG.setDescripcion(f.getDescripcion());
		fG.setTelefonoEmergencia(f.getTelefonoEmergencia());

		return fG;
	}

	public Fenomeno toFenomeno(FenomenoGui fG) {
		Fenomeno fenomeno = new Fenomeno();
		fenomeno.setFenomenoId(fG.getFenomenoId() != null ? fG.getFenomenoId().longValue() : null);
		fenomeno.setNombre(fG.getNombre());
		fenomeno.setDescripcion(fG.getDescripcion());
		fenomeno.setTelefonoEmergencia(fG.getTelefonoEmergencia());

		return fenomeno;
	}

	public List<Fenomeno> seleccionarFenomenos(String criterioNombre) throws PersistenciaException {
		return fenomenoPersistenciaDAO.seleccionarFenomenos(criterioNombre);
	}

	public FenomenoGui buscarFenomeno(Long id) {
		Fenomeno f = fenomenoPersistenciaDAO.buscarFenomeno(id);

		return fromFenomeno(f);
	}

	public FenomenoGui buscarFenomenoGui(Long id) {
		Fenomeno f = fenomenoPersistenciaDAO.buscarFenomeno(id);

		return fromFenomeno(f);
	}

	public FenomenoGui agregarFenomenoGui(FenomenoGui fenomenoGuiSeleccionado) throws PersistenciaException   {
		Fenomeno f = fenomenoPersistenciaDAO.agregarFenomeno(toFenomeno(fenomenoGuiSeleccionado));

		return fromFenomeno(f);
	}

	public void modificarFenomenoGui(FenomenoGui fenomenoGuiSeleccionado) throws PersistenciaException   {
		fenomenoPersistenciaDAO.modificarFenomeno(toFenomeno(fenomenoGuiSeleccionado));
	}

	public void borrarFenomenoGui(FenomenoGui fenomenoGuiSeleccionado) throws PersistenciaException   {
		fenomenoPersistenciaDAO.borrarFenomeno(toFenomeno(fenomenoGuiSeleccionado));
	}

	public List<Fenomeno> getFenomenos() throws PersistenciaException {
		return fenomenoPersistenciaDAO.buscarFenomenos();
	}
}