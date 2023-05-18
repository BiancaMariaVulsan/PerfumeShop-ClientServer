package com.example.server.personService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.requests.persons.GetEmployeeShopRequest;
import com.example.server.mediator.Request;
import com.example.server.mediator.responses.persons.GetEmployeeShopResponse;
import com.example.server.mediator.Response;
import com.example.server.model.persistence.EmployeePersistence;
import com.example.server.model.persistence.PersonPersistence;

public class GetEmployeeShopHandler implements Handler {
    private final EmployeePersistence personPersistence;

    public GetEmployeeShopHandler() {
        this.personPersistence = new EmployeePersistence();
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
