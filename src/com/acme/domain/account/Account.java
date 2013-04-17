package com.acme.domain.account;

import com.acme.exceptions.OverDraftLimitExceededException;


public interface Account {

	//TODO: add declaration of IllegalArgumentException here. Amount can not be negative
	void deposit(double amount);

	//TODO: add declaration of NoEnoughFundsException here. A user can ask too much funds
	void withdraw(double amount) throws OverDraftLimitExceededException;

	int getAccountNumber();

	AccountType getAccountType();

	boolean isOpened();

	double getBalance();

}
