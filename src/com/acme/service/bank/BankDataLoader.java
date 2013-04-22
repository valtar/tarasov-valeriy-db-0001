package com.acme.service.bank;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.acme.domain.account.Account;
import com.acme.domain.account.CheckingAccount;
import com.acme.domain.account.SavingAccount;
import com.acme.domain.bank.Bank;
import com.acme.domain.bank.Client;
import com.acme.domain.bank.Gender;

public class BankDataLoader {

	/**
	 * Loads a file which contains data feed in the following format: <br>
	 * accounttype=c|s;balance=100;overdraft=50;name=John;gender=m|f;
	 * 
	 * @param bank
	 * @param path
	 * @throws FileNotFoundException
	 */
	public static void load(final Bank bank, final String path)
			throws FileNotFoundException {
		File file = new File(path);
		if (!file.exists() || !file.isFile()) {
			throw new FileNotFoundException();
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new BufferedInputStream(new FileInputStream(file))));
		try {
			String line = reader.readLine();
			for (; line != null; line = reader.readLine()) {
				addClientFromString(bank, line);
			}
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}

	}

	public static Client addClientFromString(Bank bank, String line) {
		Client client = getClientFromString(line);
		if(client != null && !bank.consistClient(client)){
			bank.addClient(getClientFromString(line));
			return client;
		}
		return null;
	}

	public static Client getClientFromString(String s) {
		Account account = null;
		Client client = null;

		String pattern = "\\s*accounttype\\s*=\\s*[cs]\\s*;"
				+ "\\s*balance\\s*=\\s*\\d+\\s*;"
				+ "\\s*overdraft\\s*=\\s*\\d+\\s*;"
				+ "\\s*name\\s*=\\s*.+\\s*;"
				+ "\\s*gender\\s*=\\s*[mf]\\s*;\\s*";

		if (!s.matches(pattern)) {
			System.out.println("wrong client string");
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
				account = new SavingAccount(0, balance);
			} else if ("c".equals(values[Key.accounttype.ordinal()])) {
				account = new CheckingAccount(0, balance, overdraft);
			} else {
				System.err.println("bad logic");
			}

			Gender gender = "f".equals(values[Key.gender.ordinal()]) ? Gender.FEMALE
					: Gender.MALE;
			String name = values[Key.name.ordinal()];
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

}
