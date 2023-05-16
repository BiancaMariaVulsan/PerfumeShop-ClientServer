package com.example.server.productService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;
import com.example.server.mediator.requests.products.FilterShopProductRequest;
import com.example.server.mediator.responses.persons.FilterShopProductsResponse;
import com.example.server.model.Product;
import com.example.server.model.ShopProduct;
import com.example.server.model.persistence.ProductPersistence;
import com.example.server.productService.specification.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilterShopProductHandler implements Handler {
    private final ProductPersistence productPersistence;

    public FilterShopProductHandler() {
        this.productPersistence = new ProductPersistence();
    }

    @Override
    public Response onMessage(Request message) {
        FilterShopProductRequest filterProductsRequest = (FilterShopProductRequest) message;

        Map<Integer, List<ShopProduct>> shopProducts = productPersistence.getShopProducts();
        List<ShopProduct> products = shopProducts.get(filterProductsRequest.getShopId());

        LowPriceSpecification lowPrice= new LowPriceSpecification(filterProductsRequest.getPrice());
        BrandSpecification brandSpec = new BrandSpecification(filterProductsRequest.getBrand());
        NameSpecification nameSpecification = new NameSpecification(filterProductsRequest.getName());
        ShopAvailabilitySpecification availabilitySpecification = new ShopAvailabilitySpecification(filterProductsRequest.isAvailability());

        Specification<Product> filterSpec = new AndSpecification<>(lowPrice, brandSpec, nameSpecification);
        Specification<ShopProduct> filterSPSpec = new AndSpecification<>(availabilitySpecification);

        List<ShopProduct> filteredProducts = new ArrayList<>();
        for (ShopProduct sp : products) {
            if (filterSpec.isSatisfiedBy(sp.getProduct()) && filterSPSpec.isSatisfiedBy(sp)) {
                filteredProducts.add(sp);
            }
        }

        return new FilterShopProductsResponse(filteredProducts);
    }
}
