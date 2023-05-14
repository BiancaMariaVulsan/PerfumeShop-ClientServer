package com.example.perfumeshop.controller;

import com.example.perfumeshop.model.Language;
import com.example.perfumeshop.model.Product;
import com.example.perfumeshop.requests.LanguageRequest;
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
import java.util.*;

public class ManagerController extends Observable implements Initializable, Observer {
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
    @FXML
    public ChoiceBox<String> languageChoice;
    private final LanguageRequest languageRequest = new LanguageRequest();
    private Language language;


    private final ProductRequest productRequest = new ProductRequest();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.addObserver(this);
        Controller.initLanguageCheckBox(languageChoice);
        try {
            populateTableProducts(productRequest.getAllProducts());
        } catch (URISyntaxException | IOException | InterruptedException | RuntimeException e) {
            throw new RuntimeException(e);
        }
        try {
            language = languageRequest.getLanguage("English");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        languageChoice.showingProperty().addListener((obs, wasShowing, isNowShowing) -> {
            try {
                language = languageRequest.getLanguage(languageChoice.getValue());
                setChanged();
                this.notifyObservers(language);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        filterButton.setOnAction(e -> {
            try {
                float price = 0;
                if(!priceFilter.getText().isEmpty()) {
                    price = Float.parseFloat(priceFilter.getText());
                }
                populateTableProducts(productRequest.filterProducts(nameFilter.getText(), brandFilter.getText(), availabilityFilter.isSelected(), price));
            } catch (URISyntaxException | IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        saveCSV.setOnAction(e -> {

        });
        saveJSON.setOnAction(e -> {
            ProductRequest productRequest = new ProductRequest();
            try {
                productRequest.saveProducts(productItems, "products.json", "json");
            } catch (IOException | InterruptedException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });
        saveXML.setOnAction(e -> {

        });
        saveTXT.setOnAction(e -> {

        });

    }

    private void populateTableProducts(List<Product> products) throws URISyntaxException, IOException, InterruptedException {
        productItems.clear();
        productTableView.getItems().clear();
        nameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
        brandColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getBrand()));
        List<Product> availableProducts = productRequest.getProductsAvailableInTheChain();
        availabilityColumn.setCellValueFactory(cellData -> new ReadOnlyBooleanWrapper(availableProducts.stream().anyMatch(p -> p.getId() == cellData.getValue().getId())));
        priceColumn.setCellValueFactory(cellData -> new ReadOnlyDoubleWrapper(cellData.getValue().getPrice()));
        productItems.addAll(products);
        productTableView.setItems(productItems);
    }

    public void setNameColumn(String nameColumn) {
        this.nameColumn.setText(nameColumn);
    }

    public void setBrandColumn(String brandColumn) {
        this.brandColumn.setText(brandColumn);
    }

    public void setAvailabilityColumn(String availabilityColumn) {
        this.availabilityColumn.setText(availabilityColumn);
    }

    public void setPriceColumn(String priceColumn) {
        this.priceColumn.setText(priceColumn);
    }

    public void setFilterButton(String filterButton) {
        this.filterButton.setText(filterButton);
    }

    public void setSortNameButton(String sortNameButton) {
        this.sortNameButton.setText(sortNameButton);
    }

    public void setSortPriceButton(String sortPriceButton) {
        this.sortPriceButton.setText(sortPriceButton);
    }

    public void setSaveCSV(String saveCSV) {
        this.saveCSV.setText(saveCSV);
    }

    public void setSaveJSON(String saveJSON) {
        this.saveJSON.setText(saveJSON);
    }

    public void setSaveXML(String saveXML) {
        this.saveXML.setText(saveXML);
    }

    public void setSaveTXT(String saveTXT) {
        this.saveTXT.setText(saveTXT);
    }

    public void setBrandAnalysisButton(String brandAnalysisButton) {
        this.brandAnalysisButton.setText(brandAnalysisButton);
    }

    public void setPriceAnalysisButton(String priceAnalysisButton) {
        this.priceAnalysisButton.setText(priceAnalysisButton);
    }

    @Override
    public void update(Observable o, Object arg) {
        setFilterButton(((Language) arg).getFilterButton());
        setSortNameButton(((Language) arg).getSortNameButton());
        setSortPriceButton(((Language) arg).getSortPriceButton());
        setBrandAnalysisButton(((Language) arg).getBrandAnalysisButton());
        setPriceAnalysisButton(((Language) arg).getPriceAnalysisButton());

        setAvailabilityColumn(((Language) arg).getAvailabilityColumn());
        setPriceColumn(((Language) arg).getPriceColumn());
        setBrandColumn(((Language) arg).getBrandColumn());
        setNameColumn(((Language) arg).getNameColumn());

        setSaveCSV(((Language) arg).getSaveCSV());
        setSaveJSON(((Language) arg).getSaveJSON());
        setSaveTXT(((Language) arg).getSaveTXT());
        setSaveXML(((Language) arg).getSaveXML());
    }
}
