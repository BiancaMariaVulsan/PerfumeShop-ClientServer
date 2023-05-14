package com.example.server.filesService;

import com.example.server.filesService.bridge.IFormat;
import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;
import com.example.server.mediator.requests.products.SaveProductsRequest;
import com.example.server.mediator.responses.products.SaveProductsResponse;

import java.util.Collections;

public class SaveProductsHandler implements Handler {
    @Override
    public Response onMessage(Request message) {
        SaveProductsRequest saveProductsRequest = (SaveProductsRequest) message;
        IFormat format = saveProductsRequest.getFormat();
        format.saveToFile(Collections.singletonList(saveProductsRequest.getData()), saveProductsRequest.getFileName());
        SaveProductsResponse saveProductsResponse = new SaveProductsResponse("Products saved successfully!"); // todo: change message
        return saveProductsResponse;
    }
}
