package com.example.foodcompare_vm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewItemController {

    @FXML
    private Button boton1;

    @FXML
    private Button boton2;
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
