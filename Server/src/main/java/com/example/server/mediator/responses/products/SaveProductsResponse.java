package com.example.server.mediator.responses.products;

import com.example.server.mediator.Response;

public class SaveProductsResponse implements Response {
    private final  String message;

    public SaveProductsResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
