package com.example.server.filesService.model;

import com.example.server.filesService.bridge.AbstractData;
import com.example.server.filesService.bridge.IFormat;
import com.example.server.model.Product;

import java.util.Collections;
import java.util.List;

public class ProductsToSave extends AbstractData {
    private final List<Product> products;

    public ProductsToSave(List<Product> products) {
        this.products = products;
    }

    @Override
    protected void saveData(IFormat format, String fileName) {
        format.saveToFile(Collections.singletonList(products), fileName);
    }
}
