package com.example.server.mediator.requests.persons;

import com.example.server.mediator.Request;

public class GetEmployeeShopRequest implements Request {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
}
