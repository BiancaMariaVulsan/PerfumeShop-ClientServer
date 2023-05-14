package com.example.server.mediator.responses.products;

import com.example.server.mediator.Response;

public class AddProductResponse implements Response {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
