package com.acme.service.bank;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.acme.domain.bank.Bank;

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
	public void load(final Bank bank, final String path)
			throws FileNotFoundException {

		// TODO: implement this method parsing a file whose path is passed to
		// the method. Add data to the bank
		// Scanner outScanner = new Scanner(path);
		// outScanner.useDelimiter(";");
		// String s;
		// Account a = null;
		// while(outScanner.hasNext()){
		// s = outScanner.next();
		// if(s.startsWith("accounttype=")){
		// if("c".equals(s.substring("accounttype=".length()))){
		// // a = new CheckingAccount();
		// } else {
		//
		// }
		// }
		// Formatter f = new Formatter(a);
		// if(s.startsWith("balance=")) ;
		// if(s.startsWith("overdraft=")) ;
		// if(s.startsWith("name=")) ;
		// if(s.startsWith("gender=")) ;
		// }
		Scanner scn = null;
		scn = new Scanner(path);
		scn.useDelimiter("%n");
		while (scn.hasNext()) {
			String line = scn.next();
			if (!line.contains("accounttype=") || !line.contains("balance=")
					|| !line.contains("overdraft=") || !line.contains("name=")
					|| !line.contains("gender=")) {
				continue;
			}
			 String[] lines = line.split(";");
			// lines.
			for (String s : lines) {

			}

		}

	}

	public BankService getBankService() {
		return bankService;
	}

	public void setBankService(final BankService bankService) {
		this.bankService = bankService;
	}
}
