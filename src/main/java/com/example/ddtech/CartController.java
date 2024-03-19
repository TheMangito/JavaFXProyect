package com.example.ddtech;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CartController {
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
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane scrollAnchorPane;
    @FXML
    private MediaView mediaView;
    @FXML
    private MediaPlayer mediaPlayer;
    @FXML
    private Button categories;
    @FXML
    private AnchorPane categoriesMenu;
    @FXML
    private Button home;


    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label price;
    @FXML
    private Label stock;
    @FXML
    private ImageView image;

    private Tienda tienda;
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private Label resumeText;
    @FXML
    private ImageView methods;
    private final File jsonCart = new File("src/main/resources/com/example/ddtech/cart.json");
    private List<Articulo> cartArticles = new ArrayList<>();
    @FXML
    private Label total;



    public void initialize() throws IOException {
        resumeText.setText("\n" +
                "Credit/Debit Card\n" +
                "Cash (7-Eleven, Circle K, Soriana, Extra, etc)\n" +
                "PayPal\n" +
                "Mercado Pago (Credit, Debit, Cash, Electronic Transfer, and more)");
        tienda = new Tienda();
        categories.setOnMouseEntered(event -> {
            categoriesMenu.setVisible(true);
        });
        categoriesMenu.setOnMouseExited(event -> {
            categoriesMenu.setVisible(false);
        });
        Image image = new Image("/methods.png");
        methods.setImage(image);
        loadArticles();
        loadArticles(cartArticles.size(),0,cartArticles);
        calculateTotal();
    }

    public void pay(ActionEvent event)throws IOException{
        cartArticles.clear();
        ObjectMapper mapper = new ObjectMapper();
        File json = new File("src/main/resources/com/example/ddtech/cart.json");
        try {
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            writer.writeValue(json, cartArticles);
        }catch (IOException e) {
            e.printStackTrace();
        }
        categoriesMenu.setVisible(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
        root = loader.load();
        CartController categoriesController = loader.getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void calculateTotal(){
        float total = 0.0F;
        for(Articulo articulo:cartArticles){
            total += Math.round((articulo.getPrecio() * 100.0) / 100.0);
        }
        this.total.setText("Total = $"+String.valueOf(total));
    }
    public void loadArticles() throws IOException {
        try {
            String json = FileUtils.readFileToString(jsonCart, StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            cartArticles = objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadArticles(int nNumbers, int minNumber, List<Articulo> articles){
        Tienda tienda = new Tienda();
        Set<Integer> numbers = generateRandomNumbers(nNumbers, minNumber, articles.size()-1);
        int numberOfArticlesToShow = 2;
        double x = 64;
        double y = 217;
        final double initialX = x;
        for(Integer number : numbers){
            if (numberOfArticlesToShow == 0){
                y += 500;
                numberOfArticlesToShow = 2;
                x = initialX;
            }
            tienda.reusableAlert(articles.get(number), scrollAnchorPane, x, y);
            numberOfArticlesToShow--;
            x+=500;
        }
    }
    public void categories(){
        if (!categoriesMenu.isVisible()){
            categoriesMenu.setVisible(true);
        }else {
            categoriesMenu.setVisible(false);
        }
    }
    public void home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root = loader.load();
        HelloController categoriesController = loader.getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void categories(ActionEvent event, String category) throws IOException {
        categoriesMenu.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("categories.fxml"));
        root = loader.load();
        CategoriesController categoriesController = loader.getController();
        categoriesController.loadArticles((this.tienda.selectCategory(category).size()), 0, this.tienda.selectCategory(category));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void categories(ActionEvent event) throws IOException {
        categoriesMenu.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("categories.fxml"));
        root = loader.load();
        CategoriesController categoriesController = loader.getController();
        categoriesController.loadArticles((this.tienda.getArticlesList()).size(), 0, this.tienda.getArticlesList());
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


    public void setArticle(Articulo article){
        title.setText(article.getNombre());
        description.setWrapText(true);
        description.setText(article.getDescripcion() + "\n" +article.getGarantia());
        price.setText("$"+String.valueOf(article.getPrecio()));
        stock.setText("Stock: "+ String.valueOf(article.getStock()));
        Image image = new Image(article.getImage());
        this.image.setImage(image);
    }
    public static Set<Integer> generateRandomNumbers(int nNumbers, int min, int max) {
        Random random = new Random();
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < nNumbers) {
            int numeroAleatorio = random.nextInt(max - min + 1) + min;
            numbers.add(numeroAleatorio);
        }
        return numbers;
    }
}
