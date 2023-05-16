package com.example.server.productService.specification;

import com.example.server.model.ShopProduct;

public class ShopAvailabilitySpecification implements Specification<ShopProduct>{
    boolean availability;

    public ShopAvailabilitySpecification(boolean availability) {
        this.availability = availability;
    }

    @Override
    public boolean isSatisfiedBy(ShopProduct entity) {
        return (entity.getStock() > 0 || !availability);
    }
}
