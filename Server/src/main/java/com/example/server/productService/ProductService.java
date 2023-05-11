package com.example.server.productService;

import com.example.server.mediator.Service;
import com.example.server.productService.logic.ProductLogic;

public class ProductService implements Service {
    @Override
    public Object onMessage(Object message) {
        ProductLogic productLogic = new ProductLogic();
        if(message.equals("allProducts")) {
            return productLogic.getAllProducts();
        } else {
            return productLogic.getShopProducts((Integer) message);
        }
    }
}
