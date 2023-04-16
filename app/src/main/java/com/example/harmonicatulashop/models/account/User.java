package com.example.harmonicatulashop.models.account;

public abstract class User {

    private int id;

    private final String login;

    private final int hashPassword;

    public User(String login, int hashPassword) {
        this.login = login;
        this.hashPassword = hashPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
