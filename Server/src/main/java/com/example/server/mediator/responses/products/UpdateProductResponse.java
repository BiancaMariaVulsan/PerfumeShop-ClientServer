package com.example.server.mediator.responses.products;

import com.example.server.mediator.Response;

public class UpdateProductResponse implements Response {
    private String message;

    public UpdateProductResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
