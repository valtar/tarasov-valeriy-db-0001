package com.acme.domain.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.acme.domain.account.Account;

public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Gender gender;

	private List<Account> accounts = new ArrayList<Account>();

	public Client(final String name, final Gender gender) {
		this.name = name;
		this.gender = gender;
	}

	public String getName() {
		return this.name;
	}

	public String getSalutation() {
		if (gender != null) {
			return gender.getSalutation() + " " + name;
		} else {
			return name;
		}
	}

	public Account getAccount(final int id) {
		for (Account acc : accounts) {
			if (acc.getAccountNumber() == id) {
				return acc;
			}
		}
		return null;
	}

	public void addAccount(final Account account) {
		accounts.add(account);
	}

	// TODO: implement toString method which outputs infor for this client

	public List<Account> getAccountsList() {
		return Collections.unmodifiableList(accounts);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (gender != other.gender)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getSalutation()).append("'s accounts: ");

		if (accounts == null || accounts.size() < 1) {
			sb.append("no one account");
			return sb.toString();
		}

		for (Account account : accounts) {
			sb.append(account).append("; ");
		}
		return sb.toString();
	}

	// TODO: implement hashCode() and equals() methods which will be used in the
	// following examples (collections)
}
