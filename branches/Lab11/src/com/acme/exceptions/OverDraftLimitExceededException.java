package com.acme.exceptions;

public class OverDraftLimitExceededException extends NotEnoughFundsException {
	private double maxAmount;
	private double overdraft;
	
	public OverDraftLimitExceededException(double d){
		super(d);
	}
	
	public OverDraftLimitExceededException(double d, double maxAmount, double overdraft){
		super(d);
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
