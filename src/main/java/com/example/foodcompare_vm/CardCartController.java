package com.example.foodcompare_vm;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;


public class CardCartController {



    @FXML
    private HBox box;

    @FXML
    private Label itemCant;

    @FXML
    private ImageView itemImage;

    @FXML
    private Label itemName;

    @FXML
    private Label itemPlatform;


    public void setData(Cart_Item cartItem){

        File file = new File(cartItem.getImageSrc());
        System.out.println(file.toURI().toString());

        Image image = new Image(file.toURI().toString());
        itemImage.setImage(image);
        itemName.setText(cartItem.getName("Big Mac"));
        itemCant.setText(String.valueOf(cartItem.getCant(10)));
        itemPlatform.setText(cartItem.getPlatform());

    }
}
