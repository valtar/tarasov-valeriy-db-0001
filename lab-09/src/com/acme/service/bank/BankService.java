package com.acme.service.bank;

import com.acme.domain.bank.Bank;
import com.acme.domain.bank.Client;

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

	public static void printMaximumAccountToWithdraw(Bank bank) {
		bank.printMaximumAccountToWithdraw();

	}
}
