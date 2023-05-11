package com.example.server.productService.logic.filters;

public class NotSpecification<T> implements Specification<T> {
    private Specification<T> spec;

    public NotSpecification(Specification<T> spec) {
        this.spec = spec;
    }

    public boolean isSatisfiedBy(T entity) {
        return !spec.isSatisfiedBy(entity);
    }
}
