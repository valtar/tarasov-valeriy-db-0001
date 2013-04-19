package com.acme.app;

import com.acme.domain.client.Client;

public class Bank {
	private static final int MAX_CLIENTS = 10;
	private Client[] clients = new Client[MAX_CLIENTS];
	private int clientsCount = 0;

	public Client[] getClients() {
		Client[] another = new Client[clients.length];
		for (int i = 0; i < clients.length; i++) {
			another[i] = clients[i];
		}
		return another;
	}

	public void printBalance() {
		for (int i = 0; i < clientsCount; i++) {
			System.out.println(clients[i].getSalutation()
					+ "'s balance is: " + clients[i].getAccount(i).getBalance());
		}
		;
	}

	public boolean addClient(Client newClient) {
		if (clientsCount >= MAX_CLIENTS)
			return false;
		clients[clientsCount++] = newClient;
		return true;
	}

}
