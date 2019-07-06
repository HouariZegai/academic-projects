package services;

import dao.CustomerDao;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import model.Customer;

@WebService(serviceName = "CustomerWS")
public class CustomerWS {
    
    @WebMethod(operationName = "getAll")
    public List<Customer> getCustomers() {
        return CustomerDao.getCustomers();
    }
    
    @WebMethod(operationName = "delete")
    public int deleteClient(@WebParam(name = "id") int id) {
        return CustomerDao.deleteClient(id);
    }

    @WebMethod(operationName = "add")
    public int addCustomer(@WebParam(name = "customer") Customer customer) {
        return CustomerDao.addCustomer(customer);
    }
    
    @WebMethod(operationName = "update")
    public int editCustomer(@WebParam(name = "customer") Customer customer) {
        return CustomerDao.editCustomer(customer);
    }
}
