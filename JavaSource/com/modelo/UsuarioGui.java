package com.modelo;

import javax.validation.constraints.NotNull;

public class UsuarioGui {

	@NotNull
	private Long usuarioId;

	@NotNull
	private String nombre;

	@NotNull
	private String apellido;

	@NotNull
	private String usuario;

	@NotNull
	private String clave;

	@NotNull
	private TipoDocumentoGui tipoDocumentoGui;

	@NotNull
	private String nroDocumento;

	@NotNull
	private String direccion;

	@NotNull
	private String mail;

	@NotNull
	private String estado;

	@NotNull
	private RolGui rolGui;

	public UsuarioGui() {
		super();
		tipoDocumentoGui=new TipoDocumentoGui();
		rolGui=new RolGui();
	}

	public UsuarioGui(@NotNull Long usuarioId, @NotNull String nombre, @NotNull String apellido,
			@NotNull String usuario, @NotNull String clave, @NotNull TipoDocumentoGui tipoDocumentoGui,
			@NotNull String nroDocumento, @NotNull String direccion, @NotNull String mail, @NotNull String estado,
			@NotNull RolGui rolGui) {
		super();
		this.usuarioId = usuarioId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.clave = clave;
		this.tipoDocumentoGui = tipoDocumentoGui;
		this.nroDocumento = nroDocumento;
		this.direccion = direccion;
		this.mail = mail;
		this.estado = estado;
		this.rolGui = rolGui;
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
	public TipoDocumentoGui getTipoDocumentoGui() {
		return tipoDocumentoGui;
	}
	public void setTipoDocumentoGui(TipoDocumentoGui tipoDocumentoGui) {
		this.tipoDocumentoGui = tipoDocumentoGui;
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
	public RolGui getRolGui() {
		return rolGui;
	}
	public void setRolGui(RolGui rolGui) {
		this.rolGui = rolGui;
	}
	
	
}