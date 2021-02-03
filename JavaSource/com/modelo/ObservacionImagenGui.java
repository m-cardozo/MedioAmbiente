package com.modelo;

import javax.validation.constraints.NotNull;

public class ObservacionImagenGui {

	@NotNull
	private Long observacionImagenId;

	@NotNull
	private String path;

	@NotNull
	private ObservacionGui observacionGui;

	public ObservacionImagenGui() {
		super();
	}

	public ObservacionImagenGui(@NotNull Long observacionImagenId, @NotNull String path, @NotNull ObservacionGui observacionGui) {
		super();
		this.observacionImagenId = observacionImagenId;
		this.path = path;
		this.observacionGui = observacionGui;
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
	public ObservacionGui getObservacionGui() {
		return observacionGui;
	}
	public void setObservacionGui(ObservacionGui observacionGui) {
		this.observacionGui = observacionGui;
	}
}