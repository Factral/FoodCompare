package com.example.foodcompare_vm;

import com.example.model.Food;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private HBox cardLayoout;
    @FXML
    private HBox cardLayoout_1;
    @FXML
    private HBox cardLayoout_2;

    private List<Food> recentlyAdded;

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
        Restaurant mcdonalds = new Restaurant(rest_doc.get("_id").toString(), rest_doc.getString("name"), rest_doc.getString("phonenumber"), rest_doc.getString("adress"));

        ArrayList<Platform> platforms_item1 = new ArrayList<>();
        System.out.println(rest_doc.get("Platforms"));
        for(Integer i: (ArrayList<Integer>) rest_doc.get("Platforms")){
            platforms_item1.add(platforms.get(i));
        }
        mcdonalds.addPlatforms(platforms_item1);


        // burger king
        rest_doc = rest_collecion.find(new Document("name", "Burguer King")).first();
        Restaurant burgerking = new Restaurant(rest_doc.get("_id").toString(), rest_doc.getString("name"), rest_doc.getString("phonenumber"), rest_doc.getString("adress"));

        ArrayList<Platform> platforms_item2 = new ArrayList<>();
        for(Integer i: (ArrayList<Integer>) rest_doc.get("Platforms")){
            platforms_item2.add(platforms.get(i));
        }
        burgerking.addPlatforms(platforms_item2);

        // kfc
        rest_doc = rest_collecion.find(new Document("name", "KFC")).first();
        Restaurant kfc = new Restaurant(rest_doc.get("_id").toString(), rest_doc.getString("name"), rest_doc.getString("phonenumber"), rest_doc.getString("adress"));

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

        ArrayList<Item> items = new ArrayList<>();
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

            Item item = new Item(document.getString("name"), document.getBoolean("availability"),platforms_item, prices , restaurant, document.getString("imageSrc"));
            items.add(item);
        }


        recentlyAdded = new ArrayList<>( recentlyAdded(items));

        try {
            for(int i=0 ; i<recentlyAdded.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(recentlyAdded.get(i));
                cardLayoout.getChildren().add(cardBox);

            }
            for(int i=0 ; i<recentlyAdded.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(recentlyAdded.get(i));

                cardLayoout_1.getChildren().add(cardBox);

            }
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

    private List<Food> recentlyAdded(List itemsList) {


        List<Food> ls = new ArrayList<>();
        Food food = new Food();
        food.setName("Big Mac");
        food.setPrice("10000");
        food.setImageSrc("src/main/java/com/example/img/big_mac.jpg");
        food.setRestaurant("Mc Donald's");
        ls.add(food);


        food = new Food();
        food.setName("Cuarto  de \n Libra");
        food.setPrice("10000");
        food.setImageSrc("src/main/java/com/example/img/cuarto_libra.jpg");
        food.setRestaurant("Mc Donald's");
        ls.add(food);



        food = new Food();
        food.setName("Whopper");
        food.setPrice("11000");
        food.setImageSrc("src/main/java/com/example/img/whopper.jpg");
        food.setRestaurant("Burguer King");
        ls.add(food);

        food = new Food();
        food.setName("Big King");
        food.setPrice("10000");
        food.setImageSrc("src/main/java/com/example/img/big_king.jpg");
        food.setRestaurant("Burguer King");
        ls.add(food);

        food = new Food();
        food.setName("Crispy");
        food.setPrice("12000");
        food.setImageSrc("src/main/java/com/example/img/crispy.jpg");
        food.setRestaurant("KFC");
        ls.add(food);

        food = new Food();
        food.setName("Twister");
        food.setPrice("11500");
        food.setImageSrc("src/main/java/com/example/img/twister.jpg");
        food.setRestaurant("KFC");
        ls.add(food);

        food = new Food();
        food.setName("Super Pollo");
        food.setPrice("39900");
        food.setImageSrc("src/main/java/com/example/img/super_pollo.jpg");
        food.setRestaurant("Domino's");
        ls.add(food);

        food = new Food();
        food.setName("Hawaiian");
        food.setPrice("49900");
        food.setImageSrc("src/main/java/com/example/img/hawaiian.jpg");
        food.setRestaurant("Domino's");
        ls.add(food);


        food = new Food();
        food.setName("Arequipe \n Rolls");
        food.setPrice("11900");
        food.setImageSrc("src/main/java/com/example/img/rolls_large.jpg");
        food.setRestaurant("Domino's");
        ls.add(food);

        food = new Food();
        food.setName("Sandwich \n BBQ");
        food.setPrice("25500");
        food.setImageSrc("src/main/java/com/example/img/carne_sw.jpg");
        food.setRestaurant("Subway");
        ls.add(food);

        food = new Food();
        food.setName("Ensalada \n Atun");
        food.setPrice("16000");
        food.setImageSrc("src/main/java/com/example/img/atun_sd.jpg");
        food.setRestaurant("Subway");
        ls.add(food);

        food = new Food();
        food.setName("Sandwich \n Queso");
        food.setPrice("12000");
        food.setImageSrc("src/main/java/com/example/img/sd_huevoq.jpg");
        food.setRestaurant("Subway");
        ls.add(food);

        food = new Food();
        food.setName("Burrito");
        food.setPrice("43000");
        food.setImageSrc("src/main/java/com/example/img/burrito_sp.jpg");
        food.setRestaurant("Taco Bell");
        ls.add(food);

        food = new Food();
        food.setName("Quesarito");
        food.setPrice("31250");
        food.setImageSrc("src/main/java/com/example/img/quesarito.jpg");
        food.setRestaurant("Taco Bell");
        ls.add(food);

        food = new Food();
        food.setName("Bacon Fries");
        food.setPrice("22500");
        food.setImageSrc("src/main/java/com/example/img/bc_topfries.jpg");
        food.setRestaurant("Taco Bell");
        ls.add(food);

        return ls;
    }
}
