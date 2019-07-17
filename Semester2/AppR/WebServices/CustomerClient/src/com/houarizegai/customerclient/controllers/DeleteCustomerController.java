package com.houarizegai.customerclient.controllers;

import com.houarizegai.customerclient.model.CustomerModel;
import com.houarizegai.customerclient.service.CustomerService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteCustomerController implements Initializable {

    @FXML
    private Label lblId, lblFirstName, lblLastName, lblEmail;

    // client u want to delete it
    public static CustomerModel customer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /* Initialize the view by information of customer u want to delete it */
        lblId.setText(String.valueOf(customer.getId()));
        lblFirstName.setText(customer.getFirstName());
        lblLastName.setText(customer.getLastName());
        lblEmail.setText(customer.getEmail());
    }

    @FXML
    private void onDelete() {
        int status = CustomerService.deleteClient(customer.getId());

        switch (status) {
            case 1:
                Notifications notification2 = Notifications.create()
                        .title("You are delete the customer !")
                        .graphic(new ImageView(new Image("/resources/images/icons/valid.png")))
                        .hideAfter(Duration.millis(4000))
                        .position(Pos.BOTTOM_RIGHT);
                notification2.darkStyle();
                notification2.show();
                break;
            default:
                System.out.println("Connection error (cannot delete client)!");
                break;
        }

        CustomerController.dialogCustomerDelete.close();
    }

    @FXML
    private void onCancel() {
        CustomerController.dialogCustomerDelete.close();
    }
}
