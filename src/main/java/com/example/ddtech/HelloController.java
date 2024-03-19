package com.example.ddtech;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

public class HelloController {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane scrollAnchorPane;
    @FXML
    private MediaView mediaView;
    @FXML
    private Button nextvideo;
    private int videonumber = 1;
    private MediaPlayer mediaPlayer;
    @FXML
    private Button categories;

    @FXML
    private AnchorPane categoriesMenu;
    @FXML
    private Label videoTitle;
    @FXML
    private Button videoBuy;
    @FXML
    private Label videoMoreInformation;
    @FXML
    private Slider volumeControl;
    @FXML
    private Button pauseVideo;
    @FXML
    private ImageView imagePause;
    @FXML
    private ImageView imagePlay;
    @FXML
    private Button storage;
    @FXML
    private Button power;
    @FXML
    private Button motherboards;
    @FXML
    private Button cases;
    @FXML
    private Button cpus;
    @FXML
    private Button graphics;
    @FXML
    private Button monitors;
    @FXML
    private Button cooling;
    @FXML
    private Button memory;
    @FXML
    private Button laptops;
    @FXML
    private Button cellphones;
    @FXML
    private Button cart;



    private Parent root;
    private Stage stage;
    private Scene scene;
    private Tienda tienda;

    public void initialize() {


        reproduceVideo("/videos/videof1.mp4");
        reproduceVideo("/videos/videof2.mp4");
        reproduceVideo("/videos/videof1.mp4");

        tienda = new Tienda();
        categories.setOnMouseEntered(event -> {
            mediaView.setEffect(new GaussianBlur(30.0));
            categoriesMenu.setVisible(true);
        });
        categoriesMenu.setOnMouseExited(event -> {
            categoriesMenu.setVisible(false);
            mediaView.setEffect(null);
        });
        mediaView.setOnMouseEntered(event -> {
            setInformationVisible(true);
        });
        volumeControl.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume((newValue.doubleValue())/100);
            }
        });
        pauseVideo.setOnMouseEntered(event -> {
            imagePause.setScaleX(1.2);
            imagePause.setScaleY(1.2);
        });
        pauseVideo.setOnMouseExited(event -> {
            imagePause.setScaleX(0.9);
            imagePause.setScaleY(0.9);
        });
        pauseVideo.setOnAction(event -> {
            if(imagePause.isVisible()){
                imagePause.setVisible(false);
                imagePlay.setVisible(true);
                mediaPlayer.pause();
            }else {
                imagePause.setVisible(true);
                imagePlay.setVisible(false);
                mediaPlayer.play();
            }
        });
        scrollPane.vvalueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue()>0.6){
                imagePause.setVisible(false);
                imagePlay.setVisible(true);
                mediaPlayer.pause();
            } else if (imagePlay.isVisible() && newValue.doubleValue()<0.15) {
                imagePause.setVisible(true);
                imagePlay.setVisible(false);
                mediaPlayer.play();
            }
        });



        tienda.reusableAlert(tienda.getArticlesList().getFirst(), scrollAnchorPane, 250.0, 1300.0);
        tienda.reusableAlert(tienda.getArticlesList().get(1), scrollAnchorPane, 750.0, 1300.0);
        tienda.reusableAlert(tienda.getArticlesList().get(2), scrollAnchorPane, 1250.0, 1300.0);
        tienda.reusableAlert(tienda.getArticlesList().get(3), scrollAnchorPane, 250.0, 1800.0);
        tienda.reusableAlert(tienda.getArticlesList().get(4), scrollAnchorPane, 750.0, 1800.0);
        tienda.reusableAlert(tienda.getArticlesList().get(5), scrollAnchorPane, 1250.0, 1800.0);

    }

    public void setInformationVisible(Boolean value){
        videoTitle.setVisible(value);
        videoBuy.setVisible(value);
        videoMoreInformation.setVisible(value);
    }
    public void reproduceVideo(String url) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        String path = getClass().getResource(url).toExternalForm();
        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnReady(() -> {
            mediaPlayer.play();
        });
        mediaView.setPreserveRatio(false);

    }
    public void nextVideo() {
        imagePause.setVisible(true);
        imagePlay.setVisible(false);
        if (videonumber == 1) {
            reproduceVideo("/videos/videof2.mp4");
            videoTitle.setText("¡New Razer Blade!");
            videonumber = 2;
        } else {
            videoTitle.setText("¡New Legion Series!");
            videonumber = 1;
            reproduceVideo("/videos/videof1.mp4");
        }
        System.out.println(videonumber);
    }
    public void categories(){
        if (!categoriesMenu.isVisible()){
            categoriesMenu.setVisible(true);
            mediaView.setEffect(new GaussianBlur(30.0));
        }else {
            categoriesMenu.setVisible(false);
            mediaView.setEffect(null);
        }
    }

    @FXML
    public void categories(ActionEvent event) throws IOException {
        Platform.runLater(() -> {
            Robot robot = new Robot();
            robot.mouseMove(960, 540);
        });
        categoriesMenu.setVisible(false);
        if(imagePause.isVisible()) {
            imagePause.setVisible(false);
            imagePlay.setVisible(true);
            mediaPlayer.pause();
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("categories.fxml"));
        root = loader.load();
        CategoriesController categoriesController = loader.getController();
        categoriesController.loadArticles((this.tienda.getArticlesList()).size(), 0, this.tienda.getArticlesList());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void categories(ActionEvent event, String category) throws IOException {
        Platform.runLater(() -> {
            Robot robot = new Robot();
            robot.mouseMove(960, 540);
        });
        categoriesMenu.setVisible(false);
        if(imagePause.isVisible()) {
            imagePause.setVisible(false);
            imagePlay.setVisible(true);
            mediaPlayer.pause();
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("categories.fxml"));
        root = loader.load();
        CategoriesController categoriesController = loader.getController();
        categoriesController.loadArticles((this.tienda.selectCategory(category).size()), 0, this.tienda.selectCategory(category));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void storage(ActionEvent event) throws IOException {
        categories(event, "Almacenamiento");
    }
    public void power(ActionEvent event) throws IOException {
        categories(event, "Fuente de Poder");
    }
    public void motherboards(ActionEvent event) throws IOException {
        categories(event, "Placas Madre");
    }public void cases(ActionEvent event) throws IOException {
        categories(event, "Gabinete");
    }public void cpus(ActionEvent event) throws IOException {
        categories(event, "Procesador");
    }public void graphic(ActionEvent event) throws IOException {
        categories(event, "Tarjeta Grafica");
    }
    public void monitors(ActionEvent event) throws IOException {
        categories(event, "Monitor");
    }public void cooling(ActionEvent event) throws IOException {
        categories(event, "Enfriador");
    }public void memory(ActionEvent event) throws IOException {
        categories(event, "Ram");
    }public void laptops(ActionEvent event) throws IOException {
        categories(event, "Laptop");
    }
    public void cellphones(ActionEvent event) throws IOException {
        categories(event, "Celular");
    }

    public void cart(ActionEvent event) throws IOException {
        Platform.runLater(() -> {
            Robot robot = new Robot();
            robot.mouseMove(960, 540);
        });
        categoriesMenu.setVisible(false);
        if(imagePause.isVisible()) {
            imagePause.setVisible(false);
            imagePlay.setVisible(true);
            mediaPlayer.pause();
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
        root = loader.load();
        CartController categoriesController = loader.getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}