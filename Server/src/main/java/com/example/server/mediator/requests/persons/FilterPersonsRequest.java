package com.example.server.mediator.requests.persons;

import com.example.server.mediator.Request;

public class FilterPersonsRequest implements Request {
    private final String role;

    public FilterPersonsRequest(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
