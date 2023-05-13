package com.example.server.productService.specification;

public interface Specification<T> {
    boolean isSatisfiedBy(T entity);

    default Specification<T> and(Specification<T> other) {
        return new AndSpecification<>(this, other);
    }
}

