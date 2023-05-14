package com.example.server.mediator.requests.products;

import com.example.server.filesService.bridge.*;
import com.example.server.mediator.Request;
import com.example.server.model.Product;

import java.util.List;

public class SaveProductsRequest implements Request {
    private final String fileName;
    private final List<Product> data;
    private final String format;

    public SaveProductsRequest(String fileName, List<Product> data, String format) {
        this.fileName = fileName;
        this.data = data;
        this.format = format;
    }

    public String getFileName() {
        return fileName;
    }

    public List<Product> getData() {
        return data;
    }

    public IFormat getFormat() {
        if(format.equals("json")) {
            return new JsonFormat();
        }
        else if(format.equals("xml")) {
            return new XmlFormat();
        }
        else if(format.equals("txt")) {
            return new TxtFormat();
        }
        else {
            return new CsvFormat();
        }
    }
}
