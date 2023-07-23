package com.example.foodcompare_vm;

import com.example.model.Food;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.File;

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


    private String [] colors = {"#ECA702", "#FFF"};
    public void setData(Food food ){
        Image image = new Image(new File(food.getImageSrc()).toURI().toString());
        foodImage.setImage(image);
        foodName.setText(food.getName());
        restaurant.setText(food.getRestaurant());
        price.setText(food.getPrice());
        box.setStyle("-fx-background-color:  " + Color.web(colors[(int)(Math.random()* colors.length)]));
    }

}
