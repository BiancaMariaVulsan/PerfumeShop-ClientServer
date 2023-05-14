package com.example.perfumeshop.model;

public class AddProductRequest {
    private final ShopProduct product;
    private final int shopId;

    public AddProductRequest(ShopProduct product, int shopId) {
        this.product = product;
        this.shopId = shopId;
    }

    public ShopProduct getShopProduct() {
        return product;
    }

    public int getShopId() {
        return shopId;
    }
}
