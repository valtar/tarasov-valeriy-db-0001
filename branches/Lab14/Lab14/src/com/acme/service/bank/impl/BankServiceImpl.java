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

	@Override
	public Client addClient(Bank bank, String name, Gender gender)
			throws ClientExistsException, IllegalArgumentException {
		if(bank == null || name == null || gender == null){
			throw new IllegalArgumentException("Can't add client with null parameters.");
		}
		Client client = new Client(name,gender);
		return client;
	}

}
