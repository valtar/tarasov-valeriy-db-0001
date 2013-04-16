package com.acme.domain.bank;

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
			System.out.println(clients[i].getStringClientSalutation()
					+ "'s balance is: " + clients[i].getAccount().getBalance());
		}
		;
	}

	public boolean addClient(Client newClient) {
		if (clientsCount >= MAX_CLIENTS)
			return false;
		clients[clientsCount++] = newClient;
		return true;
	}

	public void printMaximumAccountToWithdraw() {
		for (int i = 0; i < MAX_CLIENTS; i++) {
			System.out.println(clients[i].getAccount()
					.maximumAmountToWithdraw());
		}
	}

}
