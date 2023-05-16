package com.example.server.mediator.requests.products;

import com.example.server.mediator.Request;

public class FilterShopProductRequest implements Request {
    private String name;
    private String brand;
    private float price;
    private boolean availability;
    int shopId;

    public FilterShopProductRequest(String name, String brand, float price, boolean availability) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public float getPrice() {
        return price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public int getShopId() {
        return shopId;
    }
}
