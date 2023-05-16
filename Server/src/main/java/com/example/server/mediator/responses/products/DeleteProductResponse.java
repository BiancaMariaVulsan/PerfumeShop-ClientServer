package com.example.server.mediator.responses.products;

import com.example.server.mediator.Response;

public class DeleteProductResponse implements Response {
    private String message;

    public DeleteProductResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
