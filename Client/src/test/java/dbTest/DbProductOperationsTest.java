package dbTest;

import com.example.perfumeshop.model.Product;
import com.example.perfumeshop.model.ShopProduct;
import com.example.perfumeshop.model.persistence.ProductPersistence;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DbProductOperationsTest {
    ProductPersistence productPersistence = new ProductPersistence();
    @Test
    public void insertTest() {
        int shopId = 3;
        boolean added = false;
        Product product = productPersistence.findAll().get(0);
        // attach the first product from products table to the corresponding shop
        productPersistence.insertProductInShop(shopId, product.getId(), 5);
        var dbProducts = productPersistence.getShopProducts().get(shopId);
        for(ShopProduct prod : dbProducts) {
            if(prod.getProduct().getId() == product.getId()) {
                added = true;
            }
        }
        assertTrue(added == true);
    }
    @Test
    public void readTest() {
        List<Product> dbProduct = productPersistence.findAll();
        assertTrue(dbProduct.size() > 0);
    }
    @Test
    public void updateTest() {
        int shopiId = 1;
        Product product = productPersistence.findAll().get(0);
        productPersistence.updateStockOfProduct(shopiId, product.getId(), 10);
        var dbProducts = productPersistence.getShopProducts().get(shopiId);
        for(ShopProduct prod : dbProducts) {
            if(prod.getProduct().getId() == product.getId()) {
                assertTrue(prod.getStock() == 10);
            }
        }
    }
    @Test
    public void deleteTest() {
        // delete the first person from the db
        int shopiId = 1;
        boolean isRemoved = true;
        Product product = productPersistence.findAll().get(0);
        productPersistence.deleteProductFromShop(shopiId, product.getId());
        var dbProducts = productPersistence.getShopProducts().get(shopiId);
        for(ShopProduct prod : dbProducts) {
            if(prod.getProduct().getId() == product.getId()) {
                isRemoved = false;
            }
        }
        assertTrue(isRemoved == true);

    }
}
