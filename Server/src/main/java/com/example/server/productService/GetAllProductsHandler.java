package com.example.server.productService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;
import com.example.server.model.Product;
import com.example.server.model.persistence.ProductPersistence;
import com.example.server.mediator.responses.products.GetAllProductsResponse;

import java.util.List;

public class GetAllProductsHandler implements Handler {
    private final ProductPersistence productPersistence;

    public GetAllProductsHandler() {
        productPersistence = new ProductPersistence();
    }

    @Override
    public Response onMessage(Request message) {
        List<Product> products = productPersistence.findAll();
        GetAllProductsResponse getAllProductsResponse = new GetAllProductsResponse();
        getAllProductsResponse.setProducts(products);
        return getAllProductsResponse;
    }
}
