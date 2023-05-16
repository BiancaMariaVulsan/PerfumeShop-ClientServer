package com.example.server.filesService.bridge;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class TxtFormat implements IFormat {
    @Override
    public void saveToFile(List<?> list, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (Object obj : list) {
                Field[] fields = obj.getClass().getDeclaredFields();

                for (Field field : fields) {
                    field.setAccessible(true);
                    writer.write(field.getName() + ": " + field.get(obj) + "\n");
                }
                writer.write("\n");
            }
            writer.flush();
            writer.close();
            System.out.println("Objects saved to TXT file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving objects to TXT file: " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
