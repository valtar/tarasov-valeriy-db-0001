package com.acme.domain.account;

import com.acme.exceptions.NotEnoughFundsException;


public class SavingAccount extends AbstractAccount {

	public SavingAccount(final int id, final double amount) {
		super(id, amount);
	}

	@Override
	public void deposit(final double amount) throws IllegalArgumentException {
		if (amount < 0) {
			throw new IllegalArgumentException("Negative amount to deposit");	
		}
		this.balance += amount;
	}

	@Override
	public void withdraw(final double amount) throws NotEnoughFundsException, IllegalArgumentException {
		if (amount < 0) {
			throw new IllegalArgumentException("Negative amount to withdraw");
		}
		if (this.balance - amount >= 0) {
			this.balance -= amount;
		} else {
			throw new NotEnoughFundsException(amount);
		}

	}

	public AccountType getAccountType() {
		return AccountType.SAVING;
	}

}
