package com.example.server.model.persistence.files;

import com.example.server.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonPersistence {
    private final List<Product> products;
    private final String fileName;

    public JsonPersistence(List<Product> products, String fileName) {
        this.products = products;
        this.fileName = fileName;
    }

    public boolean save() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(fileName), products);
            System.out.println("Products saved to JSON file successfully.");
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while saving products to JSON file: " + e.getMessage());
            return false;
        }
    }
}
