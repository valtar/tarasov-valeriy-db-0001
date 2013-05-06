package com.deutschebank.exceptions;

public class MessageTypeFormatException extends ParseException {

	public MessageTypeFormatException() {
		super();
	}

	public MessageTypeFormatException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MessageTypeFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessageTypeFormatException(String message) {
		super(message);
	}

	public MessageTypeFormatException(Throwable cause) {
		super(cause);
	}

}
