package com.example.server.personService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;
import com.example.server.mediator.requests.persons.FilterPersonsRequest;
import com.example.server.mediator.responses.persons.FilterPersonsResponse;
import com.example.server.model.Person;
import com.example.server.model.persistence.PersonPersistence;

import java.util.List;

public class FilterPersonsHandler implements Handler {
    PersonPersistence personPersistence;

    public FilterPersonsHandler() {
        this.personPersistence = new PersonPersistence();
    }

    @Override
    public Response onMessage(Request message) {
        FilterPersonsRequest filterPersonsRequest = (FilterPersonsRequest) message;
        List<Person> persons = personPersistence.findAll().stream().filter(person -> person.getRole().name().equals(filterPersonsRequest.getRole())).toList();

        return new FilterPersonsResponse(persons);
    }
}
