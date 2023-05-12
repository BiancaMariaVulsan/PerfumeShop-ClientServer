package com.example.perfumeshop.controller;

import com.example.perfumeshop.model.Language;
import com.example.perfumeshop.requests.LanguageRequest;
import com.example.perfumeshop.requests.LoginRequest;
import com.example.perfumeshop.requests.PersonRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

public class LogInController extends Observable implements Initializable {
    @FXML
    public Label usernameLabel;
    @FXML
    public Label passwordLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button signInButton;
    @FXML
    private ChoiceBox<String> languageChoice;

    private Language language;
    private final PersonRequest personRequest = new PersonRequest();
    private final LanguageRequest languageRequest = new LanguageRequest();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initLanguageCheckBox();

        languageChoice.showingProperty().addListener((obs, wasShowing, isNowShowing) -> {
            try {
                language = languageRequest.getLanguage(languageChoice.getValue());
                setPasswordLabel(language.getPassword());
                setUsernameLabel(language.getUsername());
                setChanged();
                this.notifyObservers(language);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        signInButton.setOnAction(actionEvent -> {
            LoginRequest loginRequest = new LoginRequest();
            try {
                String role = loginRequest.login(usernameTextField.getText(), passwordTextField.getText());
                if(role.equals("ADMIN")) {
                    AdminController adminController = new AdminController();
                    this.addObserver(adminController);
                    Callback<Class<?>, Object> controllerFactory = type -> {
                        if (type == AdminController.class) {
                            return adminController;
                        } else {
                            try {
                                return type.newInstance();
                            } catch (Exception exc) {
                                Controller.initAlarmBox("Error", "Could not load manager controller...", Alert.AlertType.ERROR);
                                throw new RuntimeException(exc.getMessage());
                            }
                        }
                    };
                    Controller.loadFXML("/com/example/perfumeshop/admin-view.fxml", controllerFactory);
                } else if(role.equals("EMPLOYEE")) {
                    EmployeeController employeeController = new EmployeeController(getShopId(usernameTextField.getText()));
                    this.addObserver(employeeController);
                    Callback<Class<?>, Object> controllerFactory = type -> {
                        if (type == EmployeeController.class) {
                            return employeeController;
                        } else {
                            try {
                                return type.newInstance();
                            } catch (Exception exc) {
                                Controller.initAlarmBox("Error", "Could not load manager controller...", Alert.AlertType.ERROR);
                                throw new RuntimeException(exc.getMessage());
                            }
                        }
                    };
                    Controller.loadFXML("/com/example/perfumeshop/employee-view.fxml", controllerFactory);
                } else if(role.equals("MANAGER")) {
                    ManagerController managerController = new ManagerController();
                    this.addObserver(managerController);
                    Callback<Class<?>, Object> controllerFactory = type -> {
                        if (type == ManagerController.class) {
                            return managerController;
                        } else {
                            try {
                                return type.newInstance();
                            } catch (Exception exc) {
                                Controller.initAlarmBox("Error", "Could not load manager controller...", Alert.AlertType.ERROR);
                                throw new RuntimeException(exc.getMessage());
                            }
                        }
                    };
                    Controller.loadFXML("/com/example/perfumeshop/manager-view.fxml", controllerFactory);
                } else {
                    Controller.initAlarmBox("Error", "The password is not correct, please try again!", Alert.AlertType.ERROR);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private int getShopId(String username) {
        try {
            return personRequest.getEmployeeShop(username);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void initLanguageCheckBox() {
        List<String> languages = new ArrayList<>() {
            {
                add("English");
                add("Romanian");
                add("German");
            }
        };
        for(String language: languages) {
            languageChoice.getItems().add(language);
        }
        languageChoice.setValue(languages.get(0));
    }

    public void setUsernameLabel(String usernameLabel) {
        this.usernameLabel.setText(usernameLabel);
    }

    public void setPasswordLabel(String passwordLabel) {
        this.passwordLabel.setText(passwordLabel);
    }
}
