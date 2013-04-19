package com.acme;

import com.acme.domain.bank.Account;
import com.acme.domain.bank.Bank;
import com.acme.domain.bank.CheckingAccount;
import com.acme.domain.bank.Client;
import com.acme.domain.bank.Gender;
import com.acme.domain.bank.SavingAccount;

public class BankApplication {
	public static void main(String[] args) {
		Bank bank = new Bank();
		Client c1 = new Client(new SavingAccount(100), Gender.MALE, "Steve");
		bank.addClient(c1);
		System.out.println();

		Account account = new CheckingAccount(100, 200);

		System.out.println(account.getBalance() + " "
				+ account.maximumAmountToWithdraw());

		account.withdraw(150);

		System.out.println(account.getBalance() + " "
				+ account.maximumAmountToWithdraw());

		account.withdraw(1000);

		System.out.println(account.getBalance() + " "
				+ account.maximumAmountToWithdraw());

	}

}
