package com.lecture.junit.tdd.example;

import java.util.concurrent.atomic.AtomicInteger;

public class AfterFirstLoginAttempt extends LoginServiceState {

    public AfterFirstLoginAttempt(AtomicInteger count) {
        this.count = count;
    }

    @Override
    protected void handlePasswordFail(LoginServiceInterface context, IAccount acc) {
        count.getAndIncrement();
        context.setState(acc.getUserName(), new AfterSecondLoginAttempt(count));
    }
}
