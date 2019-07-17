package com.houarizegai.customerclient.controllers;

import com.houarizegai.customerclient.model.CustomerModel;
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

public class AddCustomerController implements Initializable {

    @FXML // Parent of all customer (root node)
    private VBox root;

    @FXML
    private JFXTextField fieldFirstName, fieldLastName, fieldEmail;

    // For show error msg (like: Toast in android)
    private JFXSnackbar toastMsg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toastMsg = new JFXSnackbar(root);
    }

    @FXML
    private void onAdd() { // add new customer
        if(fieldFirstName.getText() == null || fieldFirstName.getText().trim().isEmpty()) {
            toastMsg.show("First name error!", 2000);
            return;
        }
        if(fieldFirstName.getText() == null || fieldFirstName.getText().trim().isEmpty()) {
            toastMsg.show("Last name error!", 2000);
            return;
        }
        if(fieldEmail.getText() == null || fieldEmail.getText().trim().isEmpty()) {
            toastMsg.show("Email error!", 2000);
            return;
        }

        // Using builder design pattern to make customer object
        CustomerModel customer = new CustomerModel();
        customer.setFirstName(fieldFirstName.getText());
        customer.setLastName(fieldLastName.getText());
        customer.setEmail(fieldEmail.getText());

        int status = CustomerService.addCustomer(customer);

        switch (status) {
            case 1:
                Notifications.create()
                        .title("Congrate!, you are added new customer")
                        .graphic(new ImageView(new Image("/resources/images/icons/valid.png")))
                        .hideAfter(Duration.millis(2000))
                        .position(Pos.BOTTOM_RIGHT)
                        .darkStyle()
                        .show();

                onClear();
                break;
            default:
                toastMsg.show("Connection Error!", 1500);
                break;
            }
        }

    @FXML
    private void onClear() { // Clear everything in interface
        fieldFirstName.setText(null);
        fieldLastName.setText(null);
        fieldEmail.setText(null);
    }

    @FXML
    private void onClose() {
        CustomerController.dialogCustomerAdd.close();
    }
}
