package com.example.server.filesService.bridge;

import java.util.List;

public interface IFormat {
    void saveToFile(List<Object> list, String fileName);
}
