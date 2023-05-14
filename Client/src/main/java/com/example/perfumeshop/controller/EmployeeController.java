package com.example.perfumeshop.controller;

import com.example.perfumeshop.model.Language;
import com.example.perfumeshop.model.ShopProduct;
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
import javafx.util.Callback;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeController extends Observable implements Initializable, Observer {
    @FXML
    private TableView<ShopProduct> productTableView;
    private final ObservableList<ShopProduct> productItems = FXCollections.observableArrayList();
    @FXML
    private TableColumn<ShopProduct, String> nameColumn;
    @FXML
    private TableColumn<ShopProduct ,String> brandColumn;
    @FXML
    private TableColumn<ShopProduct, Boolean> availabilityColumn;
    @FXML
    private TableColumn<ShopProduct, Number> priceColumn;

    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button filterButton;

    @FXML
    private TextField brandFilter;
    @FXML
    private CheckBox availabilityFilter;
    @FXML
    private TextField priceFilter;
    @FXML
    public TextField nameFilter;

    @FXML
    private Button saveCSV;
    @FXML
    private Button saveJSON;
    @FXML
    private Button saveXML;
    @FXML
    private Button saveTXT;

    @FXML
    public Button sortByNameButton;
    @FXML
    public Button sortByPriceButton;
    @FXML
    public ChoiceBox<String> languageChoice;

    private final int idShop;
    private Language language;
    private final ProductRequest productRequest = new ProductRequest();
    private final LanguageRequest languageRequest = new LanguageRequest();

    public EmployeeController(int idShop) {
        this.idShop = idShop;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.addObserver(this);
        Controller.initLanguageCheckBox(languageChoice);
        try {
            populateTableProducts();
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
        sortByNameButton.setOnAction(e -> {
            productItems.setAll(productItems.stream()
                    .sorted(Comparator.comparing(sp -> sp.getProduct().getName()))
                    .collect(Collectors.toList()));

        });
        sortByPriceButton.setOnAction(e -> {
            productItems.setAll(productItems.stream()
                    .sorted(Comparator.comparing(sp -> sp.getProduct().getPrice()))
                    .collect(Collectors.toList()));
        });
        addButton.setOnAction(e -> {
            AddProductController addProductController = new AddProductController(idShop, productItems);
            this.addObserver(addProductController);
            Callback<Class<?>, Object> controllerFactory = type -> {
                if (type == AddProductController.class) {
                    return addProductController;
                } else {
                    try {
                        return type.newInstance();
                    } catch (Exception exc) {
                        System.err.println("Could not load register controller " + type.getName());
                        throw new RuntimeException(exc);
                    }
                }
            };
            Controller.loadFXML("/com/example/perfumeshop/add-product-view.fxml", controllerFactory);
        });
    }

    private void populateTableProducts() throws URISyntaxException, IOException, InterruptedException {
        productItems.clear();
        productTableView.getItems().clear();
        nameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getProduct().getName()));
        brandColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getProduct().getBrand()));
        availabilityColumn.setCellValueFactory(cellData -> new ReadOnlyBooleanWrapper(cellData.getValue().getStock() > 0));
        priceColumn.setCellValueFactory(cellData -> new ReadOnlyDoubleWrapper(cellData.getValue().getProduct().getPrice()));
        productItems.addAll(productRequest.getShopProducts(idShop));
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

    public void setAddButton(String addButton) {
        this.addButton.setText(addButton);
    }

    public void setDeleteButton(String deleteButton) {
        this.deleteButton.setText(deleteButton);
    }

    public void setEditButton(String editButton) {
        this.editButton.setText(editButton);
    }

    public void setFilterButton(String filterButton) {
        this.filterButton.setText(filterButton);
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

    public void setSortByNameButton(String sortByNameButton) {
        this.sortByNameButton.setText(sortByNameButton);
    }

    public void setSortByPriceButton(String sortByPriceButton) {
        this.sortByPriceButton.setText(sortByPriceButton);
    }

    @Override
    public void update(Observable o, Object arg) {
        setAddButton(((Language) arg).getAddButton());
        setDeleteButton(((Language) arg).getDeleteButton());
        setEditButton(((Language) arg).getEditButton());
        setFilterButton(((Language) arg).getFilterButton());
        setSortByPriceButton(((Language) arg).getSortPriceButton());
        setSortByNameButton(((Language) arg).getSortNameButton());
        setAvailabilityColumn(((Language) arg).getAvailabilityColumn());
        setBrandColumn(((Language) arg).getBrandColumn());
        setNameColumn(((Language) arg).getNameColumn());
        setPriceColumn(((Language) arg).getPriceColumn());
        setSaveCSV(((Language) arg).getSaveCSV());
        setSaveJSON(((Language) arg).getSaveJSON());
        setSaveTXT(((Language) arg).getSaveTXT());
        setSaveXML(((Language) arg).getSaveXML());
    }
}
