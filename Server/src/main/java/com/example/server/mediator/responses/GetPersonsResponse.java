package com.example.server.mediator.responses;

import com.example.server.model.Person;

import java.util.List;

public class GetPersonsResponse implements Response {
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    private List<Person> persons;

}
