package com.example.perfumeshop.controller;

import com.example.perfumeshop.requests.LoginRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML
    private Button signInButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signInButton.setOnAction(actionEvent -> {
            LoginRequest loginRequest = new LoginRequest();
            try {
                loginRequest.login();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
