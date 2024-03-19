package com.example.ddtech;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class ReusableArticle extends AnchorPane {
    @FXML
    private Label title;
    @FXML
    private Button buyButton;
    @FXML
    private Button learnMore;
    @FXML
    private ImageView imageView;
    @FXML
    private Label costLabel;

    public ReusableArticle() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("articles.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void setTitleText(String text) {
        this.title.setWrapText(true);
        this.title.setText(text);
        this.title.setWrapText(true);
    }
    public void setImage(String url){
        Image image = new Image(url);
        imageView.setImage(image);
    }

    public void setCostLabel(float cost){
        String text = "Desde $"+ String.valueOf(Math.round((cost/18) * 100.0) / 100.0) + " en 18 cuotas sin intereses* o $"+String.valueOf(cost);
        costLabel.setWrapText(true);
        costLabel.setText(text);
    }
    public void setBuyButtonText(String buttonText) {
        this.buyButton.setText(buttonText);
    }

    public String getBuyButtonText() {
        return this.buyButton.getText();
    }

    public void setOnActionBuyButton(EventHandler<ActionEvent> handler) {
        this.buyButton.setOnAction(handler);
    }


    public EventHandler<ActionEvent> getOnActionBuyButton() {
        return this.buyButton.getOnAction();
    }
    public void setLearnMore(String buttonText) {
        this.learnMore.setText(buttonText);
    }

    public String getLearnMore() {
        return this.learnMore.getText();
    }

    public void setOnActionLearnMore(EventHandler<ActionEvent> handler) {
        this.learnMore.setOnAction(handler);
    }


    public EventHandler<ActionEvent> getOnActionLearnMore() {
        return this.learnMore.getOnAction();
    }

}

