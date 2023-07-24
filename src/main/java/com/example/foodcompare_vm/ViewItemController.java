package com.example.foodcompare_vm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ViewItemController {

    @FXML
    private Button boton1;

    @FXML
    private Button boton2;

    @FXML
    private ImageView itemImage;

    @FXML
    private Label itemName;

    @FXML
    private Label itemPrice;

    @FXML
    private Label itemPlatform;

    @FXML
    private ImageView itemRestaurant;

    @FXML
    private Label itemDesc;


    @FXML
    void intialize(String itemsrc,String name,String price,String platform,String restaurant,String desc) {
        File file = new File(itemsrc);
        System.out.println(file.toURI().toString());

        Image image = new Image(file.toURI().toString());
        System.out.println(image);
        itemName.setText(name);
        itemImage.setImage(image);
    	itemPrice.setText(price);
    	itemPlatform.setText(platform);
    	itemRestaurant.setImage(new Image(restaurant));
    	itemDesc.setText(desc);
    }

    @FXML
    void onBackButtonClick(ActionEvent event) {
        try {
            Button botonPresionado = (Button) event.getSource();

            if (botonPresionado == boton1) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FView.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) boton1.getScene().getWindow();
                stage.setScene(scene);
            } else if (botonPresionado == boton2) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FView.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) boton2.getScene().getWindow();
                stage.setScene(scene);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
