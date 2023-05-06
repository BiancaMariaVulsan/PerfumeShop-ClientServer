package com.example.productservice.api;

import com.example.productservice.logic.ProductLogic;
import com.example.productservice.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        ProductLogic productLogic = new ProductLogic();
        List<Product> products = productLogic.getAllProducts();
        return ResponseEntity.ok(products);
    }

}
