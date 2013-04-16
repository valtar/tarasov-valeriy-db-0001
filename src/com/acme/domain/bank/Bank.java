package com.acme.domain.bank;

import com.acme.ClientRegistrationListener;

public class Bank {
	private static final int MAX_CLIENTS = 10;
	private static final int MAX_LISTENERS = 10;
	private int clientsCount = 0;
	private int listenersCount = 0;
	private Client[] clients = new Client[MAX_CLIENTS];
	private ClientRegistrationListener[] clientRegistrationListeners = new ClientRegistrationListener[MAX_LISTENERS];

	
	public static void main(String[] args) {
		Bank bank = new Bank();
		bank.addClient(new Client(new SavingAccount(1),Gender.MALE,"Bill"));
	}
	
	public Bank() {
		class DebugListener implements ClientRegistrationListener {

			@Override
			public void onClientAdded(Client client) {
				System.out.println("Client " + client.getStringClientSalutation() + " added at "
						+ new java.util.Date());
			}
		}

		addClientRegistrationListener(new DebugListener());

	}

	public boolean addClientRegistrationListener(
			ClientRegistrationListener clientRegistrationListener) {
		if (listenersCount >= MAX_LISTENERS)
			return false;
		clientRegistrationListeners[listenersCount++] = clientRegistrationListener;
		return true;
	}

	private void alertClientRegistrationListener(Client client) {
		for (int i = 0; i < listenersCount; i++) {
			clientRegistrationListeners[i].onClientAdded(client);
		}
	}

	public boolean addClient(Client newClient) {
		if (clientsCount >= MAX_CLIENTS)
			return false;
		clients[clientsCount++] = newClient;
		alertClientRegistrationListener(newClient);
		return true;

	}

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

	public void printMaximumAccountToWithdraw() {
		for (int i = 0; i < MAX_CLIENTS; i++) {
			System.out.println(clients[i].getAccount()
					.maximumAmountToWithdraw());
		}
	}

}
