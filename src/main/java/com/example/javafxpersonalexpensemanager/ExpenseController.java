package com.example.javafxpersonalexpensemanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExpenseController {

    @FXML private TextField titleField;
    @FXML private TextField amountField;
    @FXML private ComboBox<String> categoryComboBox;
    @FXML private DatePicker datePicker;
    @FXML private Label totalLabel;

    @FXML private TableView<Expense> expenseTable;
    @FXML private TableColumn<Expense, String> titleColumn;
    @FXML private TableColumn<Expense, Double> amountColumn;
    @FXML private TableColumn<Expense, String> categoryColumn;
    @FXML private TableColumn<Expense, String> dateColumn;

    private final ObservableList<Expense> expenseList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Populate category dropdown options
        categoryComboBox.getItems().addAll("Food", "Transport", "Utilities", "Entertainment", "Other");
        categoryComboBox.setValue("Food");

        // Set default date to today
        datePicker.setValue(LocalDate.now());

        // Link Table columns using PropertyValueFactory (automatically matches getters like titleProperty())
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Bind table data source
        expenseTable.setItems(expenseList);
    }

    @FXML
    private void handleAddExpense() {
        String title = titleField.getText().trim();
        String amountText = amountField.getText().trim();
        String category = categoryComboBox.getValue();
        LocalDate date = datePicker.getValue();

        // Basic Validation
        if (title.isEmpty() || amountText.isEmpty() || date == null) {
            showAlert("Validation Error", "Please fill in all fields.");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                showAlert("Invalid Amount", "Amount must be greater than zero.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Amount", "Please enter a valid numeric amount.");
            return;
        }

        // Add new expense to the observable list
        Expense newExpense = new Expense(title, amount, category, date.toString());
        expenseList.add(newExpense);

        // Update total and clear input fields
        updateTotal();
        clearFields();
    }

    private void updateTotal() {
        double sum = expenseList.stream().mapToDouble(Expense::getAmount).sum();
        totalLabel.setText(String.format("$%.2f", sum));
    }

    private void clearFields() {
        titleField.clear();
        amountField.clear();
        datePicker.setValue(LocalDate.now());
        categoryComboBox.setValue("Food");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}