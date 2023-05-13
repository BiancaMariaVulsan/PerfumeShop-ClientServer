package com.example.server.mediator.responses.products;

import com.example.server.mediator.Response;
import com.example.server.model.Product;

import java.util.List;

public class GetProductsAvailableInTheChainResponse implements Response {
    List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
