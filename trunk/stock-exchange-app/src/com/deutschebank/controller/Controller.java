package com.deutschebank.controller;
import com.deutschebank.exchange.ExchangeService;
import com.deutschebank.server.ServerConnector;


public class Controller {
	public static void main(String[] args) {
		ExchangeService es = new ExchangeService();
		ServerConnector connector = new ServerConnector();
		connector.startServer(2004, 10, es);
	}
}
