package com.example.perfumeshop.controller;

import com.example.perfumeshop.requests.LoginRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signInButton.setOnAction(actionEvent -> {
            LoginRequest loginRequest = new LoginRequest();
            try {
                String role = loginRequest.login(usernameTextField.getText(), passwordTextField.getText());
                if(role.equals("ADMIN")) {
                    Callback<Class<?>, Object> controllerFactory = type -> {
                        if (type == ManagerController.class) {
                            return new AdminController();
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
                    Callback<Class<?>, Object> controllerFactory = type -> {
                        if (type == ManagerController.class) {
                            return new EmployeeController();
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
                    Callback<Class<?>, Object> controllerFactory = type -> {
                        if (type == ManagerController.class) {
                            return new ManagerController();
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
}
