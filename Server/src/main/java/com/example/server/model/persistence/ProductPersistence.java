package com.example.server.model.persistence;

import com.example.server.model.Product;
import com.example.server.model.ShopProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class ProductPersistence extends DatabaseObj<Product> {
    public boolean checkIfObjectAlreadyExists(Product product) {
        String query = "SELECT COUNT (*) AS counter FROM product WHERE name = \'"  + product.getName() + "\'";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int count = 0;
        try {
            if(resultSet.next()){
                count = resultSet.getInt("counter");
            }
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        } finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return count == 0;
    }

    private Map<Integer, List<ShopProduct>> createMap(ResultSet resultSet) {
        try{
            Map<Integer, List<ShopProduct>> map = new HashMap<>();
            while (resultSet.next())
            {
                int idProduct = resultSet.getInt("id_product");
                int idShop = resultSet.getInt("id_shop");
                int stock = resultSet.getInt("stock");
                Product product = findById(idProduct);

                ShopProduct shopProduct = new ShopProduct(product, stock);

                if(map.get(idShop) == null) {
                    List<ShopProduct> products = new ArrayList<>();
                    products.add(shopProduct);
                    map.put(idShop, products);
                } else {
                    map.get(idShop).add(shopProduct);
                }
            }
            return map;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Map<Integer, List<ShopProduct>> getShopProducts() {
        String query = "SELECT * FROM shop_product";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createMap(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            return null;
        } finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public void insertProductInShop(int shopId, int productId, int stock) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "INSERT INTO shop_product (id_product, id_shop, stock) VALUES (" + productId + "," + shopId + "," + stock +")";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally{
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public void deleteProductFromShop(int shopId, int productId) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM shop_product WHERE id_product=" + productId + " AND id_shop=" + shopId;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally{
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public void updateStockOfProduct(int shopId, int productId, int stock) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "UPDATE shop_product SET stock=" + stock + " WHERE id_shop=" + shopId + " AND id_product=" + productId;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally{
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
