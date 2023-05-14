package com.example.perfumeshop.model;

import java.util.List;

public class SaveProductsRequest {
    private final String fileName;
    private final List<Product> data;
    private final String format;

    public SaveProductsRequest(String fileName, List<Product> data, String format) {
        this.fileName = fileName;
        this.data = data;
        this.format = format;
    }

    public String getFileName() {
        return fileName;
    }

    public List<Product> getData() {
        return data;
    }

    public String getFormat() {
        return format;
    }
}
