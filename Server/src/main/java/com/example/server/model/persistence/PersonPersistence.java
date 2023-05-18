package com.example.server.model.persistence;

import com.example.server.model.Person;
import com.example.server.model.Role;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonPersistence extends DatabaseObj<Person> {

    protected String createInsertQuery(Person employee) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO person (firstname,lastname,role,username,password) VALUES (");
        sb.append("'");
        sb.append(employee.getFirstName());sb.append("','");
        sb.append(employee.getLastName());sb.append("',");
        sb.append(employee.getRole().ordinal());sb.append(",'");
        sb.append(employee.getUsername());sb.append("','");
        sb.append(employee.getPassword());sb.append("'");
        sb.append(")");

        return sb.toString();
    }
    protected String createUpdateQuery(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE person SET firstname='");
        sb.append(person.getFirstName());
        sb.append("',lastname='");
        sb.append(person.getLastName());
        sb.append("',username='");
        sb.append(person.getUsername());
        sb.append("',password='");
        sb.append(person.getPassword());
        sb.append("',role=");
        sb.append(person.getRole().ordinal());
        sb.append(" WHERE id=");
        sb.append(person.getId());
        return sb.toString();
    }

    protected List<Person> createObjects(ResultSet resultSet) {
        try{
            List<Person> persons = new ArrayList<Person>();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int role_nr = resultSet.getInt("role");
                Role role = Role.values()[role_nr];
                Person person = new Person(id, firstName, lastName, role, username, password);
                persons.add(person);
            }
            return persons;
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
