package com.example.foodcompare_vm;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.bson.Document;

import java.util.ArrayList;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        //conect to database
        CConnection cConnection = new CConnection();
        cConnection.connect();

        //get collection
        MongoCollection collection = cConnection.getCollection("plataformas");
        System.out.println(collection);

        FindIterable<Document> documents = collection.find();

        ArrayList<Platform> platforms = new ArrayList<>();

        for (Document document : documents) {
            Platform platform = new Platform(document.getString("id"), document.getString("name"));
            platforms.add(platform);
        }


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