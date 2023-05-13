package com.example.server.productService.specification;

import com.example.server.model.Product;

import java.util.Locale;

public class NameSpecification implements Specification<Product> {
    private final String name;

    public NameSpecification(String name) {
        this.name = name.toLowerCase(Locale.ROOT);
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getName().toLowerCase().contains(name);
    }
}
