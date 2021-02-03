package com.entidades;

import java.io.Serializable;
import java.util.List;

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

@Entity
@Table(name = "USUARIOS")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long usuarioId;

	@Column(name = "NOMBRE", length = 50)
	private String nombre;

	@Column(name = "APELLIDO", length = 50)
	private String apellido;

	@Column(name = "USUARIO", unique = true, length = 50)
	private String usuario;

	@Column(name = "CLAVE", length = 50)
	private String clave;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TIPO_DOCUMENTO")
	private TipoDocumento tipoDocumento;

	@Column(name = "NRO_DOCUMENTO", unique = true, length = 20)
	private String nroDocumento;

	@Column(name = "DIRECCION", length = 50)
	private String direccion;

	@Column(name = "MAIL", unique = true, length = 50)
	private String mail;

	@Column(name = "ESTADO", length = 10)
	private String estado;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ROL")
	private Rol rol;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	private List<Observacion> observaciones;

	public Usuario() {
		super();
		rol=new Rol();
		tipoDocumento=new TipoDocumento();
	}

	public Usuario(Long usuarioId, String nombre, String apellido, String usuario, String clave,
			TipoDocumento tipoDocumento, String nroDocumento, String direccion, String mail, String estado, Rol rol) {
		super();
		this.usuarioId = usuarioId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.clave = clave;
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.direccion = direccion;
		this.mail = mail;
		this.estado = estado;
		this.rol = rol;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public List<Observacion> getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(List<Observacion> observaciones) {
		this.observaciones = observaciones;
	}
	
}