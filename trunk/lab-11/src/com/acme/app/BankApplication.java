package com.acme.app;

import com.acme.domain.account.Account;
import com.acme.domain.account.CheckingAccount;
import com.acme.domain.account.SavingAccount;
import com.acme.exceptions.NotEnoughFundsException;
import com.acme.exceptions.OverDraftLimitExceededException;

public class BankApplication {
	Bank bank = new Bank();

	public static void main(String[] args) {
		Account ca = new CheckingAccount(0, 200, 100);

		withdrow(ca, 10);
		System.out.println(ca.getBalance());

		withdrow(ca, 1000);
		withdrow(ca, -10);
		System.out.println(ca.getBalance());

		Account sa = new SavingAccount(1, 100);
		withdrow(sa, 1);
		System.out.println(sa.getBalance());
		withdrow(sa, 101);
		withdrow(sa, -1);

	}

	private static void withdrow(Account account, int amount) {
		try {
			account.withdraw(amount);
		} catch (OverDraftLimitExceededException e) {
			System.out.println("not enough money, available only "
					+ e.getMaxAmount());
		} catch (NotEnoughFundsException e) {
			System.out.println("not enough money, available only "
					+ e.getAmount());
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
	}

	public void printBalance() {
		bank.printBalance();
	}

}
