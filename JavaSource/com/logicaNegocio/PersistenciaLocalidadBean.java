package com.logicaNegocio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.entidades.Departamento;
import com.entidades.Localidad;
import com.entidades.Zona;
import com.excepciones.PersistenciaException;
import com.manejadoresDAO.LocalidadDAO;
import com.modelo.DepartamentoGui;
import com.modelo.LocalidadGui;
import com.modelo.ZonaGui;

@Named(value="persistenciaLocalidad")
@Stateless
@LocalBean
public class PersistenciaLocalidadBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	LocalidadDAO localidadPersistenciaDAO;

	@EJB
	PersistenciaDepartamentoBean persistenciaDB;
	
	private LocalidadGui localidadGuiSeleccionado;
	
	private List<DepartamentoGui> listaDepartamentos;
	
	private DepartamentoGui departamentoGuiSeleccionado;
	
	private Departamento departamentoSeleccionado;
	

	public PersistenciaLocalidadBean() {
		super();
	}

	public LocalidadGui fromLocalidad(Localidad l) {
		LocalidadGui lG = new LocalidadGui();

		if(l.getLocalidadId() != null) {
			lG.setLocalidadId(l.getLocalidadId().longValue());
		}

		lG.setDescripcion(l.getDescripcion());
		lG.setDepartamentoGui(persistenciaDB.fromDepartamento(l.getDepartamento()));

		return lG;
	}

	public Localidad toLocalidad(LocalidadGui lG) {
		Localidad localidad = new Localidad();
		localidad.setLocalidadId(lG.getLocalidadId() != null ? lG.getLocalidadId().longValue() : null);
		localidad.setDescripcion(lG.getDescripcion());
		localidad.setDepartamento(persistenciaDB.toDepartamento(lG.getDepartamentoGui()));

		return localidad;
	}

	public List<Localidad> seleccionarLocalidades(String criterioDescripcion) throws PersistenciaException {
		return localidadPersistenciaDAO.seleccionarLocalidades(criterioDescripcion);
	}

	public LocalidadGui buscarLocalidad(Long id) {
		Localidad l = localidadPersistenciaDAO.buscarLocalidad(id);

		return fromLocalidad(l);
	}

	public LocalidadGui buscarLocalidadGui(Long id) {
		Localidad l = localidadPersistenciaDAO.buscarLocalidad(id);

		return fromLocalidad(l);
	}

	public LocalidadGui agregarLocalidadGui(LocalidadGui localidadGuiSeleccionada) throws PersistenciaException   {
		Localidad l = localidadPersistenciaDAO.agregarLocalidad(toLocalidad(localidadGuiSeleccionada));

		return fromLocalidad(l);
	}

	public void modificarLocalidadGui(LocalidadGui localidadGuiSeleccionada) throws PersistenciaException   {
		localidadPersistenciaDAO.modificarLocalidad(toLocalidad(localidadGuiSeleccionada));
	}

	public void borrarLocalidadGui(LocalidadGui localidadGuiSeleccionado) throws PersistenciaException   {
		localidadPersistenciaDAO.borrarLocalidad(toLocalidad(localidadGuiSeleccionado));
	}

	public List<Localidad> getLocalidades() throws PersistenciaException {
		return localidadPersistenciaDAO.buscarLocalidades();
	}

	public LocalidadGui getLocalidadGuiSeleccionado() {
		return localidadGuiSeleccionado;
	}

	public void setLocalidadGuiSeleccionado(LocalidadGui localidadGuiSeleccionado) {
		this.localidadGuiSeleccionado = localidadGuiSeleccionado;
	}

	public List<DepartamentoGui> getListaDepartamentos() {
		return listaDepartamentos;
	}

	public void setListaDepartamentos(List<DepartamentoGui> listaDepartamentos) {
		this.listaDepartamentos = listaDepartamentos;
	}

	public DepartamentoGui getDepartamentoGuiSeleccionado() {
		return departamentoGuiSeleccionado;
	}

	public void setDepartamentoGuiSeleccionado(DepartamentoGui departamentoGuiSeleccionado) {
		this.departamentoGuiSeleccionado = departamentoGuiSeleccionado;
	}

	public Departamento getDepartamentoSeleccionado() {
		return departamentoSeleccionado;
	}

	public void setDepartamentoSeleccionado(Departamento departamentoSeleccionado) {
		this.departamentoSeleccionado = departamentoSeleccionado;
	}
	

	
}