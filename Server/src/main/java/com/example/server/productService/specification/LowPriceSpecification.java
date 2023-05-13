package com.example.server.productService.specification;

import com.example.server.model.Product;

public class LowPriceSpecification implements Specification<Product> {

    private double maxPrice;

    public LowPriceSpecification(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getPrice() > 0;
    }
}
