package com.acme.domain.bank;

public interface Account {
	public abstract boolean deposit(double amount);

	public abstract boolean withdraw(double amount);

	public abstract double getBalance();

	public abstract double maximumAmountToWithdraw();
}
