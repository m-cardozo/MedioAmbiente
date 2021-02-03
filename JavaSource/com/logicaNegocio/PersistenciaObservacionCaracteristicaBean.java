package com.logicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.entidades.ObservacionCaracteristica;
import com.entidades.ObservacionCaracteristicaPK;
import com.excepciones.PersistenciaException;
import com.manejadoresDAO.ObservacionCaracteristicaDAO;
import com.modelo.CaracteristicaGui;
import com.modelo.ObservacionCaracteristicaGui;
import com.modelo.ObservacionGui;

@Named(value = "persistenciaObservacionCaracteristica")
@Stateless
@LocalBean
public class PersistenciaObservacionCaracteristicaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	ObservacionCaracteristicaDAO observacionCaracteristicaPersistenciaDAO;

	@EJB
	PersistenciaObservacionBean persistenciaOB;

	@EJB
	PersistenciaCaracteristicaBean persistenciaCB;

	public PersistenciaObservacionCaracteristicaBean() {
		super();
	}

	public ObservacionCaracteristicaGui fromObservacionCaracteristica(ObservacionCaracteristica oC) {
		ObservacionCaracteristicaGui oCG = new ObservacionCaracteristicaGui();

		if (oC.getId() != null) {
			oCG.setId(oC.getId());
		}

		CaracteristicaGui cG=persistenciaCB.buscarCaracteristica(oC.getCaracteristica().getCaracteristicaId());
		oCG.setCaracteristicaGui(cG);
		
		ObservacionGui oG=persistenciaOB.buscarObservacion(oC.getObservacion().getObservacionId());
		oCG.setObservacionGui(oG);
		
		oCG.setValorNumerico(oC.getValorNumerico());
		oCG.setValorTexto(oC.getValorTexto());
		oCG.setValorFecha(oC.getValorFecha());

		return oCG;
	}

	public ObservacionCaracteristica toObservacionCaracteristica(ObservacionCaracteristicaGui oCG) {
		ObservacionCaracteristica observacionCaracteristica = new ObservacionCaracteristica();
		observacionCaracteristica.setObservacion(persistenciaOB.toObservacion(oCG.getObservacionGui()) != null ? persistenciaOB.toObservacion(oCG.getObservacionGui()) : null);
		observacionCaracteristica.setCaracteristica(persistenciaCB.toCaracteristica(oCG.getCaracteristicaGui()) != null ? persistenciaCB.toCaracteristica(oCG.getCaracteristicaGui()) : null);
		observacionCaracteristica.setValorNumerico(oCG.getValorNumerico());
		observacionCaracteristica.setValorTexto(oCG.getValorTexto());
		observacionCaracteristica.setValorFecha(oCG.getValorFecha());

		return observacionCaracteristica;
	}

	public List<ObservacionCaracteristica> seleccionarObservacionesCaracteristicas(String criterioIdObservacion, String criterioIdCaracteristica) throws PersistenciaException {
		return observacionCaracteristicaPersistenciaDAO.seleccionarObservacionesCaracteristicas(criterioIdObservacion, criterioIdCaracteristica);
	}

	public ObservacionCaracteristicaGui buscarObservacionCaracteristica(ObservacionCaracteristicaPK id) {
		ObservacionCaracteristica oC = observacionCaracteristicaPersistenciaDAO.buscarObservacionCaracteristica(id);

		return fromObservacionCaracteristica(oC);
	}

	public ObservacionCaracteristicaGui buscarObservacionCaracteristicaGui(ObservacionCaracteristicaPK id) {
		ObservacionCaracteristica oC = observacionCaracteristicaPersistenciaDAO.buscarObservacionCaracteristica(id);

		return fromObservacionCaracteristica(oC);
	}

	public ObservacionCaracteristicaGui agregarObservacionCaracteristicaGui(ObservacionCaracteristicaGui observacionCaracteristicaGuiSeleccionado) throws PersistenciaException {
		ObservacionCaracteristica oC = observacionCaracteristicaPersistenciaDAO.agregarObservacionCaracteristica(toObservacionCaracteristica(observacionCaracteristicaGuiSeleccionado));

		return fromObservacionCaracteristica(oC);
	}

	public void modificarObservacionCaracteristicaGui(ObservacionCaracteristicaGui observacionCaracteristicaGuiSeleccionado) throws PersistenciaException {
		observacionCaracteristicaPersistenciaDAO.modificarObservacionCaracteristica(toObservacionCaracteristica(observacionCaracteristicaGuiSeleccionado));
	}

	public void borrarObservacionCaracteristicaGui(ObservacionCaracteristicaGui observacionCaracteristicaGuiSeleccionado) throws PersistenciaException {
		observacionCaracteristicaPersistenciaDAO.borrarObservacionCaracteristica(toObservacionCaracteristica(observacionCaracteristicaGuiSeleccionado));
	}

	public List<ObservacionCaracteristica> getObservacionesCaracteristicas() throws PersistenciaException {
		return observacionCaracteristicaPersistenciaDAO.getObservacionesCaracteristicas();
	}

	public List<ObservacionCaracteristica> getObservacionesCaracteristicas(int idFenomeno) throws PersistenciaException {
		return observacionCaracteristicaPersistenciaDAO.findByObservacionCaracteristica_Caracteristica_Fenomeno_IdFenomeno(idFenomeno);
	}

	
	public boolean inListaObservacionesCaracteristicas(ObservacionCaracteristica oC, ArrayList<ObservacionCaracteristica> listaObservacionesCaracteristicas){
		
		boolean esta=false;
		int i=0;
		try
		{
			while(!esta && i<listaObservacionesCaracteristicas.size()) {
				esta=(listaObservacionesCaracteristicas.get(i).getCaracteristica().getCaracteristicaId()==oC.getCaracteristica().getCaracteristicaId());
			}
		}catch (Exception e) {
			esta=false;
		}
		return esta;
	}
	
	public boolean inListaObservacionesCaracteristicas( ArrayList<ObservacionCaracteristicaGui> listaObservacionesCaracteristicas,ObservacionCaracteristica oC){
		
		boolean esta=false;
		int i=0;
		try
		{
			while(!esta && i<listaObservacionesCaracteristicas.size()) {
				esta=(listaObservacionesCaracteristicas.get(i).getCaracteristicaGui().getCaracteristicaId()==oC.getCaracteristica().getCaracteristicaId());
			}
		}catch (Exception e) {
			esta=false;
		}
		return esta;
	}
}