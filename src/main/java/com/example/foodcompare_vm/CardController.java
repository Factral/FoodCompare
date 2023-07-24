package com.example.foodcompare_vm;

import com.example.model.Food;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class CardController {

    @FXML
    private HBox box;

    @FXML
    private Label foodName;

    @FXML
    private ImageView foodImage;

    @FXML
    private Label price;

    @FXML
    private Label restaurant;

    @FXML
    private Button ver;




    private String [] colors = {"ECA702", "FFF"};
    public void setData(Food food ){
        System.out.println(food.getImageSrc());
        // load the image from the folder img
        File file = new File(food.getImageSrc());

        System.out.println(file.toURI().toString());

        Image image = new Image(file.toURI().toString());
        foodImage.setImage(image);
        foodName.setText(food.getName());
        restaurant.setText(food.getRestauranName());
        price.setText(food.getPrice());
        box.setStyle("-fx-background-color:  " + Color.web(colors[(int)(Math.random()* colors.length)]));

        ver.setOnAction(event -> {
            System.out.println(food.getName());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("View_Item.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ver.getScene().getWindow();
                ViewItemController controller = loader.getController();
                System.out.println(food.getImageSrc());
                stage.setScene(scene);
                controller.intialize(food.getId(),food.getImageSrc(),food.getName(),food.getPrice(),food.getRestaurant(),food.getPlatform(),food.getDesc());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



}
