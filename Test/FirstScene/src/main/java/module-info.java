module com.example.firstscene {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens com.example.firstscene to javafx.fxml;
    exports com.example.firstscene;
}