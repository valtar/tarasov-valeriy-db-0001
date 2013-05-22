package com.lecture.junit.tdd.example;


import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

import com.lecture.junit.tdd.example.exceptions.AccountAlreadyLoggedInException;
import com.lecture.junit.tdd.example.exceptions.AccountIsRevokedException;
import com.lecture.junit.tdd.example.exceptions.NotUsedALotOfTimeException;
import com.lecture.junit.tdd.example.exceptions.UsedMoreThan30DaysPasswordException;

public abstract class LoginServiceState {

    AtomicInteger count;

    public void login(LoginServiceInterface context, IAccount acc, String password) {
    
    	
    	
    	if (acc.passwordMatches(password)) {
            if (acc.isLoggedIn()) {
                throw new AccountAlreadyLoggedInException();
            }
            
            if (acc.isRevoked()) {
                throw  new AccountIsRevokedException();
            }
            
            if(!isGreaterThanCurrentCalendarNoMoreThanAmount(acc.getLastAccessCalnedar(), 46)){
            	acc.setRevoked();
            	throw new NotUsedALotOfTimeException();
            }
            
            if(!isGreaterThanCurrentCalendarNoMoreThanAmount(acc.getLastChangePasswordCalendar(), 31)){
            	throw new UsedMoreThan30DaysPasswordException();
            }
            
            acc.setLoggedIn();
            context.setState(acc.getUserName(), new AwaitFirstLoginAttempt());
        } else {
            handlePasswordFail(context, acc);
        }
    }

	protected boolean isGreaterThanCurrentCalendarNoMoreThanAmount(Calendar accCalendar, int amount) {
		Calendar currentMinusAmount = Calendar.getInstance();
		currentMinusAmount.add(Calendar.DAY_OF_YEAR, -amount);
		
		if(accCalendar.after(currentMinusAmount)) {
			return true;            		
		}
		
		return false;
	}

    protected abstract void handlePasswordFail(LoginServiceInterface context, IAccount acc);
  
}
