package com.example.server.model;

public class Shop {
    private String name;
    private int id;

    public Shop(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Shop() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
