package com.example.perfumeshop.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    public static void loadFXML(String fxmlFile, Callback<Class<?>, Object> controllerFactory) {
        Stage programStage = new Stage();
        Parent programRoot;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource(fxmlFile));
            fxmlLoader.setControllerFactory(controllerFactory);
            var path = Controller.class.getResource(fxmlFile);
            fxmlLoader.setLocation(path);
            programRoot = fxmlLoader.load();
            Scene programScene = new Scene(programRoot);
            String css = Controller.class.getResource("/com/example/perfumeshop/start.css").toExternalForm();
            programScene.getStylesheets().add(css);
            programStage.setTitle("Running Program");
            programStage.setScene(programScene);
            programStage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    public static Optional<ButtonType> initAlarmBox(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        confirm.setDefaultButton(false);
        confirm.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        return alert.showAndWait();
    }
}
