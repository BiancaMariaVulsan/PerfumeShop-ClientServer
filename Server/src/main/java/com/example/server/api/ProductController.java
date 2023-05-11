package com.example.server.api;

import com.example.server.mediator.Mediator;
import com.example.server.mediator.ServiceMediatorImpl;
import com.example.server.model.Product;
import com.example.server.model.ShopProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final Mediator mediator = new ServiceMediatorImpl();

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = (List<Product>) mediator.notify("productService", "allProducts");
        return ResponseEntity.ok(products);
    }

    @GetMapping("/shop_products")
    public ResponseEntity<List<ShopProduct>> getShopProducts(@RequestParam int shopId) {
        List<ShopProduct> shopProducts = (List<ShopProduct>) mediator.notify("productService", shopId);
        return ResponseEntity.ok(shopProducts);
    }
}

