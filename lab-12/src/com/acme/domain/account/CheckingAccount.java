package com.acme.domain.account;

import com.acme.exceptions.NoEnoughFundsException;
import com.acme.exceptions.OverDraftLimitExceededException;

public class CheckingAccount extends AbstractAccount {

	private double overdraft;

	public CheckingAccount(final int id, final double amount,
			final double overdraft) {
		super(id, amount);
		this.overdraft = overdraft;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(overdraft);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckingAccount other = (CheckingAccount) obj;
		if (Double.doubleToLongBits(overdraft) != Double
				.doubleToLongBits(other.overdraft))
			return false;
		return true;
	}

	@Override
	public void withdraw(final double amount) throws NoEnoughFundsException {
		if (amount < 0) {
			throw new IllegalArgumentException("Amount can not be negative");
		}
		if (this.balance - amount >= -overdraft) {
			this.balance -= amount;
		} else {
			throw new NoEnoughFundsException(amount);
		}

		if (this.balance < amount) {
			// Not enough balance to cover the amount requested to withdraw
			// Check if there is enough in the overdraft protection (if any)
			double overdraftNeeded = amount - this.balance;
			if (overdraft < overdraftNeeded) {
				throw new OverDraftLimitExceededException(overdraftNeeded
						- overdraft);
			} else {
				// Yes, there is overdraft protection and enough to cover the
				// amount
				this.balance = 0.0;
				overdraft -= overdraftNeeded;
			}
		} else {
			// Yes, there is enough balance to cover the amount
			// Proceed as usual
			this.balance = this.balance - amount;
		}

	}

	@Override
	public void deposit(final double amount) {
		if (amount > 0) {
			this.balance += amount;
		} else {
			throw new IllegalArgumentException("amount ca not be negative");
		}

	}

	// TODO: implement toString method which details this account information

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + ", overdraft " + overdraft;
	}

	public AccountType getAccountType() {
		return AccountType.CHECKING;
	}

	public double getOverdraft() {
		return overdraft;
	}
}
