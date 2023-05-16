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

public class PriceChartController implements Initializable {
    @FXML
    private PieChart pricePieChart;
    private final List<Product> products;

    public PriceChartController(List<Product> products) {
        this.products = products;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pricePieChart.setTitle("Price Chart");
        ObservableList<PieChart.Data> barChartData = FXCollections.observableArrayList();
        List<String> prices = new ArrayList<>() {
            {
                add("100 - 200");
                add("200 - 300");
                add("300 - 400");
                add("400 - 500");
                add("500 - 600");
            }
        };
        int i=100;
        for (String price : prices){
            PieChart.Data data = new PieChart.Data(price, getNumberOfProducts(i, i+100));
            i+=100;
            barChartData.add(data);
        }
        pricePieChart.setData(barChartData);
    }

    private int getNumberOfProducts(int priceLow, int priceHigh) {
        return (int) products.stream().filter(p -> p.getPrice() >= priceLow && p.getPrice() < priceHigh).count();
    }
}
