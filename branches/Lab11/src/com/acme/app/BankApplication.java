package com.acme.app;
import static com.acme.service.BankService.*;

import com.acme.domain.client.Client;
import com.acme.domain.client.Gender;
import com.acme.exceptions.OverDraftLimitExceededException;

public class BankApplication {
	Bank bank = new Bank();

	public static void main(String[] args) {
		BankApplication ba = new BankApplication();
		addClient(ba.bank, new Client("Bill",Gender.MALE));
		try {
			new Client("Ginger", Gender.FEMALE).getAccount(0).withdraw(200);
		} catch (OverDraftLimitExceededException e) {
			e.printStackTrace();
			System.out.println("not enough money, available only " + e.getMaxAmount());
		}
	}

	public void modifyBank(double amount) {
		int i = 0;
		for (Client client : bank.getClients()) {
			client.getAccount(++i).deposit(amount);
		}
	}

	public void printBalance() {
		bank.printBalance();
	}

}
