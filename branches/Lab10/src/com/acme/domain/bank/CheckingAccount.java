package com.acme.domain.bank;

public class CheckingAccount extends AbstractAccount {
	private double overdraft;

	public CheckingAccount(final double amount, final double overdraft) {
		super(amount);
		this.overdraft = overdraft;
	}
	
	@Override
	protected boolean assertPositive(){
		return overdraft > 0 && getBalance() > 0;
	}
	
	@Override
	public boolean withdraw(final double amount) {
		if(amount + overdraft < amount){
			assert assertPositive();
			return false;
		}
		
		if (amount > getBalance()) {
			setBalance(0);
			overdraft -= amount - getBalance();
		} else {
			setBalance(getBalance() - amount);
		}
		assert assertPositive();
		return true;

	}

	@Override
	public double maximumAmountToWithdraw() {
		return getBalance() + overdraft;
	}

}
