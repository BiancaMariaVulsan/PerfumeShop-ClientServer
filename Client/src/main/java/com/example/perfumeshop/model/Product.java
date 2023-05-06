package com.example.perfumeshop.model;

import javafx.beans.property.*;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
public class Product {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty brand = new SimpleStringProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();

    public Product(int id, String name, String brand, double price) {
        this.id.set(id);
        this.name.set(name);
        this.brand.set(brand);
        this.price.set(price);
    }

    public Product(String name, String brand, double price) {
        this.name.set(name);
        this.brand.set(brand);
        this.price.set(price);
    }

    public Product() {}

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getBrand() {
        return brand.get();
    }

    public StringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", name=" + name +
                    ", price=" + price +
                    '}';
        }
}
