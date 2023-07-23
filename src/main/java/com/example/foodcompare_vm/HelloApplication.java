package com.example.foodcompare_vm;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

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

            Item item = new Item(document.getString("name"), document.getBoolean("availability"),platforms_item, prices , restaurant);

        }

        // show items of




        // Cargar el archivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("FView.fxml"));

        // Crear la Scene con el contenido cargado desde FXML
        Scene scene = new Scene(root); // Ajusta el tamaño según tus necesidades

        // Configurar el Stage (ventana principal)
        primaryStage.setTitle("Aplicación JavaFX");
        primaryStage.setScene(scene);

        // Mostrar la ventana
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}