package com.example.perfumeshop.controller;

import com.example.perfumeshop.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BrandChartController implements Initializable {
    @FXML
    private PieChart brandPieChart;
    private final List<Product> products;

    public BrandChartController(List<Product> products) {
        this.products = products;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        brandPieChart.setTitle("Brands Availability");
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        ArrayList<String> brands = new ArrayList<>() {
            {
                add("Valentino");
                add("Versace");
                add("Prada");
                add("Dolce Gabana");
                add("Dior");
            }
        };
        for (String brand : brands){
            PieChart.Data data = new PieChart.Data(brand, getNumberOfProducts(brand));
            pieChartData.add(data);
        }
        brandPieChart.setData(pieChartData);
    }

    private double getNumberOfProducts(String brand) {
        return products.stream().filter(p -> p.getBrand().equals(brand)).toList().size();
    }
}
