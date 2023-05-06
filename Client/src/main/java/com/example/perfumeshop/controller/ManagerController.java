package com.example.perfumeshop.controller;

import com.example.perfumeshop.model.Product;
import com.example.perfumeshop.requests.ProductRequest;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {
    @FXML
    private TableView<Product> productTableView;
    private final ObservableList<Product> productItems = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product ,String> brandColumn;
    @FXML
    private TableColumn<Product, Boolean> availabilityColumn;
    @FXML
    private TableColumn<Product, Number> priceColumn;

    @FXML
    private TextField nameFilter;
    @FXML
    private TextField brandFilter;
    @FXML
    private CheckBox availabilityFilter;
    @FXML
    private TextField priceFilter;

    @FXML
    private Button filterButton;
    @FXML
    private Button sortNameButton;
    @FXML
    private Button sortPriceButton;

    @FXML
    private Button saveCSV;
    @FXML
    private Button saveJSON;
    @FXML
    private Button saveXML;
    @FXML
    private Button saveTXT;
    @FXML
    private Button brandAnalysisButton;
    @FXML
    public Button priceAnalysisButton;

    @FXML
    private ChoiceBox<String> shopChoice;
    private final ProductRequest productRequest = new ProductRequest();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            populateTableProducts();
        } catch (URISyntaxException | IOException | InterruptedException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private void populateTableProducts() throws URISyntaxException, IOException, InterruptedException {
        productItems.clear();
        productTableView.getItems().clear();
        nameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
        brandColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getBrand()));
        availabilityColumn.setCellValueFactory(cellData -> {
            try {
                return new ReadOnlyBooleanWrapper(productRequest.isAvailableInTheChain(cellData.getValue().getId()));
            } catch (URISyntaxException | IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        priceColumn.setCellValueFactory(cellData -> new ReadOnlyDoubleWrapper(cellData.getValue().getPrice()));
        productItems.addAll(productRequest.getAllProducts());
        productTableView.setItems(productItems);
    }
}
