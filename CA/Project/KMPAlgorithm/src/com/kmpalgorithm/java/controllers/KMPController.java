package com.kmpalgorithm.java.controllers;

import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.kmpalgorithm.java.Launcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KMPController implements Initializable {

    @FXML
    private JFXTextArea areaInput;
    @FXML
    private JFXTextField fieldPattern;
    @FXML
    private JFXToggleButton toggleCaseSensitive;
    @FXML
    private JFXSpinner spinnerSearch;
    @FXML
    private HBox boxResult;
    @FXML
    private Hyperlink linkTimes;
    @FXML
    private TextFlow fieldResult;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onLoadFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select *.txt File");

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(Launcher.stage);

        if(file != null) {
            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }

                areaInput.setText(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void onSearch() {

    }

    @FXML
    private void onLinkTimes() {

    }

}
