package com.acme.domain.bank;

public abstract class AbstractAccount implements Account {
	static int idCount = 0;
	protected int id;
	protected double balance;

	public AbstractAccount(final double amount) {
		this.balance = amount;
		this.id = ++idCount;
	}

	public double getBalance() {
		return balance;
	}

	protected void setBalance(final double balance) {
		this.balance = balance;
	}

	public boolean deposit(final double amount) {
		if (amount > 0) {
			balance += amount;
			return true;
		}
		return false;
	}

	public boolean withdraw(final double amount) {
		if (amount > 0 && balance >= amount) {
			balance -= amount;
			return true;
		}
		return false;
	}

	public double maximumAmountToWithdraw() {
		return getBalance();
	}

}
