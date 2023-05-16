package com.example.server.productService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;
import com.example.server.mediator.requests.products.DeleteProductRequest;
import com.example.server.mediator.responses.products.DeleteProductResponse;
import com.example.server.model.persistence.ProductPersistence;

public class DeleteProductHandler implements Handler {
    private final ProductPersistence productPersistence;

    public DeleteProductHandler() {
        this.productPersistence = new ProductPersistence();
    }

    @Override
    public Response onMessage(Request message) {
        DeleteProductRequest deleteProductRequest = (DeleteProductRequest) message;
        productPersistence.deleteProductFromShop(deleteProductRequest.getShopId(), deleteProductRequest.getProductId());
        return new DeleteProductResponse("Product deleted successfully");
    }
}
