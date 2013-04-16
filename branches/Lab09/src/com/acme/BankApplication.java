package com.acme;

import static com.acme.service.bank.BankService.addClient;

import com.acme.domain.bank.Bank;
import com.acme.domain.bank.Client;
import com.acme.domain.bank.Gender;
import com.acme.domain.bank.SavingAccount;

public class BankApplication {
	Bank bank = new Bank();

	public static void main(String[] args) {
		BankApplication ba = new BankApplication();

		addClient(ba.bank, new Client(new SavingAccount(Math.random()),
				Gender.FEMALE, "BillGates"));

		// ba.bank.addClient(new Client( new Account( Math.random() ),
		// Gender.FEMALE, "BillGates" ));
		// ba.bank.addClient(new Client( new Account( Math.random() ),
		// Gender.MALE, "SRV" ));
		// ba.bank.addClient(new Client( new Account( Math.random() ),
		// Gender.MALE, "HarryMoore" ));
		//
		// ba.printBalance();
	}

	public void modifyBank(double amount) {
		for (Client client : bank.getClients()) {
			client.getAccount().deposit(amount);
		}
	}

	public void printBalance() {
		bank.printBalance();
	}

}
