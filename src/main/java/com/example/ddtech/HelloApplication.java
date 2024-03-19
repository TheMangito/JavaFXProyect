package com.example.ddtech;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.setProperty("prism.lcdtext", "false");
        Font font1 = Font.loadFont(getClass().getResourceAsStream("/videos/SF-Pro-Text-Regular.otf"), 15);
        Font font2 = Font.loadFont(getClass().getResourceAsStream("/videos/SF-Pro-Text-Bold.otf"), 15);
        Font font3 = Font.loadFont(getClass().getResourceAsStream("/videos/SF-Pro-Text-Medium.otf"), 15);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Pane root = fxmlLoader.load();
        Scene scene = new Scene(root, 1920, 1080);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}