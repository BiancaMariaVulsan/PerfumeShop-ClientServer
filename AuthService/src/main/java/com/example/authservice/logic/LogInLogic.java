package com.example.authservice.logic;

import com.example.authservice.model.Person;
import com.example.authservice.model.persistence.PersonPersistence;

public class LogInLogic {
    private final String username;
    private final String password;
    private final PersonPersistence personPersistence;

    public LogInLogic(String username, String password) {
        this.username = username;
        this.password = password;
        personPersistence = new PersonPersistence();
    }

    public boolean signIn() {
        Person person = getPersonByUsername(username);
        if (person == null) {
            return false;
        }
        if (person.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    private Person getPersonByUsername(String username) {
        return this.personPersistence.findAll().stream()
                .filter(p -> p.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
