package test;

import java.util.List;
import services.Customer;
import services.CustomerWS;
import services.CustomerWS_Service;

public class Main {
    static CustomerWS_Service customerService = new CustomerWS_Service();
    static CustomerWS customerWS = customerService.getCustomerWSPort();
    
    public static void main(String[] args) {
        getAll();
        //addCustomer("Abdelkader", "Meghazi", "a_meghazi@houarizegai.me");
        //updateCustomer(23, "AEK", "Meghazzi", "a_meghazi@esi.dz");
        //deleteCustomer(23);
    }
    
    private static void getAll() {
        List<Customer> customers = customerWS.getAll();
        for(Customer customer : customers)
            System.out.println("Customer {" + "id=" + customer.getId() + ", firstName=" + customer.getFirstName() + ", lastName=" + customer.getLastName() + ", email=" + customer.getEmail() + '}');
    }
    
    private static void addCustomer(String firstName, String lastName, String email) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        
        customerWS.add(customer);
    }
    
    private static void updateCustomer(int id, String firstName, String lastName, String email) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(firstName);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        
        customerWS.update(customer);
    }
    
    private static void deleteCustomer(int id) {
        customerWS.delete(id);
    }
}
