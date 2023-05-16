package com.example.server.filesService.model;

import com.example.server.filesService.bridge.AbstractData;
import com.example.server.filesService.bridge.IFormat;
import com.example.server.model.ShopProduct;

import java.util.Collections;
import java.util.List;

public class ShopProductsToSave extends AbstractData {

   private final List<ShopProduct> products;

    public ShopProductsToSave(IFormat format, List<ShopProduct> products) {
        super(format);
        this.products = products;
    }

    @Override
    public void saveData(String fileName) {
        format.saveToFile(Collections.singletonList(products), fileName);
    }
}
