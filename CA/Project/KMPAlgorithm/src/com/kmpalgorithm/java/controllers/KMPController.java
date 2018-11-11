package com.kmpalgorithm.java.controllers;

import com.jfoenix.controls.*;
import com.kmpalgorithm.java.Launcher;
import com.kmpalgorithm.java.searchengine.KMP;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class KMPController implements Initializable {

    @FXML
    private VBox root;
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
    private TextFlow fieldResult;
    // Toast Error Msg
    private JFXSnackbar toastMsg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toastMsg = new JFXSnackbar(root);

        areaInput.setOnKeyTyped(e -> {onRemoveOutput();});
        fieldPattern.setOnKeyTyped(e -> {onRemoveOutput();});
        toggleCaseSensitive.setOnAction(e -> onRemoveOutput());
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
        onRemoveOutput();

        if(areaInput.getText() == null || areaInput.getText().trim().isEmpty()) {
            toastMsg.show("Input is Empty !", 2000);
            return;
        }
        if(fieldPattern.getText() == null || fieldPattern.getText().trim().isEmpty()) {
            toastMsg.show("Pattern is Empty !", 2000);
            return;
        }

        KMP kmp = new KMP(areaInput.getText(), toggleCaseSensitive.isSelected());
        List<Integer> foundIndex = kmp.searchAndGetIndex(fieldPattern.getText());

        Label lblFound = (Label) boxResult.getChildren().get(0);
        MaterialDesignIconView iconEmojyFound = (MaterialDesignIconView) lblFound.getGraphic();
        Hyperlink linkCountNumber = (Hyperlink) boxResult.getChildren().get(1);

        if(!foundIndex.isEmpty()) {
            lblFound.setText("Found !");
            lblFound.setTextFill(Paint.valueOf("#00b248"));
            iconEmojyFound.setGlyphName("EMOTICON_HAPPY");
            iconEmojyFound.setFill(Paint.valueOf("#00b248"));
            linkCountNumber.setText(foundIndex.size() + " Times");
            setResultTextFlow(foundIndex);
        } else {
            lblFound.setText("Not Found !");
            lblFound.setTextFill(Paint.valueOf("#000"));
            iconEmojyFound.setGlyphName("EMOTICON_SAD");
            iconEmojyFound.setFill(Paint.valueOf("#000"));
            linkCountNumber.setText("0 Times");
            linkCountNumber.setDisable(true);
        }
        boxResult.setVisible(true);
    }

    @FXML
    private void onLinkTimes() {

    }

    private void onRemoveOutput() { // remove output
        boxResult.setVisible(false);
        ((Hyperlink) boxResult.getChildren().get(1)).setDisable(false);
        fieldResult.getChildren().clear();
    }

    private void setResultTextFlow(List<Integer> indexs) { // Add Labels structured to TextFlow
        String inputTxt = areaInput.getText();
        int patternLength = fieldPattern.getText().length();
        int indexTableOfFound = 0;

        String str = "";

        for(int i = 0; i < inputTxt.length(); i++) {
            if(indexTableOfFound < indexs.size() && indexs.get(indexTableOfFound) == i) {
                indexTableOfFound++;
                if(!str.equals("")) {
                    addLabelNotFound(str);
                    str = "";
                }

                for(int j = 0; j < patternLength; j++) {
                    str += inputTxt.charAt(i++);
                }
                addLabelFound(str);
                str = "";
            } else {
                str += inputTxt.charAt(i);
            }
        }

        if(!str.equals("")) {
            addLabelNotFound(str);
        }
    }

    private void addLabelFound(String txt) {
        Label label = new Label(txt);
        label.setStyle("-fx-font-size: 16px;" +
                "-fx-background-color: #F00");

        fieldResult.getChildren().add(label);
    }

    private void addLabelNotFound(String txt) {
        Label label = new Label(txt);
        label.setStyle("-fx-font-size: 16px;");

        fieldResult.getChildren().add(label);
    }

}
