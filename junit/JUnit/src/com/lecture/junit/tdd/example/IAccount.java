package com.lecture.junit.tdd.example;

import java.util.Calendar;

import com.lecture.junit.tdd.example.exceptions.RecenlyUsedPasswordException;

public interface IAccount {

    public Boolean passwordMatches(String pass);

    public void setLoggedIn();

    public void setRevoked();

    public boolean isLoggedIn();

    public boolean isRevoked();

    public String getUserName();
    
	public Calendar getLastAccessCalnedar();

	public Calendar getLastChangePasswordCalendar();

	public void changePassword(String password) throws RecenlyUsedPasswordException;

}

