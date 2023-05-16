package com.example.server.mediator.responses.products;

import com.example.server.mediator.Response;

public class SaveShopProductsResponse implements Response {
    private final String message;

    public SaveShopProductsResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
