package com.deutschebank.exceptions;

public class IllegalPriceException extends ParseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalPriceException() {
		super();
	}

	public IllegalPriceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IllegalPriceException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalPriceException(String message) {
		super(message);
	}

	public IllegalPriceException(Throwable cause) {
		super(cause);
	}

}
