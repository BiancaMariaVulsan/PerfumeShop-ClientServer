package com.example.perfumeshop.controller;

import com.example.perfumeshop.model.Language;
import com.example.perfumeshop.model.Product;
import com.example.perfumeshop.model.ShopProduct;
import com.example.perfumeshop.requests.ProductRequest;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class AddProductController implements Initializable, Observer {

    @FXML
    public Label nameLabel;
    @FXML
    public Label brandLabel;
    @FXML
    public Label priceLabel;
    @FXML
    public Label productLabel;
    @FXML
    private TextField nameText;
    @FXML
    private TextField brandText;
    @FXML
    private TextField stockText;
    @FXML
    private TextField priceText;
    @FXML
    private Button saveButton;
    private final int shopId;
    private final ProductRequest productRequest = new ProductRequest();
    private final ObservableList<ShopProduct> productItems;
    private final boolean isEditing;
    private ShopProduct productToUpdate;

    public AddProductController(int shopId, ObservableList<ShopProduct>productItems) {
        this.shopId = shopId;
        this.productItems = productItems;
        this.isEditing = false;
    }

    public AddProductController(int shopId, ObservableList<ShopProduct>productItems, ShopProduct productToUpdate) {
        this.shopId = shopId;
        this.productItems = productItems;
        this.isEditing = true;
        this.productToUpdate = productToUpdate;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(isEditing) {
            nameText.setText(productToUpdate.getProduct().getName());
            brandText.setText(productToUpdate.getProduct().getBrand());
            stockText.setText(String.valueOf(productToUpdate.getStock()));
            priceText.setText(String.valueOf(productToUpdate.getProduct().getPrice()));

            nameText.setDisable(true);
            brandText.setDisable(true);
            priceText.setDisable(true);
        }

        saveButton.setOnAction(actionEvent -> {
            try {
                if(!isEditing) {
                    Product product = new Product(nameText.getText(), brandText.getText(), Float.parseFloat(priceText.getText()));
                    ShopProduct shopProduct = new ShopProduct(product, Integer.parseInt(stockText.getText()));
                    String message = productRequest.addProduct(shopProduct, shopId);
                    Controller.initAlarmBox("Success", message, Alert.AlertType.INFORMATION);
                    productItems.setAll(productRequest.getShopProducts(shopId));

                } else {
                    int newStock = Integer.parseInt(stockText.getText());
                    String message = productRequest.updateProduct(newStock, productToUpdate.getProduct().getId(), shopId);
                    Controller.initAlarmBox("Success", message, Alert.AlertType.INFORMATION);
                    productItems.setAll(productRequest.getShopProducts(shopId));
                }
            } catch (URISyntaxException | IOException | InterruptedException e) {
                Controller.initAlarmBox("Error", "Something went wrong when trying to add the product. Please make sure you insert valid properties!", Alert.AlertType.ERROR);
            }
        });
    }

    public void setNameLabel(String nameLabel) {
        this.nameLabel.setText(nameLabel);
    }

    public void setBrandLabel(String brandLabel) {
        this.brandLabel.setText(brandLabel);
    }

    public void setPriceLabel(String priceLabel) {
        this.priceLabel.setText(priceLabel);
    }

    public void setProductLabel(String productLabel) {
        this.productLabel.setText(productLabel);
    }

    public void setSaveButton(String saveButton) {
        this.saveButton.setText(saveButton);
    }

    @Override
    public void update(Observable o, Object arg) {
        setBrandLabel(((Language) arg).getBrandColumn());
        setPriceLabel(((Language) arg).getPriceColumn());
        setNameLabel(((Language) arg).getNameColumn());
        setProductLabel(((Language) arg).getProduct());
        setSaveButton(((Language) arg).getSave());
    }
}
