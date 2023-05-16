package com.example.server.mediator.responses.persons;

import com.example.server.mediator.Response;
import com.example.server.model.Shop;

import java.util.List;

public class GetShopResponse implements Response {
    private List<Shop> shops;

    public GetShopResponse(List<Shop> shops) {
        this.shops = shops;
    }

    public List<Shop> getShops() {
        return shops;
    }
}
