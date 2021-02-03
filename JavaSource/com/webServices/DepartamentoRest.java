package com.webServices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.entidades.Departamento;
import com.excepciones.PersistenciaException;
import com.logicaNegocio.PersistenciaDepartamentoBean;
import com.modelo.DepartamentoGui;

@Stateless
@LocalBean
public class DepartamentoRest implements IDepartamentoRest{

	@EJB
	private PersistenciaDepartamentoBean persistenciaDepartamentoBean;

	@Override
	public String echo() {
		return "Servicio Departamentos Disponible";
	}

	@Override
	public Response getDepartamentos() {
		try {
			List<Departamento> ret = persistenciaDepartamentoBean.getDepartamentos();

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response add(Departamento departamento) {
		try {
			DepartamentoGui ret = persistenciaDepartamentoBean.agregarDepartamentoGui(persistenciaDepartamentoBean.fromDepartamento(departamento));

			return Response.ok().entity(ret).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response remove(Departamento departamento) {
		try {
			persistenciaDepartamentoBean.borrarDepartamentoGui(persistenciaDepartamentoBean.fromDepartamento(departamento));

			return Response.ok().entity(departamento).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response edit(Departamento departamento) {
		try {
			persistenciaDepartamentoBean.modificarDepartamentoGui(persistenciaDepartamentoBean.fromDepartamento(departamento));

			return Response.ok().entity(departamento).build();
		} catch (PersistenciaException e) {
			e.printStackTrace();

			return Response.serverError().build();
		}
	}

	@Override
	public Response getDepartamento(Long id) {

		System.out.println("DepartamentoREST - ID: "+id);

		DepartamentoGui ret = persistenciaDepartamentoBean.buscarDepartamento(id);

		return Response.ok().entity(ret).build();
	}
}