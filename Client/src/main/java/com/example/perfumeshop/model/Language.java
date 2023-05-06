package com.example.perfumeshop.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Language {
    // admin
    @JsonProperty("firstNameColumn")
    private String firstNameColumn;

    @JsonProperty("lastNameColumn")
    private String lastNameColumn;

    @JsonProperty("roleColumn")
    private String roleColumn;

    @JsonProperty("addButton")
    private String addButton;

    @JsonProperty("deleteButton")
    private String deleteButton;

    @JsonProperty("editButton")
    private String editButton;

    @JsonProperty("filterButton")
    private String filterButton;

    @JsonProperty("roleChoice")
    private List<String> roleChoice;

    @JsonProperty("availabilityColumn")
    private String availabilityColumn;

    @JsonProperty("nameColumn")
    private String nameColumn;

    @JsonProperty("brandColumn")
    private String brandColumn;

    @JsonProperty("priceColumn")
    private String priceColumn;

    @JsonProperty("saveCSV")
    private String saveCSV;

    @JsonProperty("saveJSON")
    private String saveJSON;

    @JsonProperty("saveXML")
    private String saveXML;

    @JsonProperty("saveTXT")
    private String saveTXT;

    @JsonProperty("sortNameButton")
    private String sortNameButton;

    @JsonProperty("sortPriceButton")
    private String sortPriceButton;

    @JsonProperty("brandAnalysisButton")
    private String brandAnalysisButton;

    @JsonProperty("priceAnalysisButton")
    public String priceAnalysisButton;

    @JsonProperty("username")
    public String username;

    @JsonProperty("password")
    public String password;

    @JsonProperty("role")
    public String role;

    @JsonProperty("shop")
    public String shop;

    @JsonProperty("product")
    public String product;

    @JsonProperty("save")
    public String save;

    public Language(String firstNameColumn, String lastNameColumn, String roleColumn,
                    String addButton, String deleteButton, String editButton,
                    String filterButton, List<String> roleChoice, String availabilityColumn,
                    String sortNameButton, String sortPriceButton, String brandAnalysisButton, String priceAnalysisButton) {
        this.firstNameColumn = firstNameColumn;
        this.lastNameColumn = lastNameColumn;
        this.roleColumn = roleColumn;
        this.addButton = addButton;
        this.deleteButton = deleteButton;
        this.editButton = editButton;
        this.filterButton = filterButton;
        this.roleChoice = roleChoice;
        this.availabilityColumn = availabilityColumn;
        this.sortNameButton = sortNameButton;
        this.sortPriceButton = sortPriceButton;
        this.brandAnalysisButton = brandAnalysisButton;
        this.priceAnalysisButton = priceAnalysisButton;
    }

    public Language() {
    }

    public String getFirstNameColumn() {
        return firstNameColumn;
    }

    public void setFirstNameColumn(String firstNameColumn) {
        this.firstNameColumn = firstNameColumn;
    }

    public String getLastNameColumn() {
        return lastNameColumn;
    }

    public void setLastNameColumn(String lastNameColumn) {
        this.lastNameColumn = lastNameColumn;
    }

    public String getRoleColumn() {
        return roleColumn;
    }

    public void setRoleColumn(String roleColumn) {
        this.roleColumn = roleColumn;
    }

    public String getAddButton() {
        return addButton;
    }

    public void setAddButton(String addButton) {
        this.addButton = addButton;
    }

    public String getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(String deleteButton) {
        this.deleteButton = deleteButton;
    }

    public String getEditButton() {
        return editButton;
    }

    public void setEditButton(String editButton) {
        this.editButton = editButton;
    }

    public String getFilterButton() {
        return filterButton;
    }

    public void setFilterButton(String filterButton) {
        this.filterButton = filterButton;
    }

    public List<String> getRoleChoice() {
        return roleChoice;
    }

    public void setRoleChoice(List<String> roleChoice) {
        this.roleChoice = roleChoice;
    }

    public String getAvailabilityColumn() {
        return availabilityColumn;
    }

    public void setAvailabilityColumn(String availabilityColumn) {
        this.availabilityColumn = availabilityColumn;
    }

    public String getNameColumn() {
        return nameColumn;
    }

    public void setNameColumn(String nameColumn) {
        this.nameColumn = nameColumn;
    }

    public String getBrandColumn() {
        return brandColumn;
    }

    public void setBrandColumn(String brandColumn) {
        this.brandColumn = brandColumn;
    }

    public String getPriceColumn() {
        return priceColumn;
    }

    public void setPriceColumn(String priceColumn) {
        this.priceColumn = priceColumn;
    }

    public String getSaveCSV() {
        return saveCSV;
    }

    public void setSaveCSV(String saveCSV) {
        this.saveCSV = saveCSV;
    }

    public String getSaveJSON() {
        return saveJSON;
    }

    public void setSaveJSON(String saveJSON) {
        this.saveJSON = saveJSON;
    }

    public String getSaveXML() {
        return saveXML;
    }

    public void setSaveXML(String saveXML) {
        this.saveXML = saveXML;
    }

    public String getSaveTXT() {
        return saveTXT;
    }

    public void setSaveTXT(String saveTXT) {
        this.saveTXT = saveTXT;
    }
    public String getSortNameButton() {
        return sortNameButton;
    }

    public void setSortNameButton(String sortNameButton) {
        this.sortNameButton = sortNameButton;
    }

    public String getSortPriceButton() {
        return sortPriceButton;
    }

    public void setSortPriceButton(String sortPriceButton) {
        this.sortPriceButton = sortPriceButton;
    }

    public String getBrandAnalysisButton() {
        return this.brandAnalysisButton;
    }
    public void setBrandAnalysisButton(String brandAnalysisButton) {
        this.brandAnalysisButton = brandAnalysisButton;
    }

    public String getPriceAnalysisButton() {
        return priceAnalysisButton;
    }

    public void setPriceAnalysisButton(String priceAnalysisButton) {
        this.priceAnalysisButton = priceAnalysisButton;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSave() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }
}
