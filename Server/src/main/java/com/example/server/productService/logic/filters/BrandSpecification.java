package com.example.server.productService.logic.filters;

import com.example.server.model.Product;

public class BrandSpecification implements Specification<Product> {
    private String brandName;

    public BrandSpecification(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getBrand().contains(brandName);
    }
}

