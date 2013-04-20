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
	private BankService bankService;

	/**
	 * Loads a file which contains data feed in the following format: <br>
	 * accounttype=c|s;balance=100;overdraft=50;name=John;gender=m|f;
	 * 
	 * @param bank
	 * @param path
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) {
		String path = "D:/towrite/feed";
		Bank bank = new Bank();
		try {
			load(bank,path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(bank);
		
		
		
	}
	public static void load(final Bank bank, final String path)
			throws FileNotFoundException {
		File file = new File(path);
		if (!file.exists() || !file.isFile()) {
			throw new FileNotFoundException();
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new BufferedInputStream(new FileInputStream(file))));
		try {
			for (String line = reader.readLine(); line != null; line = reader
					.readLine()) {
				addClientFromString(bank, line);
			}
		} catch (IOException e) {
			System.out.println(e);
		} finally{
			try{
				reader.close();
			} catch(IOException e){
				System.out.println(e);
			}
		}

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

	public static void addClientFromString(Bank bank, String line) {
		bank.addClient(getClientFromString(line));
	}

	public BankService getBankService() {
		return bankService;
	}

	public void setBankService(final BankService bankService) {
		this.bankService = bankService;
	}
}
