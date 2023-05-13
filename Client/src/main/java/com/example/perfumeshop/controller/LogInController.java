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
import java.util.*;

public class LogInController extends Observable implements Initializable, Observer {
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
        try {
            language = languageRequest.getLanguage("English");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        Controller.initLanguageCheckBox(languageChoice);
        this.addObserver(this);

        languageChoice.showingProperty().addListener((obs, wasShowing, isNowShowing) -> {
            try {
                language = languageRequest.getLanguage(languageChoice.getValue());
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
            setChanged();
            this.notifyObservers(language);
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



    public void setUsernameLabel(String usernameLabel) {
        this.usernameLabel.setText(usernameLabel);
    }

    public void setPasswordLabel(String passwordLabel) {
        this.passwordLabel.setText(passwordLabel);
    }

    @Override
    public void update(Observable o, Object arg) {
        setPasswordLabel(language.getPassword());
        setUsernameLabel(language.getUsername());
    }
}
