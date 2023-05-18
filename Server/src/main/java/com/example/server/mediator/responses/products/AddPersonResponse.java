package com.example.server.mediator.responses.products;

import com.example.server.mediator.Response;

public class AddPersonResponse implements Response {
    private final String message;

    public AddPersonResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
