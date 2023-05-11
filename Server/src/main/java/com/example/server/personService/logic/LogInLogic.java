package com.example.server.personService.logic;

import com.example.server.model.Person;
import com.example.server.model.persistence.PersonPersistence;

public class LogInLogic {
    private final String username;
    private final String password;
    private final PersonPersistence personPersistence;

    public LogInLogic(String username, String password) {
        this.username = username;
        this.password = password;
        personPersistence = new PersonPersistence();
    }

    public String signIn() {
        Person person = getPersonByUsername(username);
        if (person == null) {
            return "Wrong username or password!";
        }
        if (person.getPassword().equals(password)) {
            return person.getRole().name();
        }
        return "Wrong username or password!";
    }

    private Person getPersonByUsername(String username) {
        return this.personPersistence.findAll().stream()
                .filter(p -> p.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
