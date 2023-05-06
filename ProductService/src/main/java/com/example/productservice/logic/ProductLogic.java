package com.example.productservice.logic;

import com.example.productservice.model.Product;
import com.example.productservice.model.persistence.ProductPersistence;

import java.util.List;

public class ProductLogic {
    private final ProductPersistence productPersistence = new ProductPersistence();
    public List<Product> getAllProducts() {
        return productPersistence.findAll();
    }
}
