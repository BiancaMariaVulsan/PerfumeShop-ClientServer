package com.example.server.productService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;
import com.example.server.mediator.requests.products.AddProductRequest;
import com.example.server.mediator.responses.products.AddProductResponse;
import com.example.server.model.Product;
import com.example.server.model.persistence.ProductPersistence;

public class AddProductHandler implements Handler {
    private final ProductPersistence productPersistence;

    public AddProductHandler() {
        this.productPersistence = new ProductPersistence();
    }

    @Override
    public Response onMessage(Request message) {
        AddProductResponse addProductResponse = new AddProductResponse();
        AddProductRequest addProductRequest = (AddProductRequest) message;
        try {
            Product product = addProductRequest.getShopProduct().getProduct();
            productPersistence.insert(product);
            Product insertedProduct = productPersistence.findAll()
                    .stream().filter(p -> p.getName().equals(product.getName()) && p.getBrand().equals(product.getBrand())
                            && p.getPrice() == product.getPrice())
                    .findFirst()
                    .orElse(null);
            productPersistence.insertProductInShop(addProductRequest.getShopId(), insertedProduct.getId(), addProductRequest.getShopProduct().getStock());
            addProductResponse.setMessage("Product added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            addProductResponse.setMessage("Product could not be added!");
        }
        return addProductResponse;
    }
}
