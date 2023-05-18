package com.example.server.mediator.requests.persons;

import com.example.server.mediator.Request;
import com.example.server.model.Person;

public class AddPersonRequest implements Request {
    private final Person person;

    public AddPersonRequest(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
