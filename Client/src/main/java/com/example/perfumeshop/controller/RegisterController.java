package com.example.perfumeshop.controller;

import com.example.perfumeshop.model.Language;
import com.example.perfumeshop.model.Shop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class RegisterController implements Initializable, Observer {
    @FXML
    public Label firstNameLabel;
    @FXML
    public Label lastNameLabel;
    @FXML
    public Label usrnameLabel;
    @FXML
    public Label passwordLabel;
    @FXML
    public Label shopLabel;
    @FXML
    public Label roleLabel;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private CheckBox termsCheckBox;

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private Button exitButton;
    @FXML
    private Button registerButton;
    @FXML
    private ChoiceBox<String> roleChoiceBox;
    @FXML
    private ChoiceBox<Shop> shopChoiceBox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setFirstNameLabel(String firstNameLabel) {
        this.firstNameLabel.setText(firstNameLabel);
    }

    public void setLastNameLabel(String lastNameLabel) {
        this.lastNameLabel.setText(lastNameLabel);
    }

    public void setUsrnameLabel(String usrnameLabel) {
        this.usrnameLabel.setText(usrnameLabel);
    }

    public void setPasswordLabel(String passwordLabel) {
        this.passwordLabel.setText(passwordLabel);
    }

    public void setRoleChoiceBox(List<String> roleChoiceBox) {
        ObservableList<String> items = FXCollections.observableArrayList(roleChoiceBox);
        this.roleChoiceBox.setItems(items);
    }

    public void setShopLabel(String shopLabel) {
        this.shopLabel.setText(shopLabel);
    }

    public void setRoleLabel(String roleLabel) {
        this.roleLabel.setText(roleLabel);
    }
    @Override
    public void update(Observable o, Object arg) {
        setFirstNameLabel(((Language) arg).getFirstNameColumn());
        setLastNameLabel(((Language) arg).getLastNameColumn());
        setRoleChoiceBox(((Language) arg).getRoleChoice());
        setUsrnameLabel(((Language) arg).getUsername());
        setPasswordLabel(((Language) arg).getPassword());
        setRoleLabel(((Language) arg).getRole());
        setShopLabel(((Language) arg).getShop());
    }
}
