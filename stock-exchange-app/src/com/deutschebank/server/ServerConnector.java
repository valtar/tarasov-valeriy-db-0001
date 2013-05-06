package com.deutschebank.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.deutschebank.exchange.ExchangeService;
import com.sun.istack.internal.logging.Logger;

public class ServerConnector {
	private ServerSocket providerSocket = null;
	private Logger log = Logger.getLogger(ServerConnector.class);
	private boolean isClosed = false;

	public synchronized void startServer(int port, int backlog, ExchangeService service) {
		try {
			providerSocket = new ServerSocket(port, backlog);
		} catch (IOException e) {
			log.warning("server failed, cause: ", e);
			try {
				providerSocket.close();
			} catch (IOException e1) {
				log.warning("can't close server, cause: ", e1);
			}
		}

		Socket connection = null;
		while (!isClosed) {
			try {
				connection = providerSocket.accept();
				new Thread(new Acceptor(connection,service)).start();
				log.info("new client has been connected");
			} catch (IOException e) {
				log.warning("server failed, cause: ", e);
			} catch (Throwable e) {
				log.severe("server failed, cause: ", e);
			}
		}

		try {
			providerSocket.close();
		} catch (IOException e) {
			log.warning("can't close server, cause: ", e);
		}
	}

	public void closeServer() {
		isClosed = true;
	}

}
