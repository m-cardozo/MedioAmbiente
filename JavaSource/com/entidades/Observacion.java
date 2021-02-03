package com.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.ws.rs.ext.ParamConverter.Lazy;

@Entity
@Table(name = "OBSERVACIONES")
public class Observacion implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long observacionId;

	@Column(name = "DESCRIPCION", length = 50)
	private String descripcion;

	@Column(name = "FECHA")
	private Date fecha;

	@Column(name = "LATITUD", length = 100)
	private String latitud;

	@Column(name = "LONGITUD", length = 100)
	private String longitud;

	@Column(name = "ALTITUD", length = 100)
	private String altitud;

	@Column(name = "NIVEL_CRITICO", length = 10)
	private String nivelCritico;

	@ManyToOne
	@JoinColumn(name = "ID_FENOMENO")
	private Fenomeno fenomeno;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "ID_LOCALIDAD")
	private Localidad localidad;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "observacion")
	private List<ObservacionImagen> observacionesImagenes;

	//@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "observacion")
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "observacion")
	private List<ObservacionCaracteristica> observacionesCaracteristicas;
	
	//@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "observacion" )
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "observacion" )
	private List<ObservacionRevision> observacionesRevisiones;

	public Observacion() {
		super();
	}

	public Observacion(Long observacionId, String descripcion, Date fecha, String latitud, String longitud,
			String altitud, String nivelCritico, Fenomeno fenomeno, Usuario usuario, Localidad localidad) {
		super();
		this.observacionId = observacionId;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.latitud = latitud;
		this.longitud = longitud;
		this.altitud = altitud;
		this.nivelCritico = nivelCritico;
		this.fenomeno = fenomeno;
		this.usuario = usuario;
		this.localidad = localidad;
	}

	public Long getObservacionId() {
		return observacionId;
	}
	public void setObservacionId(Long observacionId) {
		this.observacionId = observacionId;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public String getAltitud() {
		return altitud;
	}
	public void setAltitud(String altitud) {
		this.altitud = altitud;
	}
	public String getNivelCritico() {
		return nivelCritico;
	}
	public void setNivelCritico(String nivelCritico) {
		this.nivelCritico = nivelCritico;
	}
	public Fenomeno getFenomeno() {
		return fenomeno;
	}
	public void setFenomeno(Fenomeno fenomeno) {
		this.fenomeno = fenomeno;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public List<ObservacionImagen> getObservacionesImagenes() {
		return observacionesImagenes;
	}
	public void setObservacionesImagenes(List<ObservacionImagen> observacionImagenes) {
		this.observacionesImagenes = observacionImagenes;
	}
	public List<ObservacionCaracteristica> getObservacionesCaracteristicas() {
		return observacionesCaracteristicas;
	}
	public void setObservacionesCaracteristicas(List<ObservacionCaracteristica> observacionCaracteristica) {
		this.observacionesCaracteristicas = observacionCaracteristica;
	}
	public List<ObservacionRevision> getObservacionesRevisiones() {
		return observacionesRevisiones;
	}
	public void setObservacionesRevisiones(List<ObservacionRevision> observacionesRevisiones) {
		this.observacionesRevisiones = observacionesRevisiones;
	}

	public void agregarObservacionCaracteristica(ObservacionCaracteristica oc) {
		oc.setObservacion(this);
		this.observacionesCaracteristicas.add(oc);
	}

	public void eliminarObservacionCaracteristica(ObservacionCaracteristicaPK ocPK) {
		Iterator<ObservacionCaracteristica> iterator = observacionesCaracteristicas.iterator();
		while (iterator.hasNext()) {
			ObservacionCaracteristica observacionCaracteristica = iterator.next();
			if (observacionCaracteristica.getId().equals(ocPK)) {
				observacionCaracteristica.setObservacion(null);
				iterator.remove();
			};
		}
	}	
	
}