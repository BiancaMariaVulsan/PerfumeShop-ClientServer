package com.example.server.mediator.responses.products;

import com.example.server.mediator.Response;
import com.example.server.model.Product;

import java.util.List;

public class FilterProductsResponse implements Response {
    List<Product> filteredProducts;

    public void setFilteredProducts(List<Product> filteredProducts) {
        this.filteredProducts = filteredProducts;
    }

    public List<Product> getFilteredProducts() {
        return filteredProducts;
    }
}
