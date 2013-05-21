package com.lecture.junit.tdd.example;


import java.util.concurrent.atomic.AtomicInteger;

import com.lecture.junit.tdd.example.exceptions.AccountAlreadyLoggedInException;
import com.lecture.junit.tdd.example.exceptions.AccountIsRevokedException;

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
            acc.setLoggedIn();
            context.setState(acc.getUserName(), new AwaitFirstLoginAttempt());
        } else {
            handlePasswordFail(context, acc);
        }
    }

    protected abstract void handlePasswordFail(LoginServiceInterface context, IAccount acc);
}
