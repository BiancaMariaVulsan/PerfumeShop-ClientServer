package com.example.server.mediator.responses.persons;

import com.example.server.mediator.Response;
import com.example.server.model.ShopProduct;

import java.util.List;

public class FilterShopProductsResponse implements Response {
    private List<ShopProduct> shopProductList;

    public FilterShopProductsResponse(List<ShopProduct> shopProductList) {
        this.shopProductList = shopProductList;
    }

    public List<ShopProduct> getShopProductList() {
        return shopProductList;
    }
}
