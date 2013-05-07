package com.deutschebank.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class Connector {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket requestSocket;
	private boolean b;
	private String host;
	private int port;
	private Logger log = Logger.getLogger(Connector.class.getName());

	public Connector(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	public boolean connect() {
		try {
			requestSocket = new Socket(host, port);

			System.out.println("Connected to localhost in port 2004");

			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			log.info("connect to host: " + host + ";port: " + port);
			readMessage();
			return true;
		} catch (IOException e) {
			log.warning("can't connect to host: " + host + ";port: " + port);
			return false;
		}
	}
	
	public boolean sendMessage(String s){
		
		try {
			out.writeObject(s);
		} catch (IOException e) {
			log.warning("can't send message to server");
			return false;
		}
		return true;
	}
	
	public void readMessage(){
		new Thread(){

			@Override
			public void run() {
				String msg = null;
				while(b){
					try {
						msg = (String) in.readObject();
						System.out.println(msg);
					} catch (ClassNotFoundException | IOException e) {
						log.warning(e.toString());
					}
				}
			}
			
		}.start();
	}
	
	
	
	public void closeConnection(){
		try{
			requestSocket.close();
		}catch(IOException e){
			log.warning("can't close connection");
		}
	}

}
