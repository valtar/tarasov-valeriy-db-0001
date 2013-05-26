package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class LoggerFactory implements ILoggerFactory {
	private Appender appender = new Appender();
	private Logger logger = new MyLogger(appender);
	
	@Override
	public Logger getLogger(String name) {
		return logger;
	}

}
