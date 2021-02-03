package com.logicaNegocio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.entidades.Zona;
import com.excepciones.PersistenciaException;
import com.manejadoresDAO.ZonaDAO;
import com.modelo.ZonaGui;

@Named(value="persistenciaZona")
@Stateless
@LocalBean
public class PersistenciaZonaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	ZonaDAO zonaPersistenciaDAO;

	public PersistenciaZonaBean() {
		super();
	}

	public ZonaGui fromZona(Zona z) {
		ZonaGui zG = new ZonaGui();

		if(z.getZonaId() != null) {
			zG.setZonaId(z.getZonaId().longValue());
		}

		zG.setDescripcion(z.getDescripcion());

		return zG;
	}

	public Zona toZona(ZonaGui zG) {
		Zona zona = new Zona();
		zona.setZonaId(zG.getZonaId() != null ? zG.getZonaId().longValue() : null);
		zona.setDescripcion(zG.getDescripcion());

		return zona;
	}

	public List<Zona> seleccionarZonas(String criterioDescripcion) throws PersistenciaException {
		return zonaPersistenciaDAO.seleccionarZonas(criterioDescripcion);
	}

	public ZonaGui buscarZona(Long id) {
		Zona z = zonaPersistenciaDAO.buscarZona(id);

		return fromZona(z);
	}

	public ZonaGui buscarZonaGui(Long id) {
		Zona z = zonaPersistenciaDAO.buscarZona(id);

		return fromZona(z);
	}

	public ZonaGui agregarZonaGui(ZonaGui zonaGuiSeleccionado) throws PersistenciaException   {
		Zona z = zonaPersistenciaDAO.agregarZona(toZona(zonaGuiSeleccionado));

		return fromZona(z);
	}

	public void modificarZonaGui(ZonaGui zonaGuiSeleccionado) throws PersistenciaException   {
		zonaPersistenciaDAO.modificarZona(toZona(zonaGuiSeleccionado));
	}

	public void borrarZonaGui(ZonaGui zonaGuiSeleccionado) throws PersistenciaException   {
		zonaPersistenciaDAO.borrarZona(toZona(zonaGuiSeleccionado));
	}

	public List<Zona> getZonas() throws PersistenciaException {
		return zonaPersistenciaDAO.buscarZonas();
	}
}