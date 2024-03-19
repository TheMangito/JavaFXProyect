module com.example.ddtech {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires javafx.media;
    requires resend.java;
    requires org.apache.commons.io;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens com.example.ddtech to javafx.fxml, com.fasterxml.jackson.databind;
    exports com.example.ddtech;
}