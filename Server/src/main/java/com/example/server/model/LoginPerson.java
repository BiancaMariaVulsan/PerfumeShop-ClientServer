package com.example.server.model;

public class LoginPerson {
    private String username;
    private String password;

    public LoginPerson(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
