package com.acme.domain.bank;

public class Client {
	private Account account = null;
	private Gender gender = null;
	private String name = null;

	public Client(Account aAccount, Gender aGender, String aName) {
		account = aAccount;
		gender = aGender;
		name = aName;
	}

	public Account getAccount() {
		return account;
	}

	public void getClientSalutation() {
		System.out.println(getStringClientSalutation());
	}

	public String getStringClientSalutation() {
		return gender + " " + name;
	}

	@Override
	public String toString() {
		return getStringClientSalutation();
	}
	
	

}
