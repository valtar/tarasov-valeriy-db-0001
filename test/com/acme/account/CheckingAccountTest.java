//package com.acme.account;
//
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Test;
//
//import com.acme.Account;
//import com.acme.CheckingAccount;
//public class CheckingAccountTest  {
//
//	@Test	
//	public void testDeposit() {
//		BankServiceImpl bankService = new BankServiceImpl();
//		Bank bank = bankService.createNewBank();
//		Client client1 = bankService.addClient(bank, "John", Gender.MALE);
//		
//		Account account2 = new CheckingAccount(2, 100, 0);
//		client1.addAccount(account2);
//		assertEquals(100.0, account2.getBalance());
//		account2.deposit(11);
//		assertEquals(111.0, account2.getBalance());
//	}
//	
//	@Test
//	public void testWithdraw() {
//		Account account2 = new CheckingAccount(2, 100, 10);
//		
//		assertEquals(100.0, account2.getBalance());
//		account2.withdraw(10);
//		assertEquals(90.0, account2.getBalance());
//		
//		account2.withdraw(90);
//		assertEquals(0.0, account2.getBalance());
//		
//		assertEquals(0, account2.withdraw(5));
//		assertEquals(-5.0, account2.getBalance());
//		assertEquals(10.0, ((CheckingAccount)account2).getOverdraft());
//		assertEquals(-1, account2.withdraw(50));
//		
//	}
//
//}
