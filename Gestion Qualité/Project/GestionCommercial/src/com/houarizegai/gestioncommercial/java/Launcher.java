package com.houarizegai.gestioncommercial.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// CRUD: Client / Famille / Stock
public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(""));

        stage.setScene(new Scene(root));
        stage.setTitle("Gestion Commercial");
        stage.show();
    }
}
