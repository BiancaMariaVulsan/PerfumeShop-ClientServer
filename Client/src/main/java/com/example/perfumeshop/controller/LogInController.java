package com.example.perfumeshop.controller;

import com.example.perfumeshop.requests.LoginRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
                loginRequest.login(usernameTextField.getText(), passwordTextField.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
