package com.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "OBSERVACIONES_REVISIONES")
public class ObservacionRevision implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ObservacionRevisionPK id = new ObservacionRevisionPK();

	@MapsId("usuarioId")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	@MapsId("observacionId")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_OBSERVACION", unique = true)
	private Observacion observacion;

	@Column(name = "FIABILIDAD", length = 1)
	private char fiabilidad;

	@Column(name = "COMENTARIO", length = 200)
	private String comentario;

	@Column(name = "VALOR_FECHA")
	private Date valorFecha;
	
	@Column(name = "FECHA")
	private Date fecha;

	public ObservacionRevision() {
		super();
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Observacion getObservacion() {
		return observacion;
	}
	public void setObservacion(Observacion observacion) {
		this.observacion = observacion;
	}
	public char getFiabilidad() {
		return fiabilidad;
	}
	public void setFiabilidad(char fiabilidad) {
		this.fiabilidad = fiabilidad;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Date getValorFecha() {
		return valorFecha;
	}
	public void setValorFecha(Date valorFecha) {
		this.valorFecha = valorFecha;
	}

	public ObservacionRevisionPK getId() {
		return id;
	}

	public void setId(ObservacionRevisionPK id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}