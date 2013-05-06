package com.deutschebank.exceptions;

public class StockTypeFormatException extends ParseException {

	public StockTypeFormatException() {
		super();
	}

	public StockTypeFormatException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public StockTypeFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public StockTypeFormatException(String message) {
		super(message);
	}

	public StockTypeFormatException(Throwable cause) {
		super(cause);
	}

}
