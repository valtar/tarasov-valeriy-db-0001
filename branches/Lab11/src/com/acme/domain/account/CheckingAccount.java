package com.acme.domain.account;

import com.acme.exceptions.OverDraftLimitExceededException;

public class CheckingAccount extends AbstractAccount {

	private double overdraft;

	public CheckingAccount(final int id, final double amount,
			final double overdraft) {
		super(id, amount);
		this.overdraft = overdraft;
	}

	public void withdraw(final double amount)
			throws OverDraftLimitExceededException, IllegalArgumentException {
		if (amount < 0) {
			throw new IllegalArgumentException("Negative amount to withdraw");
		}

		if (amount <= getBalance() + overdraft) {
			super.balance = super.balance - amount;
			return;
		}

		throw new OverDraftLimitExceededException(amount, balance + overdraft,
				overdraft);

	}

	@Override
	public void deposit(final double amount) {
		if (amount > 0) {
			this.balance += amount;
		} else {
			throw new IllegalArgumentException("Negative amount to deposit");
		}

	}

	public AccountType getAccountType() {
		return AccountType.CHECKING;
	}

	public double getOverdraft() {
		return overdraft;
	}
}
