module com.example.teamproject1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.teamproject1 to javafx.fxml;

    exports com.example.teamproject1;
}