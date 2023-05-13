package com.example.server.productService.specification;

import com.example.server.model.Product;

import java.util.List;

public class ChainAvailabilitySpecification implements Specification<Product> {
    private final boolean availability;
    private final List<Product> availableProducts;

    public ChainAvailabilitySpecification(boolean availability, List<Product> availableProducts) {
        this.availability = availability;
        this.availableProducts = availableProducts;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {

        return (!availability || isAvailableInTheChain(product));
    }

    private boolean isAvailableInTheChain(Product product) {
        return availableProducts.stream().anyMatch(p -> p.getId() == product.getId());
    }
}
