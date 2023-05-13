package com.example.server.api;

import com.example.server.mediator.IMediator;
import com.example.server.mediator.Mediator;
import com.example.server.mediator.requests.persons.LoginPersonRequest;
import com.example.server.mediator.requests.products.FilterProductsRequest;
import com.example.server.mediator.requests.products.GetAllProductsRequest;
import com.example.server.mediator.requests.products.GetProductsAvailableInTheChainRequest;
import com.example.server.mediator.requests.products.GetShopProductsRequest;
import com.example.server.mediator.responses.persons.LoginResponse;
import com.example.server.mediator.responses.products.FilterProductsResponse;
import com.example.server.mediator.responses.products.GetAllProductsResponse;
import com.example.server.mediator.responses.products.GetProductsAvailableInTheChainResponse;
import com.example.server.mediator.responses.products.GetShopProductsResponse;
import com.example.server.model.Filters;
import com.example.server.model.LoginPerson;
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
    public ResponseEntity<List<Product>> login(@RequestBody Filters filters) {
        FilterProductsRequest filterProductsRequest = new FilterProductsRequest(filters.getName(), filters.getBrand(), filters.getPrice(), filters.isAvailability());
        FilterProductsResponse response = (FilterProductsResponse) mediator.send(filterProductsRequest);

        return ResponseEntity.ok(response.getFilteredProducts());
    }
}

