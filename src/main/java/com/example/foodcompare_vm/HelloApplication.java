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
    public void start(Stage primaryStage) throws Exception  {


        // Cargar el archivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("Menú_Pp.fxml"));
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