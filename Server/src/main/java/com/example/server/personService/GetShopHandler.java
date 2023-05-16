package com.example.server.personService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;
import com.example.server.mediator.requests.persons.GetShopRequest;
import com.example.server.mediator.responses.persons.GetShopResponse;
import com.example.server.model.Shop;
import com.example.server.model.persistence.ShopPersistence;

import java.util.List;

public class GetShopHandler implements Handler {
    private ShopPersistence shopPersistence;

    public GetShopHandler() {
        this.shopPersistence = new ShopPersistence();
    }

    @Override
    public Response onMessage(Request message) {
        List<Shop> shopList = shopPersistence.findAll();
        return new GetShopResponse(shopList);
    }
}
