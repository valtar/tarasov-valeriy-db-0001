package com.deutschebank.model;

import com.deutschebank.connection.Connector;

public class Model {
	private final String DELIMETER = ";";
	private Connector connector;

	public boolean loginAdded(String s) {
		connector.sendMessage(MessageType.LOGIN.toString() + DELIMETER + s);
		return true;
	}

	public void connectServer() {
		if (connector == null) {
			connector = new Connector("localhost", 2004);
			connector.connect();
		}
	}
	
	public void sendOrder(){
		
	}

}
