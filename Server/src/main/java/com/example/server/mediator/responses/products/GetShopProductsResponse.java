package com.example.server.mediator.responses.products;

import com.example.server.mediator.Response;
import com.example.server.model.ShopProduct;

import java.util.List;

public class GetShopProductsResponse implements Response {
    private List<ShopProduct> shopProducts;

    public List<ShopProduct> getShopProducts() {
        return shopProducts;
    }

    public void setShopProducts(List<ShopProduct> shopProducts) {
        this.shopProducts = shopProducts;
    }
}
