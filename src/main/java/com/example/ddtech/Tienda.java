package com.example.ddtech;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class Tienda{
    private Parent root;
    private Stage stage;
    private Scene scene;
    private final File articles = new File("src/main/resources/com/example/ddtech/articulosf.json");
    private List<Articulo> articlesList = new ArrayList<>();
    private List<ReusableArticle> articleCreated = new ArrayList<ReusableArticle>();
    public void loadArticles() throws IOException {
        try {
            String json = FileUtils.readFileToString(articles, StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            articlesList = objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Tienda() {
        try {
            loadArticles();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Articulo> selectCategory(String search){
        List<Articulo> articles = new ArrayList<>();
        for (Articulo article: articlesList){
            if(article.buscar(search))
                articles.add(article);
        }
        return articles;
    }
    public void reusableAlert(Articulo article, AnchorPane pane , Double x, Double y){
        ReusableArticle ArticleNode= new ReusableArticle();
        if (article.getImage()!=""){
            ArticleNode.setImage(article.getImage());
        }
        ArticleNode.setTitleText(article.getNombre() + "\n" + article.getDescripcion());
        ArticleNode.setLayoutX(x);
        ArticleNode.setCostLabel(article.getPrecio());
        ArticleNode.setOnActionLearnMore(event -> {
            try {
                viewProduct(event,article);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        ArticleNode.setOnActionBuyButton(event -> {
            ObjectMapper mapper = new ObjectMapper();
            File json = new File("src/main/resources/com/example/ddtech/cart.json");
            List<Articulo> cartArticles = new ArrayList<>();
            try {
                String jsonR = FileUtils.readFileToString(json, StandardCharsets.UTF_8);
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                cartArticles = objectMapper.readValue(json, new TypeReference<>() {
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
                cartArticles.add(article);
                writer.writeValue(json, cartArticles);
            }catch (IOException e) {
                e.printStackTrace();
            }
        });
        if(pane.getPrefHeight()<y+750){
            pane.setPrefHeight(y+750);
        }
        ArticleNode.setLayoutY(y);
        articleCreated.add(ArticleNode);
        pane.getChildren().add(ArticleNode);
    }

    public void clearArticles(AnchorPane pane){
        for (ReusableArticle article: articleCreated){
            pane.getChildren().remove(article);
        }
    }

    public void viewProduct(ActionEvent event, Articulo articulo) throws IOException {
        System.out.println("hola");
        Platform.runLater(() -> {
            Robot robot = new Robot();
            robot.mouseMove(960, 540);
        });
        FXMLLoader loader = new FXMLLoader(getClass().getResource("productViewer.fxml"));
        root = loader.load();
        ProductViewerController productViewerController = loader.getController();
        productViewerController.setArticle(articulo);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public List<Articulo> getArticlesList(){
        return articlesList;
    }
}
