package com.entidades;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

@Entity
@Table(name = "OBSERVACIONES_IMAGENES")
public class ObservacionImagen implements Serializable {	   

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long observacionImagenId;

	@Column(name = "PATH", unique = true, length = 200)
	private String path;

	@ManyToOne
	@JoinColumn(name = "ID_OBSERVACION")
	private Observacion observacion;

	public ObservacionImagen() {
		super();
	}

	public ObservacionImagen(Long observacionImagenId, String path, Observacion observacion) {
		super();
		this.observacionImagenId = observacionImagenId;
		this.path = path;
		this.observacion = observacion;
	}

	public Long getObservacionImagenId() {
		return observacionImagenId;
	}
	public void setObservacionImagenId(Long observacionImagenId) {
		this.observacionImagenId = observacionImagenId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Observacion getObservacion() {
		return observacion;
	}
	public void setObservacion(Observacion observacion) {
		this.observacion = observacion;
	}
}