package com.acme;

import static com.acme.service.bank.BankService.addClient;

import java.util.Random;

import com.acme.domain.bank.Bank;
import com.acme.domain.bank.CheckingAccount;
import com.acme.domain.bank.Client;
import com.acme.domain.bank.Gender;
import com.acme.domain.bank.SavingAccount;

public class BankApplication {
	private Bank bank = new Bank();

	public static void main(String[] args) {
		BankApplication ba = new BankApplication();
		Client c1 = new Client(new SavingAccount(Math.random() * 100),
				Gender.MALE, "BillGates");
		Client c2 = new Client(new SavingAccount(Math.random() * 100),
				Gender.MALE, "GarryMoore");
		Client c3 = new Client(new CheckingAccount(Math.random() * 100, 100),
				Gender.MALE, "SRV");

		addClient(ba.getBank(), c1);
		addClient(ba.getBank(), c2);
		addClient(ba.getBank(), c3);
		ba.printBalance();

		ba.modifyBank(40);
		ba.printBalance();

		ba.modifyBank(100);
		ba.printBalance();

		System.out.println(c1.getAccount().maximumAmountToWithdraw());
		System.out.println(c3.getAccount().maximumAmountToWithdraw());
		System.out.println();
		
		System.out.println( c3.getAccount().withdraw(1000) );
		System.out.println( c3.getAccount().withdraw(100) );
		System.out.println( c1.getAccount().withdraw(1) );
		System.out.println();
		
		ba.printBalance();

	}

	public void modifyBank(double amount) {
		Random rand = new Random();
		for (Client client : bank.getClients()) {
			if (rand.nextBoolean()) {
				client.getAccount().withdraw(amount);
			}
		}
	}

	public void printBalance() {
		bank.printBalance();
		System.out.println();
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

}
