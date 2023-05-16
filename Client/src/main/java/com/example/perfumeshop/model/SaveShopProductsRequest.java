package com.example.perfumeshop.model;

import java.util.List;

public class SaveShopProductsRequest {
    private final String fileName;
    private final List<ShopProduct> data;
    private final String format;

    public SaveShopProductsRequest(String fileName, List<ShopProduct> data, String format) {
        this.fileName = fileName;
        this.data = data;
        this.format = format;
    }

    public String getFileName() {
        return fileName;
    }

    public List<ShopProduct> getData() {
        return data;
    }

    public String getFormat() {
        return format;
    }
}
