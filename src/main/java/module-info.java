module com.example.spotifyfromwish {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires javafx.media;

    opens com.example.spotifyfromwish to javafx.fxml;
    exports com.example.spotifyfromwish;
    exports controllers;
    opens controllers to javafx.fxml;
}