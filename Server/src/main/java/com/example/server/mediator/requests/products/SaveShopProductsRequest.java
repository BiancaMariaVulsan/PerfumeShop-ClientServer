package com.example.server.mediator.requests.products;

import com.example.server.filesService.bridge.*;
import com.example.server.mediator.Request;
import com.example.server.model.ShopProduct;

import java.util.List;

public class SaveShopProductsRequest implements Request {
    private final String fileName;
    private final List<ShopProduct> data;
    private final String format;

    public SaveShopProductsRequest(String fileName, List<ShopProduct> data, String format) {
        this.fileName = fileName;
        this.data = data;
        this.format = format;
    }

    public String getFileName() {
        return fileName;
    }

    public List<ShopProduct> getData() {
        return data;
    }

    // factory method
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
