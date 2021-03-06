package com.deutschebank.server;

import com.deutschebank.client.Client;
import com.deutschebank.exceptions.ParseException;
import com.deutschebank.order.Order;
import com.deutschebank.order.OrderType;
import com.deutschebank.stock.StockType;

public class Parser {
	private final String DELIMERT = ";";

	public MessageType getTypeFromString(String message) throws ParseException {
		String[] ss = message.split(DELIMERT);
		if (ss.length < 1) {
			throw new ParseException("illegal number of arguments: "
					+ ss.length);
		}
		MessageType type = null;
		String sMessageType = ss[LoginRequest.TYPE_OPERATION.ordinal()];
		type = MessageType.parseMessageType(sMessageType);

		return type;
	}

	enum OrderRequest {
		OPERATION_TYPE, ID_REQUEST, ORDER_TYPE, STOCK_TYPE, PRICE, NUMBER_OF_SHARES;

		public static int amountOfArguments() {
			return OrderRequest.values().length;
		}
	}

	// "ORDER;1;ASK;GLD;95;10";
	public Order getOrderFromString(String message) throws ParseException {
		String[] ss = message.split(DELIMERT);
		if (ss.length != OrderRequest.amountOfArguments()) {
			throw new ParseException("illegal number of arguments: "
					+ ss.length);
		}
		MessageType messageType = MessageType
				.parseMessageType(ss[OrderRequest.OPERATION_TYPE.ordinal()]);
		if (messageType != MessageType.NEW_ORDER) {
			throw new ParseException("illegal order comand");
		}

		float price = 0;
		OrderType orderType = null;
		StockType stockType = null;
		int nShares = 0;
		int clientOrderId = 0;

		orderType = OrderType.parseOrderType(ss[OrderRequest.ORDER_TYPE
				.ordinal()]);
		stockType = StockType.parseStockType(ss[OrderRequest.STOCK_TYPE
				.ordinal()]);
		try {
			nShares = Integer.parseInt(ss[OrderRequest.NUMBER_OF_SHARES
					.ordinal()]);
			price = Float.parseFloat(ss[OrderRequest.PRICE.ordinal()]);
			clientOrderId = Integer.parseInt(ss[OrderRequest.ID_REQUEST
					.ordinal()]);
		} catch (NumberFormatException e) {
			throw new ParseException("illegal numeric arguments", e);
		}

		Order order = new Order(price, orderType, nShares, clientOrderId,
				stockType);
		return order;
	}

	// "LOGIN;IVAN"
	enum LoginRequest {
		TYPE_OPERATION, LOGIN;
		public static int amountOfArguments() {
			return LoginRequest.values().length;
		}
	}

	public Client getClientFromString(String message, Acceptor acceptor)
			throws ParseException {
		String[] ss = message.split(DELIMERT);
		if (ss.length != LoginRequest.amountOfArguments()) {
			new ParseException("illegal number of arguments: " + ss.length);
		}

		String sMessageType = ss[LoginRequest.TYPE_OPERATION.ordinal()];
		MessageType type = MessageType.parseMessageType(sMessageType);

		if (type != MessageType.NEW_CLIENT) {
			throw new ParseException("wrong argument to parse client");
		}

		String name = ss[LoginRequest.LOGIN.ordinal()];
		Client client = new Client(name, acceptor);
		return client;

	}

}
