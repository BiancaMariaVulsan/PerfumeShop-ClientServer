package com.example.authservice.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Person {
    private final IntegerProperty id =  new SimpleIntegerProperty();
    private final StringProperty firstName =  new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private Role role;

    public Person(int id, String firstName, String lastName, Role role, String username, String password) {
        this.id.set(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.role = role;
        this.username.set(username);
        this.password.set(password);
    }

    public Person(String firstName, String lastName, Role role, String username, String password) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.role = role;
        this.username.set(username);
        this.password.set(password);
    }

    public Person() {}

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", username=" + username +
                ", password=" + password +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        if (getClass() != o.getClass()) return false;
        return username.get().equals(person.username.get()) && password.get().equals(person.password.get()) && role == ((Person) o).role
                && firstName.get().equals(person.firstName.get()) && lastName.get().equals(person.lastName.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, username, password, role);
    }
}
