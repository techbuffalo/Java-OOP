module hw3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens hw3 to javafx.fxml;
    exports hw3;
}
