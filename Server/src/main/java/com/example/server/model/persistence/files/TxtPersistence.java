package com.example.server.model.persistence.files;

import com.example.server.model.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TxtPersistence {
    private final List<Product> products;
    private final String fileName;

    public TxtPersistence(List<Product> products, String fileName) {
        this.products = products;
        this.fileName = fileName;
    }

    public boolean save() {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (Product product : products) {
                writer.write("Name: " + product.getName() + "\n");
                writer.write("Price: " + product.getPrice() + "\n");
                writer.write("Brand: " + product.getBrand() + "\n\n");
            }
            writer.flush();
            writer.close();
            System.out.println("Products saved to TXT file successfully.");
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while saving products to TXT file: " + e.getMessage());
            return false;
        }
    }
}
