package com.acme.exceptions;

public class OverDraftLimitExceededException extends NotEnoughFundsException {
	private double maxAmount;
	private double overdraft;

	public OverDraftLimitExceededException(double amount, double maxAmount,
			double overdraft) {
		super(amount);
		this.maxAmount = maxAmount;
		this.overdraft = overdraft;
	}

	public double getMaxAmount() {
		return maxAmount;
	}

	public double getOverdraft() {
		return overdraft;
	}

}
