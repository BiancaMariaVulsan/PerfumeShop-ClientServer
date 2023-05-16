package com.example.server.productService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;
import com.example.server.mediator.responses.products.GetProductsAvailableInTheChainResponse;
import com.example.server.model.Product;
import com.example.server.model.ShopProduct;
import com.example.server.model.persistence.ProductPersistence;
import com.example.server.mediator.requests.products.FilterProductsRequest;
import com.example.server.mediator.responses.products.FilterProductsResponse;
import com.example.server.productService.specification.*;

import java.util.*;

public class FilterProductsHandler implements Handler {
    private final ProductPersistence productPersistence;

    public FilterProductsHandler() {
        productPersistence = new ProductPersistence();
    }

    @Override
    public Response onMessage(Request message) {
        FilterProductsRequest filterProductsRequest = (FilterProductsRequest) message;

        Map<Integer, List<ShopProduct>> shopProducts = productPersistence.getShopProducts();
        Set<Product> availableProducts = new HashSet<>();
        for (Map.Entry<Integer, List<ShopProduct>> entry : shopProducts.entrySet()) {
            for (ShopProduct shopProduct : entry.getValue()) {
                if (shopProduct.getStock() > 0) {
                    availableProducts.add(shopProduct.getProduct());
                }
            }
        }
        List<Product> products = new ArrayList<>(availableProducts);

        LowPriceSpecification lowPrice = new LowPriceSpecification(filterProductsRequest.getPrice());
        BrandSpecification brandSpec = new BrandSpecification(filterProductsRequest.getBrand());
        NameSpecification nameSpecification = new NameSpecification(filterProductsRequest.getName());
        ChainAvailabilitySpecification availabilitySpecification = new ChainAvailabilitySpecification(filterProductsRequest.isAvailability(), products);

        Specification<Product> filterSpec = new AndSpecification<>(lowPrice, brandSpec, nameSpecification, availabilitySpecification);

        List<Product> allProducts;
        if (filterProductsRequest.getShopId() == 0) {
            allProducts = productPersistence.findAll();
        } else {
            allProducts = shopProducts.get(filterProductsRequest.getShopId()).stream().map(ShopProduct::getProduct).toList();
        }
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
