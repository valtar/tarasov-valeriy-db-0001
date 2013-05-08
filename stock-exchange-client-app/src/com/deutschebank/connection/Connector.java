package com.deutschebank.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

import com.deutschebank.controller.Controller;

public class Connector {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket requestSocket;
	private boolean b = true;
	private String host;
	private int port;
	private Logger log = Logger.getLogger(Connector.class.getName());
	Controller controller;

	public Connector(String host, int port, Controller controller) {
		super();
		this.host = host;
		this.port = port;
		this.controller = controller;
	}

	public boolean connect() throws IOException {
		try {
			requestSocket = new Socket(host, port);

			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			log.info("connect to host: " + host + ";port: " + port);
			readMessage();
			return true;
		} catch (IOException e) {
			log.warning("can't connect to host: " + host + ";port: " + port);
			b = false;
			throw new IOException(e);
		}
	}

	public boolean sendMessage(String s) {

		try {
			log.info("sending message :" + s);
			out.writeObject(s);
		} catch (IOException e) {
			log.warning("can't send message to server");
			return false;
		}
		return true;
	}

	public void readMessage() {
		final Parser parser = new Parser(controller);
		Thread t = new Thread() {

			@Override
			public void run() {
				String msg = null;
				try {
					while (b) {
						try {
							msg = (String) in.readObject();
							log.info("server>" + msg);
							parser.parse(msg);

						} catch (ClassNotFoundException e) {
							log.warning(e.toString());
						}
					}
				} catch (IOException e) {
					log.warning(e.toString());
				}
			}

		};
		t.setDaemon(true);
		t.start();
	}

	public void closeConnection() {
		b = false;
		try {
			requestSocket.close();
			log.info("connection closed");
		} catch (IOException e) {
			log.warning("can't close connection");
		}
	}

}
