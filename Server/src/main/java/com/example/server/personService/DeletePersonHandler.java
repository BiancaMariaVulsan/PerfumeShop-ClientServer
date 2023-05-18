package com.example.server.personService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;
import com.example.server.mediator.requests.persons.DeletePersonRequest;
import com.example.server.mediator.responses.products.AddPersonResponse;
import com.example.server.model.persistence.PersonPersistence;

public class DeletePersonHandler implements Handler {
    private final PersonPersistence personPersistence;

    public DeletePersonHandler() {
        this.personPersistence = new PersonPersistence();
    }

    @Override
    public Response onMessage(Request message) {
        DeletePersonRequest request = (DeletePersonRequest) message;
        personPersistence.delete(request.getPerson());
        return new AddPersonResponse("Person deleted successfully");
    }
}
