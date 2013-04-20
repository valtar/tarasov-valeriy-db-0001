package com.acme.service.bank.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.acme.domain.account.Account;
import com.acme.domain.account.CheckingAccount;
import com.acme.domain.account.SavingAccount;
import com.acme.domain.bank.Bank;
import com.acme.domain.bank.Client;
import com.acme.domain.bank.Gender;
import com.acme.exceptions.ClientExistsException;
import com.acme.service.bank.BankService;

public class BankServiceImpl implements BankService {

	// TODO: serialize the bank to the file represented by pathTo
	public void saveBank(final Bank bank, final String pathTo)
			throws IOException {
		ObjectOutputStream output = new ObjectOutputStream(
				new FileOutputStream(pathTo));
		try {
			output.writeObject(bank);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}

	}

	// TODO: deserialize the bank to the file represented by pathTo
	public Bank loadBank(final String pathFrom) throws IOException {
		Bank bank = new Bank();
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(
				pathFrom));
		try {
			bank = (Bank)input.readObject();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} finally{
			
		}

		return null;

	}

	// accounttype=c|s;balance=100;overdraft=50;name=John;gen
	// der=m|f;

	public static void main(String[] args) {
		String s = "accounttype=s ; balance= 100 ;overdraft= 5000 ;name=sdvfsdv;gender=f;";
		System.out.println(getClientFromString(s));
	}

	private static Client getClientFromString(String s) {
		Account account = null;
		Client client = null;

		String pattern = "\\s*accounttype\\s*=\\s*[cs]\\s*;"
				+ "\\s*balance\\s*=\\s*\\d+\\s*;"
				+ "\\s*overdraft\\s*=\\s*\\d+\\s*;"
				+ "\\s*name\\s*=\\s*.+\\s*;"
				+ "\\s*gender\\s*=\\s*[mf]\\s*;\\s*";

		if (!s.matches(pattern)) {
			System.out.println("wrong");
			return null;
		}
		try {
			String mapString[] = s.split(";");
			String[] values = new String[mapString.length];
			for (int i = 0; i < mapString.length; i++) {
				values[i] = (mapString[i].split("="))[1].trim();
			}

			double balance = Double.parseDouble(values[Key.balance.ordinal()]);
			double overdraft = Double.parseDouble(values[Key.overdraft
					.ordinal()]);

			if ("s".equals(values[Key.accounttype.ordinal()])) {
				// TODO: ID ???
				account = new SavingAccount(0, balance);
			} else if ("c".equals(values[Key.accounttype.ordinal()])) {
				// TODO: ID ???
				account = new CheckingAccount(1, balance, overdraft);
			} else {
				System.err.println("bad logic");
			}

			Gender gender = "f".equals(values[Key.gender.ordinal()]) ? Gender.FEMALE
					: Gender.MALE;
			String name = values[Key.gender.ordinal()];
			client = new Client(name, gender);
			client.addAccount(account);
		} catch (Exception e) {
			System.err.println(e);
		}

		return client;
	}

	private enum Key {
		accounttype, balance, overdraft, name, gender
	};

	// public static final String EXAMPLE_TEST = "true=123 vsdfvfsdd";
	// // "This is my small example "
	// // + "string which I'm going to " + "use for pattern matching.";
	//
	// public static void main(String[] args) {
	// System.out.println(EXAMPLE_TEST.matches(".*(true=\\d).*"));
	// // String[] splitString = (EXAMPLE_TEST.split("\\s+"));
	// // System.out.println(splitString.length);// Should be 14
	// // for (String string : splitString) {
	// // System.out.println(string);
	// // }
	// // // Replace all whitespace with tabs
	// // System.out.println(EXAMPLE_TEST.replaceAll("\\s+", "\t"));
	// }
	//

	@Override
	public Client addClient(Bank bank, String name, Gender gender)
			throws ClientExistsException {
		// TODO Auto-generated method stub
		return null;
	}

}
