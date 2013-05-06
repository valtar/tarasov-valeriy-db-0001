package com.deutschebank.exceptions;

public class OrderTypeFormatException extends ParseException {

	public OrderTypeFormatException() {
		super();
	}

	public OrderTypeFormatException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OrderTypeFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderTypeFormatException(String message) {
		super(message);
	}

	public OrderTypeFormatException(Throwable cause) {
		super(cause);
	}

}
