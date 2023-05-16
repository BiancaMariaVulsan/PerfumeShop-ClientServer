package com.example.server.filesService.bridge;

public abstract class AbstractData {
    protected final IFormat format;

    public AbstractData(IFormat format) {
        this.format = format;
    }

    public abstract void saveData(String fileName);
}
