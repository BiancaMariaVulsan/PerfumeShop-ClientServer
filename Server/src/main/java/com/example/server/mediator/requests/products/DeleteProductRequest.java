package com.example.server.mediator.requests.products;

import com.example.server.mediator.Request;

public class DeleteProductRequest implements Request {
    int productId;
    int shopId;
    public DeleteProductRequest(int productId, int shopId) {
        this.productId = productId;
        this.shopId = shopId;
    }

    public int getProductId() {
        return productId;
    }

    public int getShopId() {
        return shopId;
    }
}
