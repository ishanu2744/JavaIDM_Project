module com.javaidm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.javaidm to javafx.fxml;
    exports com.javaidm;
}