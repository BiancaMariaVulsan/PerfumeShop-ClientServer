package com.example.perfumeshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShopProduct {
    private Product product;
    private int stock;
    public ShopProduct() {
    }
    public ShopProduct(Product product, int stock) {
        this.product = product;
        this.stock = stock;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}
