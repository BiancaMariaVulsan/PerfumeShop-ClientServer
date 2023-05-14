package com.example.server.mediator.requests.products;

import com.example.server.mediator.Request;
import com.example.server.model.ShopProduct;

public class AddProductRequest implements Request {
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
