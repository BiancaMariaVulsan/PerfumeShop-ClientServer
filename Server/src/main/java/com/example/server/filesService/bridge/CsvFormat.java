package com.example.server.filesService.bridge;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class CsvFormat implements IFormat {
    @Override
    public void saveToFile(List<Object> list, String fileName) {
        try{
            FileWriter csvWriter = new FileWriter(fileName);
            for (Object obj : list) {
                Field[] fields = obj.getClass().getDeclaredFields();

                for (Field field : fields) {
                    csvWriter.append(field.toString());
                    csvWriter.append(",");
                }
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
            System.out.println("Products saved to CSV file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving products to CSV file: " + e.getMessage());
        }
    }
}
