package com.example.server.productService.specification;

import com.example.server.model.Product;

public class BrandSpecification implements Specification<Product> {
    private final String brandName;

    public BrandSpecification(String brandName) {
        this.brandName = brandName.toLowerCase();
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getBrand().toLowerCase().contains(brandName);
    }
}

