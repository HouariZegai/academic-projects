package com.kmpalgorithm.java.controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.kmpalgorithm.java.Launcher;
import com.kmpalgorithm.java.searchengine.KMP;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
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
    private StackPane root;
    @FXML
    private VBox parentLeft;

    /* Start Input Part */

    @FXML
    private JFXTextArea areaInput;
    @FXML
    private JFXTextField fieldPattern;
    @FXML
    private JFXToggleButton toggleCaseSensitive;
    @FXML
    private JFXComboBox<String> comboAlgorithmType;

    /* Start Input Part */

    /* Start Output Part */

    @FXML
    private TextFlow fieldResult;
    @FXML
    private VBox boxResult;
    @FXML
    private Hyperlink linkTimes;
    @FXML
    private Label lblExecutionTime;
    @FXML
    private JFXTreeTableView tableHistory;

    private JFXTreeTableColumn<TableHistory, String> colNum, colMethod, colPattern, colTime;

    /* End Output Part */

    // Toast Error Msg
    private JFXSnackbar toastMsg;

    // For show IndexTable View
    public static JFXDialog dialogFoundIndex;

    // Used in table index GUI
    public static List<Integer> foundIndex;
    public static String inputText;
    public static String pattern;

    // Data of table+
    private ObservableList<TableHistory> listHistory = FXCollections.observableArrayList();
    // Number of ligne
    private int indexTable = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboAlgorithmType.getItems().addAll("KMP");

        // just for testing
        comboAlgorithmType.getSelectionModel().select(0);

        toastMsg = new JFXSnackbar(parentLeft);
        areaInput.setOnKeyTyped(e -> {onRemoveOutput();});
        fieldPattern.setOnKeyTyped(e -> {onRemoveOutput();});
        toggleCaseSensitive.setOnAction(e -> onRemoveOutput());

        areaInput.setOnKeyTyped(e -> {
            if(indexTable == 0)
                return;
            indexTable = 0;
            listHistory.clear();
            final TreeItem<TableHistory> treeItem = new RecursiveTreeItem<>(listHistory, RecursiveTreeObject::getChildren);
            try {
                tableHistory.setRoot(treeItem);
            } catch (Exception ex) {
                System.out.println("Error catched !");
            }
        });

        initTable();
    }

    class TableHistory extends RecursiveTreeObject<TableHistory> {
        StringProperty num;
        StringProperty method;
        StringProperty pattern;
        StringProperty time;

        public TableHistory(String num, String method, String pattern, String time) {
            this.num = new SimpleStringProperty(num);
            this.method = new SimpleStringProperty(method);
            this.pattern = new SimpleStringProperty(pattern);
            this.time = new SimpleStringProperty(time);
        }
    }

    private void initTable() {
        colNum = new JFXTreeTableColumn<>("N°");
        colNum.setPrefWidth(50);
        colNum.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableHistory, String> param) -> param.getValue().getValue().num);

        colMethod = new JFXTreeTableColumn<>("Method");
        colMethod.setPrefWidth(100);
        colMethod.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableHistory, String> param) -> param.getValue().getValue().method);

        colPattern = new JFXTreeTableColumn<>("Pattern");
        colPattern.setPrefWidth(250);
        colPattern.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableHistory, String> param) -> param.getValue().getValue().pattern);

        colTime = new JFXTreeTableColumn<>("Time (ms)");
        colTime.setPrefWidth(150);
        colTime.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableHistory, String> param) -> param.getValue().getValue().time);

        tableHistory.getColumns().addAll(colNum, colMethod, colPattern, colTime);
        tableHistory.setPrefWidth(550.0);
        tableHistory.setShowRoot(false);
    }

    private void addToTableHistory(String method, String pattern, double time) {

        listHistory.add(new TableHistory(String.valueOf(++indexTable), method, pattern, String.valueOf(time)));

        final TreeItem<TableHistory> treeItem = new RecursiveTreeItem<>(listHistory, RecursiveTreeObject::getChildren);
        try {
            tableHistory.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
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
        if(comboAlgorithmType.getSelectionModel().getSelectedItem() == null) {
            toastMsg.show("Please Select Algorithm Type !", 2000);
            return;
        }
        switch (comboAlgorithmType.getSelectionModel().getSelectedIndex()) {
            case 0 : {
                //long startTime = System.currentTimeMillis();
                long startTime = System.nanoTime();

                KMP kmp = new KMP(areaInput.getText(), toggleCaseSensitive.isSelected());
                kmp.indexOf(fieldPattern.getText());
                //long endTime = System.currentTimeMillis();
                long endTime = System.nanoTime();

                this.foundIndex = kmp.searchAndGetIndex(fieldPattern.getText());

                KMPController.pattern = fieldPattern.getText();
                KMPController.inputText = areaInput.getText();

                MaterialDesignIconView iconEmojyFound = (MaterialDesignIconView) boxResult.getChildren().get(0);
                Label lblFound = (Label) ((HBox) boxResult.getChildren().get(1)).getChildren().get(0);

                if(!foundIndex.isEmpty()) {
                    lblFound.setText("Found !");
                    lblFound.setTextFill(Paint.valueOf("#00b248"));
                    iconEmojyFound.setGlyphName("EMOTICON_HAPPY");
                    iconEmojyFound.setFill(Paint.valueOf("#00b248"));
                    linkTimes.setText(foundIndex.size() + " Times");
                    setResultTextFlow(foundIndex);
                    lblExecutionTime.setText((endTime - startTime) / 1000000d + " ms");
                    addToTableHistory("KMP", KMPController.pattern, (endTime - startTime) / 1000000d);
                } else {
                    lblFound.setText("Not Found !");
                    lblFound.setTextFill(Paint.valueOf("#000"));
                    iconEmojyFound.setGlyphName("EMOTICON_SAD");
                    iconEmojyFound.setFill(Paint.valueOf("#000"));
                    lblExecutionTime.setText("0 ms");
                    linkTimes.setText("0 Times");
                    linkTimes.setDisable(true);
                }
            } break;
        }

        boxResult.setVisible(true);

    }

    @FXML
    private void onLinkTimes() {
        VBox foundIndexView = null;
        try {
            foundIndexView = FXMLLoader.load(getClass().getResource("/com/kmpalgorithm/resources/views/TableIndex.fxml"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        dialogFoundIndex = new JFXDialog(root, foundIndexView, JFXDialog.DialogTransition.CENTER);
        dialogFoundIndex.show();
    }

    private void onRemoveOutput() { // remove output
        boxResult.setVisible(false);
        linkTimes.setDisable(false);
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
                    fieldResult.getChildren().add(getTextNotFound(str));
                    str = "";
                }

                for(int j = 0; j < patternLength; j++) {
                    str += inputTxt.charAt(i++);
                }
                fieldResult.getChildren().add(getTextFound(str));
                str = "";
                i--;
            } else {
                str += inputTxt.charAt(i);
            }
        }

        if(!str.equals("")) {
            fieldResult.getChildren().add(getTextNotFound(str));
        }
    }

    private Text getTextFound(String txt) {
        Text text = new Text(txt);
        text.setStyle("-fx-font-size: 16px; -fx-fill: #F00;");

        return text;
    }

    private Text getTextNotFound(String txt) {
        Text text = new Text(txt);
        text.setStyle("-fx-font-size: 16px;");

        return text;
    }

}
