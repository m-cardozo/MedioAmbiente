package com.modelo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.entidades.ObservacionRevisionPK;


public class ObservacionRevisionGui {

	@NotNull
	private ObservacionRevisionPK id;

	@NotNull
	private ObservacionGui observacionGui;

	@NotNull
	private UsuarioGui usuarioGui;

	@NotNull
	private char fiabilidad;

	@NotNull
	private String comentario;

	@NotNull
	private Date valorFecha;
	
	@NotNull
	private Date fecha;

	public ObservacionRevisionGui() {
		super();
		id=new ObservacionRevisionPK();
		observacionGui=new ObservacionGui();
		usuarioGui=new UsuarioGui();
		
	}

	public ObservacionRevisionGui(@NotNull ObservacionGui observacionGui, @NotNull UsuarioGui usuarioGui, @NotNull char fiabilidad,
			@NotNull String comentario, @NotNull Date valorFecha, @NotNull Date fecha) {
		super();
		this.observacionGui = observacionGui;
		this.usuarioGui = usuarioGui;
		this.fiabilidad = fiabilidad;
		this.comentario = comentario;
		this.valorFecha = valorFecha;
		this.fecha = fecha ;
	}

	
	public ObservacionRevisionPK getId() {
		return id;
	}

	public void setId(ObservacionRevisionPK id) {
		this.id = id;
	}

	public ObservacionGui getObservacionGui() {
		return observacionGui;
	}
	public void setObservacionGui(ObservacionGui observacionGui) {
		this.observacionGui = observacionGui;
	}
	public UsuarioGui getUsuarioGui() {
		return usuarioGui;
	}
	public void setUsuarioGui(UsuarioGui usuarioGui) {
		this.usuarioGui = usuarioGui;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}