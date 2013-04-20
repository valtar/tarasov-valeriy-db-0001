package com.acme.domain.account;

import com.acme.exceptions.NoEnoughFundsException;

public abstract class AbstractAccount implements Account {

	protected int id;
	protected double balance;
	protected AccountState state;

	public static int getUniqueAccountNumber() {
		return (int) System.currentTimeMillis();
	}

	public AbstractAccount(final int id, final double amount) {
		this.balance = amount;
		this.id = id;
		state = AccountState.OPENED;
	}

	public double getBalance() {
		return balance;
	}

	public long decimalValue() {
		return Math.round(getBalance());
	}

	@Override
	public String toString() {
		return "account " + getAccountNumber() + " balace = " + getBalance();

	}

	public int getAccountNumber() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractAccount other = (AbstractAccount) obj;
		if (Double.doubleToLongBits(balance) != Double
				.doubleToLongBits(other.balance))
			return false;
		if (id != other.id)
			return false;
		if (state != other.state)
			return false;
		return true;
	}

	public abstract void deposit(final double amount)
			throws IllegalArgumentException;

	public abstract void withdraw(final double amount)
			throws NoEnoughFundsException;

	public void setState(final AccountState state) {
		this.state = state;
	}

	public AccountState getState() {
		return state;
	}

	public boolean isOpened() {
		return state == AccountState.OPENED;
	}
}
