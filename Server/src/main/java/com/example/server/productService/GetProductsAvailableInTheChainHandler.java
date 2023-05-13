package com.example.server.productService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;
import com.example.server.mediator.responses.products.GetProductsAvailableInTheChainResponse;
import com.example.server.model.Product;
import com.example.server.model.ShopProduct;
import com.example.server.model.persistence.ProductPersistence;

import java.util.*;

public class GetProductsAvailableInTheChainHandler implements Handler {
    private final ProductPersistence productPersistence;

    public GetProductsAvailableInTheChainHandler() {
        productPersistence = new ProductPersistence();
    }

    @Override
    public Response onMessage(Request message) {
        Map<Integer, List<ShopProduct>> shopProducts = productPersistence.getShopProducts();
        GetProductsAvailableInTheChainResponse getShopProductsResponse = new GetProductsAvailableInTheChainResponse();
        Set<Product> availableProducts = new HashSet<>();
        for(Map.Entry<Integer, List<ShopProduct>> entry : shopProducts.entrySet()) {
            for(ShopProduct shopProduct : entry.getValue()) {
                if(shopProduct.getStock() > 0) {
                    availableProducts.add(shopProduct.getProduct());
                }
            }
        }
        List<Product> products = new ArrayList<>(availableProducts);
        getShopProductsResponse.setProducts(products);
        return getShopProductsResponse;
    }
}
