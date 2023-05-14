package com.example.server.model;

public class ProductInShop {
    private final ShopProduct shopProduct;
    private final int shopId;

    public ProductInShop(ShopProduct shopProduct, int shopId) {
        this.shopProduct = shopProduct;
        this.shopId = shopId;
    }

    public ShopProduct getShopProduct() {
        return shopProduct;
    }

    public int getShopId() {
        return shopId;
    }
}
