package com.entidades;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ObservacionRevisionPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long usuarioId;

	private Long observacionId;

	public ObservacionRevisionPK() {
		super();
	}

	public ObservacionRevisionPK(Long usuarioId, Long observacionId) {
		super();
		this.usuarioId = usuarioId;
		this.observacionId = observacionId;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Long getObservacionId() {
		return observacionId;
	}
	public void setObservacionId(Long observacionId) {
		this.observacionId = observacionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());
		result = prime * result + ((observacionId == null) ? 0 : observacionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		ObservacionRevisionPK other = (ObservacionRevisionPK) obj;
		
		if (usuarioId == null) {
			if (other.usuarioId != null) {
				return false;
			}
		} else if (!usuarioId.equals(other.usuarioId)) {
			return false;
		}
		
		if (observacionId == null) {
			if (other.observacionId != null) {
				return false;
			}
		} else if (!observacionId.equals(other.observacionId)) {
			return false;
		}
		return true;
	}
}