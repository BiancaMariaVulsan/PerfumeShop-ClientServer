package com.example.server.personService;

import com.example.server.mediator.Handler;
import com.example.server.personService.requests.GetEmployeeShopRequest;
import com.example.server.mediator.Request;
import com.example.server.personService.responses.GetEmployeeShopResponse;
import com.example.server.mediator.Response;
import com.example.server.model.persistence.PersonPersistence;

public class GetEmployeeShopHandler implements Handler {
    private final PersonPersistence personPersistence;

    public GetEmployeeShopHandler() {
        this.personPersistence = new PersonPersistence();
    }

    @Override
    public Response onMessage(Request message) {
        String username = ((GetEmployeeShopRequest)message).getUsername();
        int shopId = personPersistence.findAll()
                .stream()
                .filter(p -> p.getUsername().equals(username))
                .toList()
                .get(0)
                .getShopId();
        GetEmployeeShopResponse response = new GetEmployeeShopResponse();

        response.setShopId(shopId);
        return response;
    }
}
