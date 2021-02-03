package com.logicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.entidades.Caracteristica;
import com.excepciones.PersistenciaException;
import com.manejadoresDAO.CaracteristicaDAO;
import com.modelo.CaracteristicaGui;

@Named(value="persistenciaCaracteristica")
@Stateless
@LocalBean
public class PersistenciaCaracteristicaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	CaracteristicaDAO caracteristicaPersistenciaDAO;
	
	@EJB
	PersistenciaFenomenoBean persistenciaFB;

	public PersistenciaCaracteristicaBean() {
		super();
	}

	public CaracteristicaGui fromCaracteristica(Caracteristica c) {
		CaracteristicaGui cG = new CaracteristicaGui();

		if(c.getCaracteristicaId() != null) {
			cG.setCaracteristicaId(c.getCaracteristicaId().longValue());
		}

		cG.setNombre(c.getNombre());
		cG.setEtiqueta(c.getEtiqueta());
		cG.setTipoDato(c.getTipoDato());
		cG.setFenomenoGui(persistenciaFB.fromFenomeno(c.getFenomeno()));

		return cG;
	}

	public Caracteristica toCaracteristica(CaracteristicaGui cG) {
		Caracteristica caracteristica= new Caracteristica();
		caracteristica.setCaracteristicaId(cG.getCaracteristicaId() != null ? cG.getCaracteristicaId().longValue() : null);
		caracteristica.setNombre(cG.getNombre());
		caracteristica.setEtiqueta(cG.getEtiqueta());
		caracteristica.setTipoDato(cG.getTipoDato());
		caracteristica.setFenomeno(persistenciaFB.toFenomeno(cG.getFenomenoGui()));

		return caracteristica;
	}

	public List<Caracteristica> seleccionarCaracteristicas(String criterioNombre, String criterioEtiqueta) throws PersistenciaException {
		return caracteristicaPersistenciaDAO.seleccionarCaracteristicas(criterioNombre, criterioEtiqueta);
	}

	public CaracteristicaGui buscarCaracteristica(Long id) {
		Caracteristica c = caracteristicaPersistenciaDAO.buscarCaracteristica(id);

		return fromCaracteristica(c);
	}

	public CaracteristicaGui buscarCaracteristicaGui(Long id) {
		Caracteristica c = caracteristicaPersistenciaDAO.buscarCaracteristica(id);

		return fromCaracteristica(c);
	}

	public CaracteristicaGui agregarCaracteristicaGui(CaracteristicaGui caracteristicaGuiSeleccionado) throws PersistenciaException   {
		Caracteristica c= caracteristicaPersistenciaDAO.agregarCaracteristica(toCaracteristica(caracteristicaGuiSeleccionado));

		return fromCaracteristica(c);
	}

	public void modificarCaracteristicaGui(CaracteristicaGui caracteristicaGuiSeleccionado) throws PersistenciaException   {
		caracteristicaPersistenciaDAO.modificarCaracteristica(toCaracteristica(caracteristicaGuiSeleccionado));
	}

	public void borrarCaracteristicaGui(CaracteristicaGui caracteristicaGuiSeleccionado) throws PersistenciaException   {
		caracteristicaPersistenciaDAO.borrarCaracteristica(toCaracteristica(caracteristicaGuiSeleccionado));
	}

	public List<Caracteristica> getCaracteristicas() throws PersistenciaException {
		return caracteristicaPersistenciaDAO.buscarCaracteristicas();
	}

	public boolean inListaCaracteristicas( ArrayList<CaracteristicaGui> listaCaracteristicas,Caracteristica c){
		
		boolean esta=false;
		int i=0;
		try
		{
			while(!esta && i<listaCaracteristicas.size()) {
				esta=(listaCaracteristicas.get(i).getCaracteristicaId()==c.getCaracteristicaId());
			}
		}catch (Exception e) {
			esta=false;
		}
		return esta;
	}
	
	
}