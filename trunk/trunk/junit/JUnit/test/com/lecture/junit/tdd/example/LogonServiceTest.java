package com.lecture.junit.tdd.example;

import org.junit.Before;
import org.junit.Test;

import com.lecture.junit.tdd.example.exceptions.AccountAlreadyLoggedInException;
import com.lecture.junit.tdd.example.exceptions.AccountIsRevokedException;
import com.lecture.junit.tdd.example.exceptions.AccountNotFoundException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class LogonServiceTest {

	public static final String BRETT = "brett";
	public static final String PASSWORD = "password";
	IAccount account;
	IAccountRepository accountRepository;
	LoginService service;

	@Before
	public void init() {
		account = mock(IAccount.class);
		when(account.getUserName()).thenReturn(BRETT);
		accountRepository = mock(IAccountRepository.class);
		when(accountRepository.find(anyString())).thenReturn(account);
		service = new LoginService(accountRepository);
	}

	public void willPasswordMatch(boolean flag) {
		when(account.passwordMatches(anyString())).thenReturn(flag);
	}

	@Test
	public void stestLogin_shouldLogin_whenUserNameAndPasswordPassed() {
		willPasswordMatch(true);
		service.login("brett", PASSWORD);
		verify(account, times(1)).setLoggedIn();
	}

	@Test
	public void testLogin_shouldRevokeAccount_whenThreeLoginAttemptsFail() {
		willPasswordMatch(false);

		service.login("brett", PASSWORD);
		service.login("brett", PASSWORD);
		service.login("brett", PASSWORD);

		verify(account).setRevoked();
	}

	@Test
	public void testLogin_shouldNotSetLoggedIn_whenPasswordNotMatches() {
		willPasswordMatch(false);
		service.login(BRETT, PASSWORD);

		verify(account, never()).setLoggedIn();
	}

	@Test
	public void testLogin_shouldNotRevoke_whenOneAccFailsTwoTimesAndOtherFailsOneTime() {
		willPasswordMatch(false);
		IAccount second = mock(IAccount.class);
		when(second.getUserName()).thenReturn("YURA");
		doReturn(false).when(second).passwordMatches(anyString());
		when(accountRepository.find("YURA")).thenReturn(second);

		service.login(BRETT, PASSWORD);
		service.login(BRETT, PASSWORD);

		service.login("YURA", PASSWORD);
		verify(second, never()).setRevoked();
	}

	@Test(expected = AccountAlreadyLoggedInException.class)
	public void testLogin_shouldNotifyWithException_whenLoggedIndAccTriesToLogin() {
		willPasswordMatch(true);
		doReturn(true).when(account).isLoggedIn();

		service.login(BRETT, PASSWORD);
	}

	@Test(expected = AccountNotFoundException.class)
	public void testLogin_shouldThrowException_whenAccIsNotFound() {
		doReturn(null).when(accountRepository).find(anyString());

		service.login(BRETT, PASSWORD);
	}

	@Test(expected = AccountIsRevokedException.class)
	public void testLogin_shouldNotLoginAndThrowException_whenAccRevoked() {
		doReturn(true).when(account).isRevoked();
		willPasswordMatch(true);
		service.login(BRETT, PASSWORD);

		verify(account, never()).setLoggedIn();
	}

	@Test
	public void testLogin_shouldRestBackToInitialState_afterSuccessfulLogin() {
		willPasswordMatch(false);
		service.login(BRETT, PASSWORD);
		service.login(BRETT, PASSWORD);
		willPasswordMatch(true);
		service.login(BRETT, PASSWORD);
		willPasswordMatch(false);
		service.login(BRETT, PASSWORD);

		verify(account, never()).setRevoked();
	}
	// A user must change their password every 30 days, so if they attempt to
	// log in and the password is expired, then the user must change their
	// password before they can successfully log in
	
	@Test
	public void should(){}

}
