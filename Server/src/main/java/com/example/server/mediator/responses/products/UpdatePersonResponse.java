package com.example.server.mediator.responses.products;

import com.example.server.mediator.Response;

public class UpdatePersonResponse implements Response {
    private final String message;

    public UpdatePersonResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
