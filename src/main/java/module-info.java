module com.example.javafxpersonalexpensemanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxpersonalexpensemanager to javafx.fxml;
    exports com.example.javafxpersonalexpensemanager;
}