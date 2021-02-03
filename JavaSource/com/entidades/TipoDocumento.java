package com.entidades;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "TIPOS_DOCUMENTOS")
public class TipoDocumento implements Serializable {	   

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long tipoDocumentoId;

	@Column(name = "DESCRIPCION", unique = true, length = 20)
	private String descripcion;

	@OneToMany(mappedBy = "tipoDocumento")
	private List<Usuario> usuarios;

	public TipoDocumento() {
		super();
	}

	public TipoDocumento(Long tipoDocumentoId, String descripcion) {
		super();
		this.tipoDocumentoId = tipoDocumentoId;
		this.descripcion = descripcion;
	}

	public Long getTipoDocumentoId() {
		return tipoDocumentoId;
	}
	public void setTipoDocumentoId(Long tipoDocumentoId) {
		this.tipoDocumentoId = tipoDocumentoId;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}