package com.example.server.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "shop_product")
public class ShopProduct {
    private Product product;
    private int stock;

    public ShopProduct(Product product, int stock) {
        this.product = product;
        this.stock = stock;
    }
    public ShopProduct() {
    }
    @XmlElement(name = "product")
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    @XmlElement(name = "stock")
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}
