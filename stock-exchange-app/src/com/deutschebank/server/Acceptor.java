package com.deutschebank.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

import com.deutschebank.client.Client;
import com.deutschebank.exceptions.ParseException;
import com.deutschebank.exchange.ExchangeService;
import com.deutschebank.order.Order;

public class Acceptor implements Runnable {
	private Logger log = Logger.getLogger(Acceptor.class.getName());
	private Parser parser = new Parser();
	private Client client;
	private Socket connection;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private ExchangeService service;

	public Acceptor(Socket socket, ExchangeService service) {
		this.connection = socket;
		this.service = service;
	}

	@Override
	public void run() {
		try {
			out = new ObjectOutputStream(connection.getOutputStream());
			in = new ObjectInputStream(connection.getInputStream());

			out.flush();

			initClient();

			requestLoop();

		} catch (IOException ioException) {
			log.warning("connection failed, cause: " + ioException);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO: handle exception
		} finally {
			closeConnection();
		}
	}

	private void closeConnection() {
		try {
			connection.close();
		} catch (IOException ioException) {
			log.warning("can't close connection, cause: " + ioException);
		}
	}

	private void initClient() throws IOException, ParseException,
			ClassNotFoundException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String message = (String) in.readObject();
		MessageType type = parser.getTypeFromString(message);
		if (MessageType.NEW_CLIENT != type) {
			closeConnection();
		}

		client = parser.getClientFromString(message, this);
		sendMessage("correct login");
	}

	private void requestLoop() throws IOException, ClassNotFoundException {
		String message = "";
		boolean closeConnection = false;
		do {

			message = (String) in.readObject();
			log.info("client>" + message);

			MessageType type;
			try {
				type = parser.getTypeFromString(message);

				switch (type) {
				case NEW_ORDER:
					Order order = parser.getOrderFromString(message);
					sendMessage("correct");
					service.add(client, order);
					break;
				case CLOSE_CONNECTION:
					closeConnection = true;
					break;
				default:
					;
				}

			} catch (ParseException e) {
				log.warning("incorrect parse " + e);
				sendMessage("incorrect order");
			}
		} while (!closeConnection);
	}

	void sendMessage(final String msg) {
		try {
			out.writeObject(msg);
			out.flush();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public void matchNotify(Order order) {
		sendMessage("oder matches:" + order.getClientId());
	}

}
