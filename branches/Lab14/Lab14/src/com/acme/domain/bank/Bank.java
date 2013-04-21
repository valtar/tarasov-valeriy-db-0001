package com.acme.domain.bank;

import java.io.Serializable;

public class Bank implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Client[] clients = new Client[10];
	private int maxClients = 0;
	
	public Client addClient(final Client client) {
		clients[maxClients++] = client;
		return client;
	}
	
	public boolean consistClient(Client client){
		if(client == null) return false;
		
		boolean  consist = false;
		for (int i = 0; i < maxClients; i++) {
			if(client.equals(clients[i])){
				consist = true;
				break;
			}
		}
		
		return consist;
	}

}
