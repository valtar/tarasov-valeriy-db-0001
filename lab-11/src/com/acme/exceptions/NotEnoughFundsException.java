package com.acme.exceptions;

public class NotEnoughFundsException extends BankException {
	private double amount;

	public double getAmount() {
		return amount;
	}

	public NotEnoughFundsException(double amount) {
		this.amount = amount;
	}

}
