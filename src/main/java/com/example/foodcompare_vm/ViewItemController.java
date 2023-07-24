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
    private Button back;

    @FXML
    void onBackButtonClick(ActionEvent event) {
        try {
            // Cargar el archivo FXML de la vista siguiente
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FView.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la vista siguiente (si es necesario)
            // Por ejemplo, si tienes un controlador para la vista siguiente, puedes obtenerlo así:
            // VistaSiguienteController siguienteController = loader.getController();

            // Crear una nueva escena y configurarla en el escenario (stage)
            Scene scene = new Scene(root);
            Stage stage = (Stage) back.getScene().getWindow(); // Obtener el escenario actual
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
