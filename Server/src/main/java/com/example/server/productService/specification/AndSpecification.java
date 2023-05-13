package com.example.server.productService.specification;

public class AndSpecification<T> implements Specification<T> {
    private final Specification<T>[] specs;

    @SafeVarargs
    public AndSpecification(Specification<T>... specs) {
        this.specs = specs;
    }

    @Override
    public boolean isSatisfiedBy(T t) {
        for (Specification<T> spec : specs) {
            if (!spec.isSatisfiedBy(t)) {
                return false;
            }
        }
        return true;
    }
}

