package com.example.server.mediator.responses.persons;

import com.example.server.mediator.Response;

public class LoginResponse implements Response {
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private String error;
}
