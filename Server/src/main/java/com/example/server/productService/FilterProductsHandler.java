package com.example.server.productService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;
import com.example.server.model.Product;
import com.example.server.model.persistence.ProductPersistence;
import com.example.server.mediator.requests.products.FilterProductsRequest;
import com.example.server.mediator.responses.products.FilterProductsResponse;
import com.example.server.productService.specification.*;

import java.util.ArrayList;
import java.util.List;

public class FilterProductsHandler implements Handler {
    private final ProductPersistence productPersistence;

public FilterProductsHandler() {
        productPersistence = new ProductPersistence();
    }

    @Override
    public Response onMessage(Request message) {
        FilterProductsRequest filterProductsRequest = (FilterProductsRequest) message;
        float price = filterProductsRequest.getPrice();
        String brand = filterProductsRequest.getBrand();
        String name = filterProductsRequest.getName();
        boolean availability = filterProductsRequest.isAvailability();

        LowPriceSpecification lowPrice= new LowPriceSpecification(price);
        BrandSpecification brandSpec = new BrandSpecification(brand);
        NameSpecification nameSpecification = new NameSpecification(name);
        ChainAvailabilitySpecification availabilitySpecification = new ChainAvailabilitySpecification(availability);

        Specification<Product> filterSpec = new AndSpecification<>(lowPrice, brandSpec, nameSpecification, availabilitySpecification);

        List<Product> allProducts = productPersistence.findAll();
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (filterSpec.isSatisfiedBy(product)) {
                filteredProducts.add(product);
            }
        }

        FilterProductsResponse filterProductsResponse = new FilterProductsResponse();
        filterProductsResponse.setFilteredProducts(filteredProducts);
        return filterProductsResponse;
    }
}
