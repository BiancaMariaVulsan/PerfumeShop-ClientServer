package com.example.server.mediator.responses.persons;

import com.example.server.mediator.Response;

public class GetEmployeeShopResponse implements Response {
    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    private int shopId;
}
