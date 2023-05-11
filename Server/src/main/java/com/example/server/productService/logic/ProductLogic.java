package com.example.server.productService.logic;

import com.example.server.model.Product;
import com.example.server.model.ShopProduct;
import com.example.server.model.persistence.ProductPersistence;
import com.example.server.productService.logic.filters.AndSpecification;
import com.example.server.productService.logic.filters.BrandSpecification;
import com.example.server.productService.logic.filters.LowPriceSpecification;
import com.example.server.productService.logic.filters.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductLogic {
    private final ProductPersistence productPersistence = new ProductPersistence();
    Map<Integer, List<ShopProduct>> productsMap;

    public ProductLogic() {
        if (this.productsMap == null) {
            this.productsMap = getProductsMap();
        }
    }

    private Map<Integer, List<ShopProduct>> getProductsMap() {
        return productPersistence.getShopProducts();
    }

    public List<Product> getAllProducts() {
        return productPersistence.findAll();
    }

    public List<ShopProduct> getShopProducts(int idShop) {
        return productsMap.get(idShop);
    }

    public List<Product> filterProducts(String name, String brand, boolean availability, Float price, int shopId) {
        LowPriceSpecification lowPrice= new LowPriceSpecification(price);
        BrandSpecification brandSpec = new BrandSpecification(brand);
        // todo: complete with other filters

        Specification<Product> filterSpec = new AndSpecification<>(lowPrice, brandSpec);

        List<Product> allProducts = getAllProducts();
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (filterSpec.isSatisfiedBy(product)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
}
