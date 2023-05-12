package com.example.server.mediator.responses;

public class GetEmployeeShopResponse implements Response {
    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    private int shopId;
}
