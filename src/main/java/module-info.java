module org.example.cosc190a3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.example.cosc190a3 to javafx.fxml;
    exports org.example.cosc190a3;
    exports org.example.cosc190a3.q2;
}