package com.acme.domain.bank;

public class CheckingAccount extends AbstractAccount {
	private double overdraft;

	public CheckingAccount(final double amount, final double overdraft) {
		super(amount);
		this.overdraft = overdraft;
	}

	@Override
	public void withdraw(final double amount) {
		if (amount > getBalance()) {
			setBalance(0);
			overdraft -= amount - getBalance();
		} else {
			setBalance(getBalance() - amount);
		}

	}

	@Override
	public double maximumAmountToWithdraw() {
		return getBalance() + overdraft;
	}

}
