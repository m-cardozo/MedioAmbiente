package com.entidades;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ObservacionCaracteristicaPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long caracteristicaId;

	private Long observacionId;

	public ObservacionCaracteristicaPK() {
		super();
	}

	public ObservacionCaracteristicaPK(Long caracteristicaId, Long observacionId) {
		super();
		this.caracteristicaId = caracteristicaId;
		this.observacionId = observacionId;
	}

	public Long getCaracteristicaId() {
		return caracteristicaId;
	}
	public void setCaracteristicaId(Long caracteristicaId) {
		this.caracteristicaId = caracteristicaId;
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
		result = prime * result + ((caracteristicaId == null) ? 0 : caracteristicaId.hashCode());
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
		
		ObservacionCaracteristicaPK other = (ObservacionCaracteristicaPK) obj;
		
		if (caracteristicaId == null) {
			if (other.caracteristicaId != null) {
				return false;
			}
		} else if (!caracteristicaId.equals(other.caracteristicaId)) {
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