package com.example.server.personService.logic;

import com.example.server.model.Person;
import com.example.server.model.persistence.PersonPersistence;

import java.util.ArrayList;
import java.util.List;

public class PersonLogic {
    private static List<Person> persons = new ArrayList<>();

    private static final PersonPersistence personPersistence = new PersonPersistence();

    public PersonLogic() {
        persons = personPersistence.findAll();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public int getEmployeeShop(String username) {
        return persons.stream().filter(p -> p.getUsername().equals(username)).toList().get(0).getShopId();
    }
}
