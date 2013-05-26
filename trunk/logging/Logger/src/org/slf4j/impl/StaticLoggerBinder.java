package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

public class StaticLoggerBinder implements LoggerFactoryBinder {

	private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();

	private ILoggerFactory loggerFactory = new LoggerFactory();

	public static String REQUESTED_API_VERSION = "1.6.99";

	@Override
	public ILoggerFactory getLoggerFactory() {
		return loggerFactory;
	}

	@Override
	public String getLoggerFactoryClassStr() {
		return LoggerFactory.class.toString();
	}

	public static final StaticLoggerBinder getSingleton() {
		return SINGLETON;
	}

}
