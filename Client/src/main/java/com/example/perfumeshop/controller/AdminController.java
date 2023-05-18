package com.example.perfumeshop.controller;

import com.example.perfumeshop.model.Language;
import com.example.perfumeshop.model.Person;
import com.example.perfumeshop.requests.LanguageRequest;
import com.example.perfumeshop.requests.PersonRequest;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class AdminController extends Observable implements Initializable, Observer {
    @FXML
    private TableView<Person> personTableView;
    private final ObservableList<Person> personItems = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person, String> roleColumn;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button filterButton;
    @FXML
    private ChoiceBox<String> roleChoice;
    @FXML
    public ChoiceBox<String> languageChoice;
    private Language language;
    private final LanguageRequest languageRequest = new LanguageRequest();

    private final PersonRequest personRequest = new PersonRequest();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.addObserver(this);
        Controller.initLanguageCheckBox(languageChoice);
        try {
            populateTablePersons();
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
        addButton.setOnAction(e -> {
            RegisterController registerController = new RegisterController();
            this.addObserver(registerController);
            Callback<Class<?>, Object> controllerFactory = type -> {
                if (type == RegisterController.class) {
                    return registerController;
                } else {
                    try {
                        return type.newInstance();
                    } catch (Exception exc) {
                        System.err.println("Could not load register controller " + type.getName());
                        throw new RuntimeException(exc);
                    }
                }
            };
            Controller.loadFXML("/com/example/perfumeshop/register-view.fxml", controllerFactory);
        });
        filterButton.setOnAction(e -> {
            try {
                String roleMap;
                if(roleChoice.getValue().equals("ANGAJAT") || roleChoice.getValue().equals("MITERBEITER") || roleChoice.getValue().equals("EMPLOYEE")) {
                    roleMap = "EMPLOYEE";
                } else if(roleChoice.getValue().equals("ADMINISTRATOR") || roleChoice.getValue().equals("ADMIN")) {
                    roleMap = "ADMIN";
                } else {
                    roleMap = "MANAGER";
                }
                personItems.setAll(personRequest.filterPersons(roleMap));
            } catch (IOException | InterruptedException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void populateTablePersons() throws IOException, URISyntaxException, InterruptedException {
        personItems.clear();
        personTableView.getItems().clear();
        firstNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLastName()));
        roleColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getRole().toString()));
        personItems.addAll(personRequest.getPersons());
        personTableView.setItems(personItems);
    }

    public void setFirstNameColumn(String firstNameColumn) {
        this.firstNameColumn.setText(firstNameColumn);
    }

    public void setLastNameColumn(String lastNameColumn) {
        this.lastNameColumn.setText(lastNameColumn);
    }

    public void setRoleColumn(String roleColumn) {
        this.roleColumn.setText(roleColumn);
    }

    public void setAddButton(String textButton) {
        this.addButton.setText(textButton);
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

    public void setRoleChoice(List<String> roleChoice) {
        ObservableList<String> items = FXCollections.observableArrayList(roleChoice);
        this.roleChoice.setItems(items);
    }
    @Override
    public void update(Observable o, Object arg) {
        setAddButton(((Language) arg).getAddButton());
        setDeleteButton(((Language) arg).getDeleteButton());
        setEditButton(((Language) arg).getEditButton());
        setFilterButton(((Language) arg).getFilterButton());
        setFirstNameColumn(((Language) arg).getFirstNameColumn());
        setLastNameColumn(((Language) arg).getLastNameColumn());
        setRoleChoice(((Language) arg).getRoleChoice());
    }
}
