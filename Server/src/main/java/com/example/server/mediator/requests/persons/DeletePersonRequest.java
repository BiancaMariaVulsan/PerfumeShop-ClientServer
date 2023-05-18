package com.example.server.mediator.requests.persons;

import com.example.server.mediator.Request;
import com.example.server.model.Person;

public class DeletePersonRequest implements Request {
    private final Person person;

    public DeletePersonRequest(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
