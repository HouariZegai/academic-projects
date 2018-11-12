package com.kmpalgorithm.java.controllers;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class TableIndexController implements Initializable {

    @FXML
    private JFXTreeTableView tableIndexFound;
    private JFXTreeTableColumn<IndexFoundTable, String> colPattern, colIndex;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        loadTableData();
    }

    private void initTable() {
        colPattern = new JFXTreeTableColumn<>("Pattern");
        colPattern.setPrefWidth(250);
        colPattern.setCellValueFactory((TreeTableColumn.CellDataFeatures<IndexFoundTable, String> param) -> param.getValue().getValue().pattern);

        colIndex = new JFXTreeTableColumn<>("Index");
        colIndex.setPrefWidth(100);
        colIndex.setCellValueFactory((TreeTableColumn.CellDataFeatures<IndexFoundTable, String> param) -> param.getValue().getValue().index);

        tableIndexFound.getColumns().addAll(colPattern, colIndex);
        tableIndexFound.setPrefWidth(350.0);
        tableIndexFound.setShowRoot(false);
    }

    private void loadTableData() {
        ObservableList<IndexFoundTable> listFoundIndex = FXCollections.observableArrayList();

        for(Integer index : KMPController.foundIndex) {
            String str = "";
            for(int i = 0; i < KMPController.pattern.length(); i++) {
                str += KMPController.inputText.charAt(index + i);
            }
            listFoundIndex.add(new IndexFoundTable(str, String.valueOf(index)));
        }

        final TreeItem<IndexFoundTable> treeItem = new RecursiveTreeItem<>(listFoundIndex, RecursiveTreeObject::getChildren);
        try {
            tableIndexFound.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
    }

    class IndexFoundTable extends RecursiveTreeObject<IndexFoundTable> {
        StringProperty pattern;
        StringProperty index;

        public IndexFoundTable(String pattern, String index) {
            this.pattern = new SimpleStringProperty(pattern);
            this.index = new SimpleStringProperty(index);
        }
    }

    @FXML
    private void onClose() {
        KMPController.dialogFoundIndex.close();
    }
}
