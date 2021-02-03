package com.logicaNegocio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.entidades.Departamento;
import com.excepciones.PersistenciaException;
import com.manejadoresDAO.DepartamentoDAO;
import com.modelo.DepartamentoGui;

@Named(value="persistenciaDepartamento")
@Stateless
@LocalBean
public class PersistenciaDepartamentoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	DepartamentoDAO departamentoPersistenciaDAO;

	@EJB
	PersistenciaZonaBean persistenciaZB;

	public PersistenciaDepartamentoBean() {
		super();
	}

	public DepartamentoGui fromDepartamento(Departamento d) {
		DepartamentoGui dG = new DepartamentoGui();

		if(d.getDepartamentoId() != null) {
			dG.setDepartamentoId(d.getDepartamentoId().longValue());
		}

		dG.setDescripcion(d.getDescripcion());
		dG.setZonaGui(persistenciaZB.fromZona(d.getZona()));

		return dG;
	}

	public Departamento toDepartamento(DepartamentoGui dG) {
		Departamento departamento = new Departamento();
		departamento.setDepartamentoId(dG.getDepartamentoId() != null ? dG.getDepartamentoId().longValue() : null);
		departamento.setDescripcion(dG.getDescripcion());
		departamento.setZona(persistenciaZB.toZona(dG.getZonaGui()));

		return departamento;
	}

	public List<Departamento> seleccionarDepartamentos(String criterioDescripcion) throws PersistenciaException {
		return departamentoPersistenciaDAO.seleccionarDepartamentos(criterioDescripcion);
	}

	public DepartamentoGui buscarDepartamento(Long id) {
		Departamento d = departamentoPersistenciaDAO.buscarDepartamento(id);

		return fromDepartamento(d);
	}

	public DepartamentoGui buscarDepartamentoGui(Long id) {
		Departamento d = departamentoPersistenciaDAO.buscarDepartamento(id);

		return fromDepartamento(d);
	}

	public DepartamentoGui agregarDepartamentoGui(DepartamentoGui departamentoGuiSeleccionada) throws PersistenciaException   {
		Departamento d = departamentoPersistenciaDAO.agregarDepartamento(toDepartamento(departamentoGuiSeleccionada));

		return fromDepartamento(d);
	}

	public void modificarDepartamentoGui(DepartamentoGui departamentoGuiSeleccionada) throws PersistenciaException   {
		departamentoPersistenciaDAO.modificarDepartamento(toDepartamento(departamentoGuiSeleccionada));
	}

	public void borrarDepartamentoGui(DepartamentoGui departamentoGuiSeleccionado) throws PersistenciaException   {
		departamentoPersistenciaDAO.borrarDepartamento(toDepartamento(departamentoGuiSeleccionado));
	}

	public List<Departamento> getDepartamentos() throws PersistenciaException {
		return departamentoPersistenciaDAO.buscarDepartamentos();
	}
}