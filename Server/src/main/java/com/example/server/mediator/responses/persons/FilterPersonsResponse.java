package com.example.server.mediator.responses.persons;

import com.example.server.mediator.Response;
import com.example.server.model.Person;

import java.util.List;

public class FilterPersonsResponse implements Response {
    private final List<Person> filteredPersons;

    public FilterPersonsResponse(List<Person> filteredPersons) {
        this.filteredPersons = filteredPersons;
    }

    public List<Person> getFilteredPersons() {
        return filteredPersons;
    }
}
