package com.example.server.filesService;

import com.example.server.filesService.bridge.IFormat;
import com.example.server.filesService.model.ShopProductsToSave;
import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;
import com.example.server.mediator.requests.products.SaveShopProductsRequest;
import com.example.server.mediator.responses.products.SaveShopProductsResponse;

public class SaveShopProductsHandler implements Handler {
    @Override
    public Response onMessage(Request message) {
        SaveShopProductsRequest saveProductsRequest = (SaveShopProductsRequest) message;
        IFormat format = saveProductsRequest.getFormat();
        ShopProductsToSave abstractData = new ShopProductsToSave(format, saveProductsRequest.getData());
        abstractData.saveData(saveProductsRequest.getFileName());
        return new SaveShopProductsResponse("Products saved successfully!");
    }
}
