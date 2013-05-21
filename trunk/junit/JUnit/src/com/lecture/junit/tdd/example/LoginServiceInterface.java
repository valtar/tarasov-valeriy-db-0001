package com.lecture.junit.tdd.example;

interface LoginServiceInterface {

    public void login(String userName, String password);

    public void setState(String userName, LoginServiceState state);
}
