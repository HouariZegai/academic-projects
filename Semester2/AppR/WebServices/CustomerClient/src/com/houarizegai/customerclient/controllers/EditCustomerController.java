package com.houarizegai.customerclient.controllers;

import services.Customer;
import com.houarizegai.customerclient.service.CustomerService;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCustomerController implements Initializable {

    @FXML // Parent of all customer (root node)
    private VBox root;

    /* Customer infos */
    @FXML
    private JFXTextField fieldId, fieldFirstName, fieldLastName, fieldEmail;

    // For show error msg (like: Toast in android)
    private JFXSnackbar toastMsg;

    // customer infos
    public static Customer customerInfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toastMsg = new JFXSnackbar(root);

        fieldId.setText(String.valueOf(customerInfo.getId()));
        onReset();
    }

    @FXML
    private void onSave() { // Add new CustomerRegex
        if(fieldFirstName.getText() == null || fieldFirstName.getText().trim().isEmpty()) {
            toastMsg.show("First name Error!", 2000);
            return;
        }
        if(fieldLastName.getText() == null || fieldLastName.getText().trim().isEmpty()) {
            toastMsg.show("Last name Error!", 2000);
            return;
        }
        if(fieldEmail.getText() == null || fieldEmail.getText().trim().isEmpty()) {
            toastMsg.show("Email Error!", 2000);
            return;
        }


        // Using builder design pattern to make customer object
        Customer customer = new Customer();
        customer.setId(Integer.parseInt(fieldId.getText()));
        customer.setFirstName(fieldFirstName.getText());
        customer.setLastName(fieldLastName.getText());
        customer.setEmail(fieldEmail.getText());

        int status = CustomerService.editCustomer(customer);

        switch (status) {
            case 1 : {
                Notifications.create()
                        .title("Congrate!, you are edit the customer")
                        .graphic(new ImageView(new Image("/resources/images/icons/valid.png")))
                        .hideAfter(Duration.millis(2000))
                        .position(Pos.BOTTOM_RIGHT)
                        .darkStyle()
                        .show();

                CustomerController.dialogCustomerEdit.close();
                break;
            }
            default :
                toastMsg.show("Connection error!", 1500);
                break;
        }
    }

    @FXML
    private void onReset() { // Clear everything in interface
        fieldFirstName.setText(customerInfo.getFirstName());
        fieldLastName.setText(customerInfo.getLastName());
        fieldEmail.setText(customerInfo.getEmail());
    }

    @FXML
    private void onClose() {
        CustomerController.dialogCustomerEdit.close();
    }
}
