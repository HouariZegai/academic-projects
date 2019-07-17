package com.houarizegai.customerclient.service;

import java.util.LinkedList;
import java.util.List;
import com.houarizegai.customerclient.model.CustomerModel;
import services.Customer;
import services.CustomerWS_Service;
import services.CustomerWS;

public class CustomerService {
    
    static CustomerWS_Service customerService = new CustomerWS_Service();
    static CustomerWS customerWS = customerService.getCustomerWSPort();
    
    public static List<CustomerModel> getCustomers() {
        List<CustomerModel> customerModelList = new LinkedList<>();
        
        for(Customer customer : customerWS.getAll()) {
            customerModelList.add(new CustomerModel(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail()));
        }
        
        return customerModelList;
    }

    public static int deleteClient(int id) {
        return customerWS.delete(id);
    }

    public static int addCustomer(CustomerModel customerModel) {
        Customer customer = new Customer();
        customer.setId(customerModel.getId());
        customer.setFirstName(customerModel.getFirstName());
        customer.setLastName(customerModel.getLastName());
        customer.setEmail(customerModel.getEmail());
        
        return customerWS.add(customer);
    }
    
    public static int editCustomer(CustomerModel customerModel) {
        Customer customer = new Customer();
        customer.setId(customerModel.getId());
        customer.setFirstName(customerModel.getFirstName());
        customer.setLastName(customerModel.getLastName());
        customer.setEmail(customerModel.getEmail());
        
        return customerWS.update(customer);
    }
}
