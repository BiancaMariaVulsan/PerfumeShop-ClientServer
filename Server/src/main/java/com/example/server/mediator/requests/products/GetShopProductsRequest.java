package com.example.server.mediator.requests.products;

import com.example.server.mediator.Request;

public class GetShopProductsRequest implements Request {
    private int shopId;

    public int getShopId() {
        return shopId;
    }
    public void setShopId(int shopId) {
        this.shopId = shopId;
    }
}
