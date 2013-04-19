package com.acme.domain.bank;

public class CheckingAccount extends AbstractAccount {
	private double overdraft;

	public CheckingAccount(final double amount, final double overdraft) {
		super(amount);
		this.overdraft = overdraft;
	}

	@Override
	public boolean withdraw(final double amount) {
		if (amount <= getBalance() + overdraft) {
			setBalance(getBalance() - amount);
			assert getBalance() + overdraft >= 0;
			return true;
		}
		assert getBalance() + overdraft >= 0;
		return false;
	}

	@Override
	public double maximumAmountToWithdraw() {
		return super.maximumAmountToWithdraw() + overdraft;
	}

}
