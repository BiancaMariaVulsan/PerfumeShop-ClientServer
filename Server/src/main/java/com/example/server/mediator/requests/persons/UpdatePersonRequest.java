package com.example.server.mediator.requests.persons;

import com.example.server.mediator.Request;
import com.example.server.model.Person;

public class UpdatePersonRequest implements Request {
    private final Person person;

    public UpdatePersonRequest(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
