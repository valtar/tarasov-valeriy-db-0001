package com.acme.domain.bank;

import java.io.Serializable;

public class Bank implements Serializable {

	private final Client[] clients = new Client[10];
	private int maxClients = 0;
	
	public Client addClient(final Client client) {
		clients[maxClients++] = client;
		
		
		return client;
	}

}
