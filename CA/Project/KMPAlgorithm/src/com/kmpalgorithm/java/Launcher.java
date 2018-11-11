package com.kmpalgorithm.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/kmpalgorithm/resources/views/KMP.fxml"));

        stage.setScene(new Scene(root));
        Launcher.stage = stage;
        stage.setTitle("KMP Algorithm");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
