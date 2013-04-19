package com.acme.domain.account;

import com.acme.exceptions.NotEnoughFundsException;


public interface Account {

	void deposit(double amount) throws NotEnoughFundsException, IllegalArgumentException;;

	void withdraw(double amount) throws NotEnoughFundsException, IllegalArgumentException;;

	int getAccountNumber();

	AccountType getAccountType();

	boolean isOpened();

	double getBalance();

}
