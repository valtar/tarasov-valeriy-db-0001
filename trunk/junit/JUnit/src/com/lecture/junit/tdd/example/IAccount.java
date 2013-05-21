package com.lecture.junit.tdd.example;

public interface IAccount {

    public Boolean passwordMatches(String pass);

    public void setLoggedIn();

    public void setRevoked();

    public boolean isLoggedIn();

    public boolean isRevoked();

    public String getUserName();

}
