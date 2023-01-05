package com.dnt.cloud.common.domain.exception;

public class OpenException extends Exception {

	private static final long serialVersionUID = -8666234314406667151L;

	public OpenException() {
		super();
	}

	public OpenException(String message) {
		super(message);
	}

	public OpenException(String message, Throwable cause) {
		super(message, cause);
	}

	public OpenException(Throwable cause) {
		super(cause);
	}

	protected OpenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
