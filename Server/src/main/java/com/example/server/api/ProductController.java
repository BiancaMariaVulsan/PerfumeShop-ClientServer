package com.example.server.api;

import com.example.server.mediator.IMediator;
import com.example.server.mediator.Mediator;
import com.example.server.mediator.requests.products.GetAllProductsRequest;
import com.example.server.mediator.requests.products.GetShopProductsRequest;
import com.example.server.mediator.responses.products.GetAllProductsResponse;
import com.example.server.mediator.responses.products.GetShopProductsResponse;
import com.example.server.model.Product;
import com.example.server.model.ShopProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final IMediator mediator = new Mediator();

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        GetAllProductsRequest request = new GetAllProductsRequest();
        GetAllProductsResponse response = (GetAllProductsResponse) mediator.send(request);

        return ResponseEntity.ok(response.getProducts());
    }

    @GetMapping("/shop_products")
    public ResponseEntity<List<ShopProduct>> getShopProducts(@RequestParam int shopId) {
        GetShopProductsRequest request = new GetShopProductsRequest();
        request.setShopId(shopId);
        GetShopProductsResponse response = (GetShopProductsResponse) mediator.send(request);

        return ResponseEntity.ok(response.getShopProducts());
    }
}

