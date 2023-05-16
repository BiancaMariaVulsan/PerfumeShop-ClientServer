package com.example.server.api;

import com.example.server.mediator.IMediator;
import com.example.server.mediator.Mediator;
import com.example.server.mediator.requests.products.*;
import com.example.server.mediator.responses.persons.FilterShopProductsResponse;
import com.example.server.mediator.responses.products.*;
import com.example.server.model.Product;
import com.example.server.model.ProductInShop;
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

    @GetMapping("/products/available")
    public ResponseEntity<List<Product>> getProductsAvailableInTheChain() {
        GetProductsAvailableInTheChainRequest request = new GetProductsAvailableInTheChainRequest();
        GetProductsAvailableInTheChainResponse response = (GetProductsAvailableInTheChainResponse) mediator.send(request);

        return ResponseEntity.ok(response.getProducts());
    }

    @PostMapping("/filter_products")
    public ResponseEntity<List<Product>> login(@RequestBody FilterProductsRequest filterProductsRequest) {
        FilterProductsResponse response = (FilterProductsResponse) mediator.send(filterProductsRequest);

        return ResponseEntity.ok(response.getFilteredProducts());
    }

    @PostMapping("/filter_shop_products")
    public ResponseEntity<List<ShopProduct>> login(@RequestBody FilterShopProductRequest filterProductsRequest) {
        FilterShopProductsResponse response = (FilterShopProductsResponse) mediator.send(filterProductsRequest);

        return ResponseEntity.ok(response.getShopProductList());
    }

    @PostMapping("/add_product")
    public ResponseEntity<String> addProduct(@RequestBody ProductInShop productInShop) {
        AddProductRequest addProductRequest = new AddProductRequest(productInShop.getShopProduct(), productInShop.getShopId());
        AddProductResponse response = (AddProductResponse) mediator.send(addProductRequest);

        return ResponseEntity.ok(response.getMessage());
    }

    @PostMapping("/save_products")
    public ResponseEntity<String> saveProducts(@RequestBody SaveProductsRequest saveProductsRequest) {
        SaveProductsResponse response = (SaveProductsResponse) mediator.send(saveProductsRequest);

        return ResponseEntity.ok(response.getMessage());
    }
}

