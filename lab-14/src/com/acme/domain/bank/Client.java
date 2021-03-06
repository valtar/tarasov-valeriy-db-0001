package com.acme.domain.bank;

import java.io.Serializable;

import com.acme.domain.account.Account;

public class Client implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Gender gender;
	private final int MAX_ACCOUNTS = 10;
	private int accountCount = 0;
	Account[] accounts = new Account[MAX_ACCOUNTS];
	
	public Client(String name, Gender gender) {
		this.name = name;
		this.gender = gender;
	}

	public void addAccount(Account account) {
		this.accounts[accountCount++] = account;
	}

	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!( obj instanceof Client)) return false;
		
		Client another = (Client) obj;
		if(!this.name.equals(another.name)) return false;
		if(this.gender != another.gender)  return false;
		
		return true;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	Gender getGender() {
		return gender;
	}

	void setGender(Gender gender) {
		this.gender = gender;
	}

	
}
