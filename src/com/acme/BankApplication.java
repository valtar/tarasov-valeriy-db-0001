package com.acme;

import com.acme.domain.account.CheckingAccount;
import com.acme.domain.account.SavingAccount;
import com.acme.domain.client.Client;
import com.acme.domain.client.Gender;

public class BankApplication {
	private static void outHashAndEquals(Object obj1, Object obj2, Object obj3) {
		System.out.println(obj1.equals(obj1));// true
		System.out.println(obj1.equals(obj2));// true
		System.out.println(obj1.equals(obj3));// false
		System.out.println();

		System.out.println(obj1.hashCode() == obj1.hashCode());// true
		System.out.println(obj1.hashCode() == obj2.hashCode());// true
		System.out.println(obj1.hashCode() == obj3.hashCode());// false
	}

	public static void main(String[] args) {
		
		
		Client client1 = new Client("BillGates", Gender.MALE);
		Client client2 = new Client("BillGates", Gender.MALE);
		Client client3 = new Client("LadyAdaAugusta", Gender.FEMALE);
		
		System.out.println("Clients equals & hashes:");
		outHashAndEquals(client1, client2, client3);
		System.out.println();

		SavingAccount sa1 = new SavingAccount(0, 100);
		SavingAccount sa2 = new SavingAccount(0, 100);
		SavingAccount sa3 = new SavingAccount(1, 100);
		
		System.out.println("Saving Accounts:");
		outHashAndEquals(sa1, sa2, sa3);
		System.out.println();
		
		CheckingAccount ca1 = new CheckingAccount(0, 100, 100);
		CheckingAccount ca2 = new CheckingAccount(0, 100, 100);
		CheckingAccount ca3 = new CheckingAccount(1, 50, 100);

		System.out.println("CheckingAccounts: ");
		outHashAndEquals(ca1, ca2, ca3);
		System.out.println();

		client1.addAccount(ca1);
		client3.addAccount(sa1);
		client3.addAccount(ca2);
		
		System.out.println("add account: ");
		System.out.println(client1);
		System.out.println(client2);
		System.out.println(client3);
		System.out.println();
		
		System.out.println("account's balances: ");
		System.out.println(ca1.decimalValue());
		System.out.println(sa1.decimalValue());

	}

}
