package com.example.server.mediator.responses.products;

import com.example.server.mediator.Response;

public class DeletePersonResponse implements Response {
    private final String message;

    public DeletePersonResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
