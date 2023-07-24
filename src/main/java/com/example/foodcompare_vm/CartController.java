package com.example.foodcompare_vm;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class CartController implements Initializable {
    @FXML
    private Button home;
    @FXML
    private Button profile;
    @FXML
    private HBox cardLayoout;

    private List<Cart_Item> recentlyAdded;


    static Cart carrito = new Cart();

    ArrayList<Item> items = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recentlyAdded = new ArrayList<>(recentlyAdded());
        try {
        for (int i=0; i< recentlyAdded().size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("card_Cart.fxml"));
             HBox cardBox  = fxmlLoader.load();
             CardCartController cardCartController = fxmlLoader.getController();
             cardCartController.setData(recentlyAdded.get(i));
             cardLayoout.getChildren().add(cardBox);
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private List<Cart_Item> recentlyAdded(){

        CConnection cConnection = new CConnection();
        cConnection.connect();

        MongoCollection collection = cConnection.getCollection("plataformas");
        FindIterable<Document> platforms_doc = collection.find();
        ArrayList<Platform> platforms = new ArrayList<>();

        for (Document document : platforms_doc) {
            System.out.println(document);
            Platform platform = new Platform(document.getString("name"), document.get("_id").toString() );
            platforms.add(platform);
        }

        System.out.println(platforms);

        ArrayList<Restaurant> restaurants = new ArrayList<>();
        MongoCollection<Document> rest_collecion  = cConnection.getCollection("restaurantes");

        // macdonalds
        Document rest_doc = rest_collecion.find(new Document("name", "McDonalds")).first();
        Restaurant mcdonalds = new Restaurant(rest_doc.get("_id").toString(), rest_doc.getString("name"), rest_doc.getString("phonenumber"), rest_doc.getString("adress"), rest_doc.getString("src"));

        ArrayList<Platform> platforms_item1 = new ArrayList<>();
        System.out.println(rest_doc.get("Platforms"));
        for(Integer i: (ArrayList<Integer>) rest_doc.get("Platforms")){
            platforms_item1.add(platforms.get(i));
        }
        mcdonalds.addPlatforms(platforms_item1);


        // burger king
        rest_doc = rest_collecion.find(new Document("name", "Burguer King")).first();
        Restaurant burgerking = new Restaurant(rest_doc.get("_id").toString(), rest_doc.getString("name"), rest_doc.getString("phonenumber"), rest_doc.getString("adress"), rest_doc.getString("src"));

        ArrayList<Platform> platforms_item2 = new ArrayList<>();
        for(Integer i: (ArrayList<Integer>) rest_doc.get("Platforms")){
            platforms_item2.add(platforms.get(i));
        }
        burgerking.addPlatforms(platforms_item2);

        // kfc
        rest_doc = rest_collecion.find(new Document("name", "KFC")).first();
        Restaurant kfc = new Restaurant(rest_doc.get("_id").toString(), rest_doc.getString("name"), rest_doc.getString("phonenumber"), rest_doc.getString("adress"), rest_doc.getString("src"));

        ArrayList<Platform> platforms_item3 = new ArrayList<>();
        for(Integer i: (ArrayList<Integer>) rest_doc.get("Platforms")){
            platforms_item3.add(platforms.get(i));
        }
        kfc.addPlatforms(platforms_item3);

        restaurants.add(mcdonalds);
        restaurants.add(burgerking);
        restaurants.add(kfc);

        // load items
        MongoCollection<Document> item_collection = cConnection.getCollection("productos");
        FindIterable<Document> items_doc = item_collection.find();

        Integer k = 0;
        for (Document document : items_doc) {

            ArrayList<Integer> prices = (ArrayList<Integer>) document.get("prices");

            ArrayList<Integer> platforms_id = (ArrayList<Integer>) document.get("platforms");
            // get platfomrs by position in platforms array
            ArrayList<Platform> platforms_item = new ArrayList<>();
            for (Integer integer : platforms_id) {
                platforms_item.add(platforms.get(integer));
            }

            Integer restaurant_position = (Integer) document.get("restaurant");
            Restaurant restaurant = restaurants.get(restaurant_position);

            Item item = new Item(k,document.getString("name"), document.getBoolean("availability"),platforms_item, prices , restaurant, document.getString("imgpath"),document.getString("description") );
            items.add(item);
            k++;
        }

        Customer cliente = new Customer("Maria", "valmonti", "pluto@gmail.com", 1234567890);

        MongoCollection<Document> carrito_collection = cConnection.getCollection("carrito");
        System.out.println("carrito_doc: " + carrito_collection);
        //first
        Document carrito_docd = carrito_collection.find().first();
        System.out.println("carrito_docd: " + carrito_docd);
        //retrieve docs from collection
        FindIterable<Document> carrito_doc = carrito_collection.find();


        //print length of items
        System.out.println("items length: " + items.size());
        // each doc contains a item
        System.out.println("carrito_doc: " + carrito_doc);
        List<Cart_Item> ls = new ArrayList<>();
        for (Document document : carrito_doc) {
            System.out.println(document);
            Map<String, Object> order = new HashMap<>();
            // item in the document is a position in the items array
            Integer item_position = (Integer) document.get("item");
            Item item = items.get(item_position);

            System.out.println("item: " + item.getName());
            order.put("item", item);

            Integer Platform_position = (Integer) document.get("platform");
            Platform platform = platforms.get(Platform_position);


            Cart_Item cartItem = new Cart_Item();
            cartItem.setImageSrc(item.getImageSrc());
            cartItem.setCant(1);
            cartItem.setName(item.getName());
            cartItem.setPlatform(platform.getName());
            ls.add(cartItem);

/*
            // platform in the document is a position in the platforms array
            Integer platform_position = (Integer) document.get("platform");
            Platform platform = platforms.get(platform_position);

            order.put("platform", platform);

            order.put("quantity", document.get("quantity"));
            carrito.add(order);
  */      }


        //productos en el carrito



        return ls;



    }



    @FXML
    void onEmpezarButtonClick_home(ActionEvent event) {
        try {
            // Cargar el archivo FXML de la vista siguiente
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FView.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la vista siguiente (si es necesario)
            // Por ejemplo, si tienes un controlador para la vista siguiente, puedes obtenerlo así:
            // VistaSiguienteController siguienteController = loader.getController();

            // Crear una nueva escena y configurarla en el escenario (stage)
            Scene scene = new Scene(root);
            Stage stage = (Stage) home.getScene().getWindow(); // Obtener el escenario actual
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onEmpezarButtonClick_profile(ActionEvent event) {
        try {
            // Cargar el archivo FXML de la vista siguiente
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Usuario.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la vista siguiente (si es necesario)
            // Por ejemplo, si tienes un controlador para la vista siguiente, puedes obtenerlo así:
            // VistaSiguienteController siguienteController = loader.getController();

            // Crear una nueva escena y configurarla en el escenario (stage)
            Scene scene = new Scene(root);
            Stage stage = (Stage) profile.getScene().getWindow(); // Obtener el escenario actual
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
