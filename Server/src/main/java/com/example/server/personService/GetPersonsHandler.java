package com.example.server.personService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.responses.persons.GetPersonsResponse;
import com.example.server.mediator.Response;
import com.example.server.model.Person;
import com.example.server.model.persistence.PersonPersistence;

import java.util.List;

public class GetPersonsHandler implements Handler {
    private final PersonPersistence personPersistence;

    public GetPersonsHandler() {
        personPersistence = new PersonPersistence();
    }

    @Override
    public Response onMessage(Request message) {
        List<Person> persons = personPersistence.findAll();
        GetPersonsResponse response = new GetPersonsResponse();

        response.setPersons(persons);
        return response;
    }
}
