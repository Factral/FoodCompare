package com.example.foodcompare_vm;

import com.mongodb.client.MongoCollection;
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
import org.bson.Document;

import java.io.File;
import java.io.IOException;

public class ViewItemController {

    @FXML
    private Button back;

    @FXML
    private Button addCart;

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

    public Integer id;

    @FXML
    void intialize(Integer id,String itemsrc,String name,String price,String restaurant,String platform,String desc) {
        File file = new File(itemsrc);
        System.out.println(file.toURI().toString());

        this.id = id;
        Image image = new Image(file.toURI().toString());
        System.out.println(image);
        itemName.setText(name);
        itemImage.setImage(image);
    	itemPrice.setText(price);
    	itemPlatform.setText(platform);

        System.out.println(restaurant);
        File file_ = new File(restaurant);
        System.out.println(file_.toURI().toString());

        Image image2 = new Image(file_.toURI().toString());

    	itemRestaurant.setImage(image2);
    	itemDesc.setText(desc);
    }

    @FXML
    void onBackButtonClick(ActionEvent event) {
        try {
            Button botonPresionado = (Button) event.getSource();

            if (botonPresionado == back) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FView.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) back.getScene().getWindow();
                stage.setScene(scene);
            } else if (botonPresionado == addCart) {
                // sned item to carrito collection in mongo
                CConnection cConnection = new CConnection();
                cConnection.connect();
                MongoCollection collection = cConnection.getCollection("carrito");

                //Didifood 0
                //Rappi 1
                //UberEats 2

                Document document = new Document();
                document.append("item", id);

                System.out.println(itemPlatform.getText());
                if(itemPlatform.getText().equals("DidiFood")) {
                	document.append("platform", 0);
                }else if(itemPlatform.getText().equals("Rappi")) {
                    document.append("platform", 1);
                }else if(itemPlatform.getText().equals("UberEats")) {
                    	document.append("platform", 2);
                }

                //quantity
                document.append("quantity", 1);

                collection.insertOne(document);


                FXMLLoader loader = new FXMLLoader(getClass().getResource("FView.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) back.getScene().getWindow();
                stage.setScene(scene);




            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
