package com.deutschebank.server;

import java.io.IOException;
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

	enum ServerMessage {
		ACCEPT, REJECT, MATCH, LOGINSUCCESS, LOGINERROR
	}

	@Override
	public void run() {
		try {
			out = new ObjectOutputStream(connection.getOutputStream());
			in = new ObjectInputStream(connection.getInputStream());

			out.flush();
			requestLoop();

		} catch (IOException ioException) {
			log.warning("connection failed, cause: " + ioException);
		} finally {
			closeConnection();
		}
	}

	private void closeConnection() {
		service.deleteClientOrders(client);
		try {
			connection.close();
			log.info("client " + client.getName() + " is loged out");
		} catch (IOException ioException) {
			log.warning("can't close connection, cause: " + ioException);
		}
	}

	private void initClient() {
		boolean isLogined = false;
		do {
			try {
				String message = (String) in.readObject();
				MessageType type = parser.getTypeFromString(message);
				if (MessageType.CLOSE_CONNECTION == type) {
					closeConnection();
				}
				if (MessageType.NEW_CLIENT != type) {
					sendMessage(ServerMessage.LOGINERROR.toString());
				}

				client = parser.getClientFromString(message, this);
				log.info("client " + client.getName() + " log in");
				sendMessage(ServerMessage.LOGINSUCCESS.toString());
				isLogined = true;
			} catch (ParseException e) {
				sendMessage(ServerMessage.LOGINERROR.toString());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				isLogined = false;
			}
		} while (!isLogined);
	}

	private void requestLoop() {
		initClient();
		String message = "";
		boolean isClosed = false;
		MessageType type;
		do {
			try {
				message = (String) in.readObject();
				log.info("client " + client.getName() + ">" + message);

				type = parser.getTypeFromString(message);

				switch (type) {
				case NEW_ORDER:
					Order order = parser.getOrderFromString(message);
					sendMessage(ServerMessage.ACCEPT.toString());
					service.add(client, order);
					break;
				case CLOSE_CONNECTION:
					sendMessage(ServerMessage.ACCEPT.toString());
					isClosed = true;
					break;
				default:
					sendMessage(ServerMessage.REJECT.toString());
				}

			} catch (ParseException e) {
				log.warning("incorrect parse " + e);
				sendMessage(ServerMessage.REJECT.toString());
			} catch (ClassNotFoundException e) {
				log.warning("incorrect " + e);
			} catch (IOException e) {
				log.warning("incorrect " + e);
				isClosed = true;
			}

		} while (!isClosed);
	}

	void sendMessage(final String msg) {
		try {
			out.writeObject(msg);
			out.flush();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public String constructMatchMessage(Order orderOwner, String counterparty,
			float price) {
		return ServerMessage.MATCH.toString() + ";" + orderOwner.getClientId()
				+ ";" + counterparty + ";" + price + ";"
				+ orderOwner.getAmount();
	}

	public void matchNotify(Order orderOwner, String counterparty, float price) {
		sendMessage(constructMatchMessage(orderOwner, counterparty, price));
	}

}
