package com.houarizegai.customerclient.service;

import java.util.List;
import services.CustomerWS_Service;
import services.CustomerWS;
import services.Customer;

public class CustomerService {
    
    static CustomerWS_Service customerService = new CustomerWS_Service();
    static CustomerWS customerWS = customerService.getCustomerWSPort();
    
    public static List<Customer> getCustomers() {
        return customerWS.getAll();
    }

    public static int deleteClient(int id) {
        return customerWS.delete(id);
    }

    public static int addCustomer(Customer customer) {
        return customerWS.add(customer);
    }
    
    public static int editCustomer(Customer customer) {
        return customerWS.update(customer);
    }
}
