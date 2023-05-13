package com.example.server.productService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;
import com.example.server.model.ShopProduct;
import com.example.server.model.persistence.ProductPersistence;
import com.example.server.mediator.requests.products.GetShopProductsRequest;
import com.example.server.mediator.responses.products.GetShopProductsResponse;

import java.util.List;
import java.util.Map;

public class GetShopProductsHandler implements Handler {
    private final ProductPersistence productPersistence;

    public GetShopProductsHandler() {
        productPersistence = new ProductPersistence();
    }

    @Override
    public Response onMessage(Request message) {
        Map<Integer, List<ShopProduct>> shopProducts = productPersistence.getShopProducts();
        GetShopProductsRequest getShopProductsRequest = (GetShopProductsRequest) message;
        int shopId = getShopProductsRequest.getShopId();
        GetShopProductsResponse getShopProductsResponse = new GetShopProductsResponse();
        getShopProductsResponse.setShopProducts(shopProducts.get(shopId));
        return getShopProductsResponse;
    }
}
