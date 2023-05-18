package com.example.perfumeshop.controller;

import com.example.perfumeshop.model.*;
import com.example.perfumeshop.requests.LanguageRequest;
import com.example.perfumeshop.requests.PersonRequest;
import com.example.perfumeshop.requests.ShopRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class RegisterController implements Initializable, Observer {
    private final ObservableList<Person> personItems;
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
    private ChoiceBox<String> shopChoiceBox;

    private Person personToUpdate;
    private final boolean isEditing;

    public RegisterController(ObservableList<Person> personItems, Person personToUpdate) {
        this.personItems = personItems;
        isEditing = true;
        this.personToUpdate = personToUpdate;
    }

    public RegisterController(ObservableList<Person> personItems) {
        this.personItems = personItems;
        isEditing = false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(roleChoiceBox.getItems().size() == 0)
        {
            LanguageRequest languageRequest = new LanguageRequest();
            try {
                Language language = language = languageRequest.getLanguage("English");
                setRoleChoiceBox(language.getRoleChoice());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        registerButton.setDisable(true);
        try {
            initShopCheckBox();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(this.isEditing)
        {
            firstNameTextField.setText(personToUpdate.getFirstName());
            lastNameTextField.setText(personToUpdate.getLastName());
            usernameTextField.setText(personToUpdate.getUsername());
            passwordTextField.setText(personToUpdate.getPassword());
            roleChoiceBox.setValue(personToUpdate.getRole().name());
            enableShopChoiceBox(personToUpdate.getRole().name());
        }

        registerButton.setOnAction(actionEvent -> {
            try {
                String role = roleChoiceBox.getValue();
                PersonRequest personRequest = new PersonRequest();
                boolean empl = role.equals("EMPLOYEE") || role.equals("ANGAJAT") || role.equals("MITERBEITER");
                if(!isEditing) {
                    if(empl) {
                        Employee employee = new Employee(firstNameTextField.getText(), lastNameTextField.getText(), usernameTextField.getText(), passwordTextField.getText(), getShopIdByName());
                        Controller.initAlarmBox("Success", personRequest.addPerson(employee), Alert.AlertType.INFORMATION);
                        personItems.setAll(personRequest.getPersons());
                    } else {
                        Person person = new Person(firstNameTextField.getText(), lastNameTextField.getText(), Role.valueOf(roleChoiceBox.getValue()), usernameTextField.getText(), passwordTextField.getText());
                        Controller.initAlarmBox("Success", personRequest.addPerson(person), Alert.AlertType.INFORMATION);
                        personItems.setAll(personRequest.getPersons());
                    }
                } else {
                    if(empl) {
                        Employee employee = new Employee(personToUpdate.getId(), firstNameTextField.getText(), lastNameTextField.getText(), usernameTextField.getText(), passwordTextField.getText(), getShopIdByName());
                        Controller.initAlarmBox("Success", personRequest.updatePerson(employee), Alert.AlertType.INFORMATION);
                        personItems.setAll(personRequest.getPersons());
                    } else {
                        Person person = new Person(personToUpdate.getId(), firstNameTextField.getText(), lastNameTextField.getText(), Role.valueOf(roleChoiceBox.getValue()), usernameTextField.getText(), passwordTextField.getText());
                        Controller.initAlarmBox("Success", personRequest.updatePerson(person), Alert.AlertType.INFORMATION);
                        personItems.setAll(personRequest.getPersons());
                    }
                }
            } catch (URISyntaxException | IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        exitButton.setOnAction(actionEvent -> {
            Optional<ButtonType> result = Controller.initAlarmBox("Exit", "Are you sure you want to exit? All progress will be lost.", Alert.AlertType.CONFIRMATION);
            if(result.get() == ButtonType.OK) {
                Stage stage = (Stage) registerButton.getScene().getWindow();
                stage.close();
            }
        });
        termsCheckBox.setOnAction(actionEvent -> {
            if (termsCheckBox.isSelected())
            {
                registerButton.setDisable(false);
            }
            else {
                registerButton.setDisable(true);
            }
        });
        roleChoiceBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            enableShopChoiceBox(newValue);
        });
    }

    private void initShopCheckBox() throws URISyntaxException, IOException, InterruptedException {
        ShopRequest shopRequest = new ShopRequest();
        List<Shop> shops = shopRequest.getShops();
        for(Shop shop: shops) {
            shopChoiceBox.getItems().add(shop.getName());
        }
        shopChoiceBox.setValue(shops.get(0).getName()); // suppose there is at least one shop
    }

    private void enableShopChoiceBox(String role) {
        if(role == null) {
            return;
        }
        if(role.equals("EMPLOYEE") || role.equals("ANGAJAT") || role.equals("MITERBEITER")) {
            shopChoiceBox.setDisable(false);
        } else {
            shopChoiceBox.setDisable(true);
        }
    }

    private int getShopIdByName() throws URISyntaxException, IOException, InterruptedException {
        ShopRequest shopRequest = new ShopRequest();
        List<Shop> shops = shopRequest.getShops();
        for(Shop shop: shops) {
            if(shop.getName().equals(shopChoiceBox.getValue())) {
                return shop.getId();
            }
        }
        return -1;
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
