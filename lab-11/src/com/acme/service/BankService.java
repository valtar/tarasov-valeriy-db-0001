package com.acme.service;

import com.acme.app.Bank;
import com.acme.domain.client.Client;

public class BankService {
	public static void addClient(Bank bank, Client client) {
		bank.addClient(client);
	}

	public static void printBalance(Bank bank) {
		bank.printBalance();
	}

	public static Client[] getClients(Bank bank) {
		return bank.getClients();
	}

}
