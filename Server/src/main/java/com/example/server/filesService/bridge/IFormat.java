package com.example.server.filesService.bridge;

import com.example.server.model.ShopProduct;

import java.util.List;

public interface IFormat {
    void saveToFile(List<?> list, String fileName);
}
