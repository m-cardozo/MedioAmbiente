package com.excepciones;

import javax.faces.application.FacesMessage;
import javax.validation.ConstraintViolationException;

public class ExceptionsTools {

	public static Throwable getCause(Throwable e) {
		Throwable cause = null;
		Throwable result = e;

		while (null != (cause = result.getCause()) && (result != cause)) {
			result = cause;

			if (result instanceof ConstraintViolationException) {
				return result;
			}
		}

		return result;
	}

	public static String formatedMsg(Throwable ex) {
		return "[" + ex.getClass().getSimpleName() + "] " + ex.getLocalizedMessage();
	}

	public static FacesMessage NotificarError(String msg1, String msg2) {
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, msg1, msg2);
	}

	public static FacesMessage NotificarExito(String msg1, String msg2) {
		return new FacesMessage(FacesMessage.SEVERITY_INFO, msg1, msg2);
	}
}