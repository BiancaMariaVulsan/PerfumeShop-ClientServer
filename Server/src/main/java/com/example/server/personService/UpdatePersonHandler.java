package com.example.server.personService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;
import com.example.server.mediator.requests.persons.UpdatePersonRequest;
import com.example.server.mediator.responses.products.UpdatePersonResponse;
import com.example.server.model.Employee;
import com.example.server.model.persistence.PersonPersistence;

public class UpdatePersonHandler implements Handler {
    private final PersonPersistence personPersistence;

    public UpdatePersonHandler() {
        this.personPersistence = new PersonPersistence();
    }

    @Override
    public Response onMessage(Request message) {
        UpdatePersonRequest request = (UpdatePersonRequest) message;
        personPersistence.update(request.getPerson());
        return new UpdatePersonResponse("Person updated successfully");
    }
}
