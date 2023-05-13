package com.example.server.productService.specification;

public class OrSpecification<T> implements Specification<T> {
    private Specification<T> spec1;
    private Specification<T> spec2;

    public OrSpecification(Specification<T> spec1, Specification<T> spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    public boolean isSatisfiedBy(T entity) {
        return spec1.isSatisfiedBy(entity) || spec2.isSatisfiedBy(entity);
    }
}

