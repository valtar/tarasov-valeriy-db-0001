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

	public void setBalance(final double balance) {
		this.balance = balance;
	}

	public void deposit(final double amount) {
		balance += amount;
	}

	public boolean withdraw(final double amount) {
		if (amount > balance) return false;
		balance -= amount;
		return true;
	}

}
