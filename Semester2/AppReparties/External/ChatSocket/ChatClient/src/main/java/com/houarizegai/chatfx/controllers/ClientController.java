package com.houarizegai.chatfx.controllers;

import com.houarizegai.chatfx.global.Config;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ClientController implements Initializable {

    @FXML
    private VBox root;

    @FXML
    private JFXListView msgNodes;

    @FXML
    private JFXTextField msgField;

    /* Start Msg variables */
    
    public static Socket socket;
    public static DataInputStream dataInputStream;
    public static DataOutputStream dataOutputStream;

    private String msgin;

    /* End Msg variables */


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        root.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                sendMsg();
            }
        });

        (new Thread() {
            @Override
            public void run() {
                try {
                    socket = new Socket(Config.serverHost, Config.port);
                    dataInputStream = new DataInputStream(socket.getInputStream());
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());

                    msgin = "";
                    while (!msgin.equals("exit")) {
                        msgin = dataInputStream.readUTF();
                        Platform.runLater(() -> addMsg(true));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @FXML
    private void sendMsg() {
        if (msgField.getText() == null || msgField.getText().trim().isEmpty()) {
            return;
        }

        try {
            dataOutputStream.writeUTF(msgField.getText().trim());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        msgin = msgField.getText().trim();
        addMsg(false);
        msgField.setText(null);
    }

    @FXML
    private void emojiChooser() {

    }

    private void addMsg(boolean senderIsServer) {
        Label lbl = new Label(msgin);
        lbl.setStyle("-fx-font-size: 16px;"
                + "-fx-background-color: #" + (senderIsServer ? "B00020" : "2196f3") + ";"
                + "-fx-text-fill: #FFF;"
                + "-fx-background-radius:25;"
                + "-fx-padding: 10px;");
        lbl.setWrapText(true);
        lbl.setMaxWidth(400);
        
        HBox container = new HBox();
        if(senderIsServer) {
            container.getChildren().add(new ImageView(new Image("/images/server-48px.png")));
            container.setAlignment(Pos.CENTER_LEFT);
            container.setSpacing(10);
            container.setPadding(new Insets(0, 10, 0, 0));
        } else {
            container.setAlignment(Pos.CENTER_RIGHT);
            container.setPadding(new Insets(0, 10, 0, 10));
        }
        container.getChildren().add(lbl);
        container.setPrefHeight(40);

        msgNodes.getItems().add(container);
    }

}
