package com.example.server.productService.specification;

import com.example.server.model.Product;
import com.example.server.model.ShopProduct;
import com.example.server.model.persistence.ProductPersistence;

import java.util.List;
import java.util.Map;

public class ChainAvailabilitySpecification implements Specification<Product> {
    private final boolean availability;

    public ChainAvailabilitySpecification(boolean availability) {
        this.availability = availability;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return (!availability || isAvailableInTheChain(product.getId()));
    }

    private boolean isAvailableInTheChain(int productId) {
        ProductPersistence productPersistence = new ProductPersistence();
        Map<Integer, List<ShopProduct>> productsMap = productPersistence.getShopProducts();
        for(Map.Entry<Integer, List<ShopProduct>> entry : productsMap.entrySet()) {
            for(ShopProduct shopProduct : entry.getValue()) {
                if(shopProduct.getProduct().getId() == productId && shopProduct.getStock() > 0)
                    return true;
            }
        }
        return false;
    }
}
