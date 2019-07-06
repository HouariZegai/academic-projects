package com.houarizegai.customerclient.controllers;

import services.Customer;
import com.houarizegai.customerclient.service.CustomerService;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private JFXTextField fieldSearch;

    @FXML
    private JFXComboBox<String> comboSearchBy;

    @FXML
    private JFXTreeTableView<TableCustomer> tableCustomer;

    private JFXTreeTableColumn<TableCustomer, String> colId, colFirstName, colLastName, colEmail;

    // Dialog showing in (add/update) table
    public static JFXDialog dialogCustomerAdd, dialogCustomerEdit, dialogCustomerDelete;

    private JFXSnackbar toastMsg;
    // data of table
    private List<Customer> Customers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboSearchBy.getItems().addAll("All", "Id", "First name", "Last name", "Email");
        comboSearchBy.getSelectionModel().select(0);

        toastMsg = new JFXSnackbar(root);

        initCustomerTable();
        loadCustomerTableData();
        // Add Filter if i change the value of search field
        fieldSearch.setOnKeyReleased(e -> filterSearchTable());
        comboSearchBy.setOnAction(e -> filterSearchTable());
    }

    private void filterSearchTable() {
        tableCustomer.setPredicate((TreeItem<TableCustomer> Customer) -> {
            String idCustomer = Customer.getValue().id.getValue();
            String firstName = (Customer.getValue().firstName.getValue() == null) ? "" : Customer.getValue().firstName.getValue().toLowerCase();
            String lastName = (Customer.getValue().lastName.getValue() == null) ? "" : Customer.getValue().lastName.getValue().toLowerCase();
            String email = (Customer.getValue().email.getValue() == null) ? "" : Customer.getValue().email.getValue().toLowerCase();


            switch (comboSearchBy.getSelectionModel().getSelectedIndex()) {
                case 0:
                    return idCustomer.contains(fieldSearch.getText().toLowerCase())
                            || firstName.contains(fieldSearch.getText().toLowerCase())
                            || lastName.contains(fieldSearch.getText().toLowerCase())
                            || email.contains(fieldSearch.getText().toLowerCase());
                case 1:
                    return idCustomer.contains(fieldSearch.getText().toLowerCase());
                case 2:
                    return firstName.contains(fieldSearch.getText().toLowerCase());
                case 3:
                    return lastName.contains(fieldSearch.getText().toLowerCase());
                case 4:
                    return email.contains(fieldSearch.getText().toLowerCase());
                default: return true;
            }

        });
    }

    class TableCustomer extends RecursiveTreeObject<TableCustomer> {
        StringProperty id;
        StringProperty firstName;
        StringProperty lastName;
        StringProperty email;

        public TableCustomer() {

        }

        TableCustomer(int id, String firstName, String lastName, String email) {
            this.id = new SimpleStringProperty(String.valueOf(id));
            this.firstName = new SimpleStringProperty(firstName);
            this.lastName = new SimpleStringProperty(lastName);
            this.email = new SimpleStringProperty(email);
        }
    }

    private void initCustomerTable() { // This function initialize the table by colunms
        colId = new JFXTreeTableColumn<>("ID");
        colId.setPrefWidth(100);
        colId.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableCustomer, String> param) -> param.getValue().getValue().id);

        colFirstName = new JFXTreeTableColumn<>("FIRSTNAME");
        colFirstName.setPrefWidth(100);
        colFirstName.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableCustomer, String> param) -> param.getValue().getValue().firstName);

        colLastName = new JFXTreeTableColumn<>("LASTNAME");
        colLastName.setPrefWidth(100);
        colLastName.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableCustomer, String> param) -> param.getValue().getValue().lastName);

        colEmail = new JFXTreeTableColumn<>("Email");
        colEmail.setPrefWidth(200);
        colEmail.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableCustomer, String> param) -> param.getValue().getValue().email);

        // Add columns to table
        tableCustomer.getColumns().addAll(colId, colFirstName, colLastName, colEmail);
        tableCustomer.setPrefWidth(1260d);
        tableCustomer.setShowRoot(false);
    }

    private void loadCustomerTableData() { // Fill table data from database

        ObservableList<TableCustomer> listCustomers = FXCollections.observableArrayList();

        this.Customers = CustomerService.getCustomers(); // Get CustomerRegex from database
        if (Customers != null) {
            for (Customer customer : Customers) {
                TableCustomer CustomerT = new TableCustomer(customer.getId(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getEmail());

                listCustomers.add(CustomerT);
            }
        }

        final TreeItem<TableCustomer> treeItem = new RecursiveTreeItem<>(listCustomers, RecursiveTreeObject::getChildren);
        try {
            tableCustomer.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
    }

    @FXML
    private void onAdd() { // On Add CustomerRegex
        try {
            VBox paneAddCustomer = FXMLLoader.load(getClass().getResource("/resources/views/AddCustomer.fxml"));
            dialogCustomerAdd = getSpecialDialog(paneAddCustomer);
            dialogCustomerAdd.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onEdit() {
        String numCustomerSelected = colId.getCellData(tableCustomer.getSelectionModel().getSelectedIndex());
        if (numCustomerSelected == null) {
            toastMsg.show("Please, select the customer you want to edit it!", 2000);
            return;
        }
        for (Customer Customer : Customers) {
            if (Customer.getId() == Integer.parseInt(numCustomerSelected)) {
                EditCustomerController.customerInfo = Customer;
                break;
            }
        }

        try {
            VBox paneEditCustomer = FXMLLoader.load(getClass().getResource("/resources/views/EditCustomer.fxml"));
            dialogCustomerEdit = getSpecialDialog(paneEditCustomer);
            dialogCustomerEdit.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onDelete() {
        // get selected Customer from table
        String numCustomerSelected = colId.getCellData(tableCustomer.getSelectionModel().getSelectedIndex());
        if (numCustomerSelected == null) {
            toastMsg.show("Please, select the customer you want to delete it!", 2000);
            return;
        }

        for (Customer c : Customers) {
            if (c.getId() == Integer.parseInt(numCustomerSelected)) {
                Customer customer = new Customer();
                customer.setId(c.getId());
                customer.setFirstName(c.getFirstName());
                customer.setLastName(c.getLastName());
                customer.setEmail(c.getEmail());
                DeleteCustomerController.customer = customer;
                break;
            }
        }

        // Show confirm dialog
        try {
            VBox paneDeleteCustomer = FXMLLoader.load(getClass().getResource("/resources/views/DeleteCustomer.fxml"));
            dialogCustomerDelete = getSpecialDialog(paneDeleteCustomer);
            dialogCustomerDelete.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private JFXDialog getSpecialDialog(Region content) { // This function create dialog
        JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);
        dialog.setOnDialogClosed(e -> loadCustomerTableData()); // if i close dialog: reload data to table
        return dialog;
    }

}
