package com.example.javafxpersonalexpensemanager;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Expense {
    private final StringProperty title;
    private final DoubleProperty amount;
    private final StringProperty category;
    private final StringProperty date;

    public Expense(String title, double amount, String category, String date) {
        this.title = new SimpleStringProperty(title);
        this.amount = new SimpleDoubleProperty(amount);
        this.category = new SimpleStringProperty(category);
        this.date = new SimpleStringProperty(date);
    }

    public String getTitle() { return title.get(); }
    public StringProperty titleProperty() { return title; }

    public double getAmount() { return amount.get(); }
    public DoubleProperty amountProperty() { return amount; }

    public String getCategory() { return category.get(); }
    public StringProperty categoryProperty() { return category; }

    public String getDate() { return date.get(); }
    public StringProperty dateProperty() { return date; }
}