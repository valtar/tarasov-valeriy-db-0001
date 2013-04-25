package com.acme.domain.email.impl;

public class AddToClosedQueueException extends Exception {

	public AddToClosedQueueException() {
		super();
	}

	public AddToClosedQueueException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AddToClosedQueueException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddToClosedQueueException(String message) {
		super(message);
	}

	public AddToClosedQueueException(Throwable cause) {
		super(cause);
	}

}
