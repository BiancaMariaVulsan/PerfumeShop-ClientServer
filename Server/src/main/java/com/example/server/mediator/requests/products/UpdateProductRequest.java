package com.example.server.mediator.requests.products;

import com.example.server.mediator.Request;

public class UpdateProductRequest implements Request {
    private int newStock;
    private int productId;
    private int shopId;

    public UpdateProductRequest(int newStock, int productId, int shopId) {
        this.newStock = newStock;
        this.productId = productId;
        this.shopId = shopId;
    }

    public int getNewStock() {
        return newStock;
    }

    public int getProductId() {
        return productId;
    }

    public int getShopId() {
        return shopId;
    }
}
