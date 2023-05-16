package com.example.server.filesService.model;

import com.example.server.filesService.bridge.AbstractData;
import com.example.server.filesService.bridge.IFormat;
import com.example.server.model.Product;

import java.util.Collections;
import java.util.List;

public class ProductsToSave extends AbstractData {
    private final List<Product> products;

    public ProductsToSave(IFormat format, List<Product> products) {
        super(format);
        this.products = products;
    }

    @Override
    public void saveData(String fileName) {
        format.saveToFile(products, fileName);
    }
}
