package com.example.server.personService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.requests.persons.LoginPersonRequest;
import com.example.server.mediator.Request;
import com.example.server.mediator.responses.persons.LoginResponse;
import com.example.server.mediator.Response;
import com.example.server.model.Person;
import com.example.server.model.persistence.PersonPersistence;

public class LoginPersonHandler implements Handler {
    private final PersonPersistence personPersistence;

    // TODO: dependency injection
    public LoginPersonHandler() {
        this.personPersistence = new PersonPersistence();
    }

    @Override
    public Response onMessage(Request message) {
        String username = ((LoginPersonRequest) message).getUsername();
        String password = ((LoginPersonRequest) message).getPassword();
        LoginResponse response = new LoginResponse();

        Person person = getPersonByUsername(username);
        if (person == null || !person.getPassword().equals(password)) {
            response.setError("Wrong username or password!");
        }
        else {
            response.setRole(person.getRole().name());
        }

        return response;
    }

    private Person getPersonByUsername(String username) {
        return this.personPersistence.findAll().stream()
                .filter(p -> p.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
