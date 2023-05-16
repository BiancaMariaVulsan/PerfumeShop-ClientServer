package com.example.server.filesService.bridge;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class CsvFormat implements IFormat {
    @Override
    public void saveToFile(List<?> list, String fileName) {
        try{
            FileWriter csvWriter = new FileWriter(fileName);
            for (Object obj : list) {
                Field[] fields = obj.getClass().getDeclaredFields();

                // Write the headers to the CSV file
                for (Field field : fields) {
                    csvWriter.append(field.getName());
                    csvWriter.append(",");
                }
                csvWriter.append("\n");

                // Write the values to the CSV file
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    csvWriter.append(String.valueOf(value));
                    csvWriter.append(",");
                }
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
            System.out.println("Products saved to CSV file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving products to CSV file: " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
