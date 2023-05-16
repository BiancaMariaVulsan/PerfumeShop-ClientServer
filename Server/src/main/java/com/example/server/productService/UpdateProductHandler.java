package com.example.server.productService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;
import com.example.server.mediator.requests.products.UpdateProductRequest;
import com.example.server.mediator.responses.products.UpdateProductResponse;
import com.example.server.model.persistence.ProductPersistence;

public class UpdateProductHandler implements Handler {
    private final ProductPersistence productPersistence;

    public UpdateProductHandler() {
        this.productPersistence = new ProductPersistence();
    }

    @Override
    public Response onMessage(Request message) {
        UpdateProductRequest updateProductRequest = (UpdateProductRequest) message;
        productPersistence.updateStockOfProduct(updateProductRequest.getShopId(), updateProductRequest.getProductId(), updateProductRequest.getNewStock());
        return new UpdateProductResponse("Product updated successfully");
    }
}
