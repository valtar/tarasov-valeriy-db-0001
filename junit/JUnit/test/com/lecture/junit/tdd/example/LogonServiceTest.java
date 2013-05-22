package com.lecture.junit.tdd.example;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.lecture.junit.tdd.example.exceptions.AccountAlreadyLoggedInException;
import com.lecture.junit.tdd.example.exceptions.AccountIsRevokedException;
import com.lecture.junit.tdd.example.exceptions.AccountNotFoundException;
import com.lecture.junit.tdd.example.exceptions.NotUsedALotOfTimeException;
import com.lecture.junit.tdd.example.exceptions.RecenlyUsedPasswordException;
import com.lecture.junit.tdd.example.exceptions.UsedMoreThan30DaysPasswordException;


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
		when(account.getLastAccessCalnedar()).thenReturn(Calendar.getInstance());
		when(account.getLastChangePasswordCalendar()).thenReturn(Calendar.getInstance());
		
	}

	public void willPasswordMatch(boolean flag) {
		when(account.passwordMatches(anyString())).thenReturn(flag);
	}

	@Test
	public void testLogin_shouldLogin_whenUserNameAndPasswordPassed() {
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

	@Test(expected = UsedMoreThan30DaysPasswordException.class)
	public void testLogin_shouldFailsWhen30DaysLast() {
		willPasswordMatch(true);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -31);
		
		when(account.getLastChangePasswordCalendar()).thenReturn(calendar);
		service.login(BRETT, PASSWORD);
	}

	// A user cannot use any of their previous 24 passwords
	@Test(expected = RecenlyUsedPasswordException.class)
	public void changePassword_shouldFailsWhenOneOfLast24Passwords() {
		doThrow(new RecenlyUsedPasswordException()).when(account).changePassword(anyString());
		service.changePassword(BRETT, PASSWORD);
	}

	// User accounts have a time stamp of the last time the account was used
	// (logged in, logged out, password changed). If an account has not been
	// used for 45 days, then the account becomes revoked and can only be
	// enabled by calling customer support.
	@Test(expected = NotUsedALotOfTimeException.class)
	public void testLogin_shouldFailsAndRevokedWhenNotUsedMoreThan45Days() {
		willPasswordMatch(true);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -46);
		
		when(account.getLastAccessCalnedar()).thenReturn(calendar);
		
		service.login(BRETT, PASSWORD);
		
		verify(account).setRevoked();
	}

}
