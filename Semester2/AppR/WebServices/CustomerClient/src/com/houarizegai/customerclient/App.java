package com.houarizegai.customerclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resources/views/Customer.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
