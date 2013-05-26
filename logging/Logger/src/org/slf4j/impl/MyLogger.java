package org.slf4j.impl;


public class MyLogger extends LoggerAdaptor {
	
	Appender appender;

	public MyLogger(Appender appender) {
		this.appender = appender;
	}

	private void log(String msg){
		appender.append(msg);
	}
	
	@Override
	public void debug(String msg) {
		log(LogLevel.DEBUG + " " + msg);
	}

	@Override
	public void debug(String msg, Object arg) {
		log(LogLevel.DEBUG + " " + msg + " " + arg.toString());
	}

	@Override
	public void info(String msg) {
		log(LogLevel.INFO + " " + msg);
	}

	@Override
	public void info(String msg, Object arg) {
		log(LogLevel.INFO + " " + msg + " " + arg.toString());
	}

	@Override
	public void error(String msg) {
		log(LogLevel.ERROR + " " + msg);
	}

	@Override
	public void error(String msg, Object arg) {
		log(LogLevel.ERROR + " " + msg + " " + arg.toString());
	}
	
	

	

}
