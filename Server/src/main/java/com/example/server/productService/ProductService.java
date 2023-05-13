package com.example.server.productService;

import com.example.server.mediator.Handler;
import com.example.server.mediator.Request;
import com.example.server.mediator.Response;

public class ProductService implements Handler {
    @Override
    public Response onMessage(Request message) {
        return null;
    }
//    @Override
//    public Object onMessage(Object message) {
//        ProductLogic productLogic = new ProductLogic();
//        if(message.equals("allProducts")) {
//            return productLogic.getAllProducts();
//        } else {
//            return productLogic.getShopProducts((Integer) message);
//        }
//    }
}
