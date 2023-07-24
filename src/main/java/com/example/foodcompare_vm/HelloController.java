package com.example.foodcompare_vm;

import com.example.model.Food;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private HBox cardLayoout;
    private List<Food> recentlyAdded;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recentlyAdded = new ArrayList<>( recentlyAdded());
        try {
        for(int i=0 ; i<recentlyAdded.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("card.fxml"));
            HBox cardBox = fxmlLoader.load();
            CardController cardController = fxmlLoader.getController();
            cardController.setData(recentlyAdded.get(i));
            cardLayoout.getChildren().add(cardBox);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Food> recentlyAdded() {

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
        food.setImageSrc("src/main/java/com/example/img/home.png");
        food.setRestaurant("Mc Donald's");
        ls.add(food);



        food = new Food();
        food.setName("Whopper");
        food.setPrice("11000");
        food.setImageSrc("src/main/java/com/example/img/whopper.png");
        food.setRestaurant("Burguer King");
        ls.add(food);

        food = new Food();
        food.setName("Big King");
        food.setPrice("10000");
        food.setImageSrc("src/main/java/com/example/img/big_king.png");
        food.setRestaurant("Burguer King");
        ls.add(food);

        food = new Food();
        food.setName("Crispy");
        food.setPrice("12000");
        food.setImageSrc("src/main/java/com/example/img/crispy.webp");
        food.setRestaurant("KFC");
        ls.add(food);

        food = new Food();
        food.setName("Twister");
        food.setPrice("11500");
        food.setImageSrc("src/main/java/com/example/img/twister.png");
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
        food.setImageSrc("src/main/java/com/example/img/hawaiian.webp");
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
        food.setImageSrc("src/main/java/com/example/img/sd_huevoq.avif");
        food.setRestaurant("Subway");
        ls.add(food);

        food = new Food();
        food.setName("Burrito");
        food.setPrice("43000");
        food.setImageSrc("src/main/java/com/example/img/burrito_sp.webp");
        food.setRestaurant("Taco Bell");
        ls.add(food);

        food = new Food();
        food.setName("Quesarito");
        food.setPrice("31250");
        food.setImageSrc("src/main/java/com/example/img/quesarito.webp");
        food.setRestaurant("Taco Bell");
        ls.add(food);

        food = new Food();
        food.setName("Bacon Fries");
        food.setPrice("22500");
        food.setImageSrc("src/main/java/com/example/img/bc_topfries");
        food.setRestaurant("Taco Bell");
        ls.add(food);

        return ls;
    }
}
