package com.lecture.junit.tdd.example;

import java.util.concurrent.atomic.AtomicInteger;

public class AfterSecondLoginAttempt extends LoginServiceState{

    public AfterSecondLoginAttempt(AtomicInteger count) {
        this.count = count;
    }

    @Override
    protected void handlePasswordFail(LoginServiceInterface context, IAccount acc) {
        if (count.incrementAndGet() > 2) {
            acc.setRevoked();
        }
    }
}
