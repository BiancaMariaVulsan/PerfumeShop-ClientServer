package com.example.server.mediator.requests.products;

import com.example.server.mediator.Request;

public class FilterProductsRequest implements Request {
    private String name;
    private String brand;
    private float price;
    private boolean availability;
    private int shopId;

    public FilterProductsRequest(String name, String brand, float price, boolean availability, int shopId) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.availability = availability;
        this.shopId = shopId;
    }

    public int getShopId() {
        return shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
