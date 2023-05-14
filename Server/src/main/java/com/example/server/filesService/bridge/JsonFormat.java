package com.example.server.filesService.bridge;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonFormat implements IFormat {

    @Override
    public void saveToFile(List<Object> list, String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(fileName), list);
            System.out.println("Products saved to JSON file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving products to JSON file: " + e.getMessage());
        }
    }
}
