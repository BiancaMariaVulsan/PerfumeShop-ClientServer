package com.example.server.personService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;
import com.example.server.mediator.requests.persons.AddPersonRequest;
import com.example.server.mediator.responses.products.AddPersonResponse;
import com.example.server.model.persistence.PersonPersistence;

public class AddPersonHandler implements Handler {
    private final PersonPersistence personPersistence;

    public AddPersonHandler() {
        this.personPersistence = new PersonPersistence();
    }

    @Override
    public Response onMessage(Request message) {
        AddPersonRequest request = (AddPersonRequest) message;
        personPersistence.insert(request.getPerson());
        return new AddPersonResponse("Person added successfully");
    }
}
