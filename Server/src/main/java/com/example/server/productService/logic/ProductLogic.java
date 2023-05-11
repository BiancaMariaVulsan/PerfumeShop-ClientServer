package com.example.server.productService.logic;

import com.example.server.model.Product;
import com.example.server.model.ShopProduct;
import com.example.server.model.persistence.ProductPersistence;

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
}
