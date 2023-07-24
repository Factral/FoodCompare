package com.example.foodcompare_vm;

import com.example.model.Food;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import java.util.Map;
import java.util.HashMap;
import java.lang.Math;


public class HelloController implements Initializable {

    @FXML
    private HBox cardLayoout;
    @FXML
    private HBox cardLayoout_1;
    @FXML
    private HBox cardLayoout_2;
    private List<Food> recentlyAdded;
    @FXML
    private Button user;

    @FXML
    private Button cart;

    static Cart carrito = new Cart();

    ArrayList<Item> items = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //conect to database
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

        for (Document document : carrito_doc) {
            System.out.println(document);
            Map<String, Object> order = new HashMap<>();
            // item in the document is a position in the items array
            Integer item_position = (Integer) document.get("item");
            Item item = items.get(item_position);

            order.put("item", item);

            // platform in the document is a position in the platforms array
            Integer platform_position = (Integer) document.get("platform");
            Platform platform = platforms.get(platform_position);

            order.put("platform", platform);

            order.put("quantity", document.get("quantity"));
            carrito.add(order);
        }

        System.out.println(items);

        ArrayList<Map> itemsCar = carrito.getItems();
        if (itemsCar.isEmpty()) {
            System.out.println("No hay ningún item en el carrito.");
        } else {
            for (int i = 0; i < itemsCar.size(); i++) {
                Item currentItemCar = (Item) itemsCar.get(i).get("item");
                Platform currentPlatformCar = (Platform) itemsCar.get(i).get("platform");
                System.out.println((i + 1) + ". " + currentItemCar.getName() + " - " + currentPlatformCar.getName() + " - " + itemsCar.get(i).get("price"));
            }
        }







        try {
            recentlyAdded = new ArrayList<>( recentlyAdded(items, "Rappi"));

            for(int i=0 ; i<recentlyAdded.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(recentlyAdded.get(i));
                cardLayoout.getChildren().add(cardBox);

            }
            recentlyAdded = new ArrayList<>( recentlyAdded(items, "DidiFood"));
            for(int i=0 ; i<recentlyAdded.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(recentlyAdded.get(i));

                cardLayoout_1.getChildren().add(cardBox);

            }
            recentlyAdded = new ArrayList<>( recentlyAdded(items, "UberEats"));

            for(int i=0 ; i<recentlyAdded.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(recentlyAdded.get(i));

                cardLayoout_2.getChildren().add(cardBox);
            }


        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Food> recentlyAdded(List itemsList, String platformName) {


        List<Food> ls = new ArrayList<>();
        for (int i = 0; i < itemsList.size(); i++) {
            Item item = (Item) itemsList.get(i);
            //Map<Platform, Integer> , solo recupera la plataforma, y si didifood esta en la lista, entonces es un producto de comida
            List<Platform> platforms = item.getPlatforms();
            for (int j = 0; j < platforms.size(); j++) {
                Platform platform = platforms.get(j);
                if (platform.getName().equals(platformName)) {
                    System.out.println("HOLAAA");
                    Food food = new Food();
                    food.setId(item.getId());
                    food.setName(item.getName());
                    food.setPrice(item.getPrices().get(j).toString());
                    food.setImageSrc(item.getImageSrc());
                    System.out.println(item.getRestaurant().getSrcImage());
                    food.setRestaurantName(item.getRestaurant().getName());
                    food.setRestaurant( item.getRestaurant().getSrcImage() );
                    food.setDesc(item.getDesc());
                    food.setPlatform(platformName);
                    ls.add(food);
                }
            }

        }

        return ls;
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        // Aquí, realiza las acciones necesarias para cambiar a otra vista
        // Por ejemplo, cargar la nueva vista y mostrarla en una nueva ventana.

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Usuario.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) user.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleButtonClick_profile(ActionEvent event) {
        // Aquí, realiza las acciones necesarias para cambiar a otra vista
        // Por ejemplo, cargar la nueva vista y mostrarla en una nueva ventana.

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Usuario.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) user.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonClick_cart(ActionEvent event) {
        // Aquí, realiza las acciones necesarias para cambiar a otra vista
        // Por ejemplo, cargar la nueva vista y mostrarla en una nueva ventana.

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Carrito.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) cart.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
