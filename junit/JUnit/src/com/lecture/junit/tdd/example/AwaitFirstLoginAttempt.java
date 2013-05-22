package com.lecture.junit.tdd.example;


import java.util.concurrent.atomic.AtomicInteger;

public class AwaitFirstLoginAttempt extends LoginServiceState {
    public AwaitFirstLoginAttempt() {
        this.count = new AtomicInteger(0);
    }

    @Override
    protected void handlePasswordFail(LoginServiceInterface context, IAccount acc) {
        count.getAndIncrement();
        context.setState(acc.getUserName(), new AfterFirstLoginAttempt(count));
    }
}
