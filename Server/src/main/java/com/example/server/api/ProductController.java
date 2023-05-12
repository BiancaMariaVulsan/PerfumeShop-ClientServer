package com.example.server.api;

import com.example.server.mediator.IMediator;
import com.example.server.mediator.Mediator;
import com.example.server.model.Product;
import com.example.server.model.ShopProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final IMediator mediator = new Mediator();

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
//        List<Product> products = (List<Product>) mediator.send("productService", "allProducts");
//        return ResponseEntity.ok(products);
        return null;
    }

    @GetMapping("/shop_products")
    public ResponseEntity<List<ShopProduct>> getShopProducts(@RequestParam int shopId) {
//        List<ShopProduct> shopProducts = (List<ShopProduct>) mediator.send("productService", shopId);
//        return ResponseEntity.ok(shopProducts)
        return null;
    }
}

