package org.slf4j.impl;

public enum LogLevel {
	INFO("[INFO]"), DEBUG("[DEBUG]"), ERROR("[ERROR]");
	
	private String s;

	LogLevel(String s){
		this.s = s;
	}

	@Override
	public String toString() {
		return s;
	}
	
	

}
