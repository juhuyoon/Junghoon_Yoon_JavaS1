package com.company.customerservice.service;

import com.company.customerservice.dao.CustomerDao;
import com.company.customerservice.model.Customer;
import com.company.customerservice.model.CustomerViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

    private CustomerDao customerDao;

    @Autowired
    public ServiceLayer(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public CustomerViewModel addCustomer(CustomerViewModel cvm){
        Customer customer = customerDao.createCustomer(convertViewModelToObject(cvm));
        cvm.setCustomer_id(customer.getCustomer_id());
        return cvm;
    }

    public CustomerViewModel findCustomer(int customer_id){
        return convertObjectToViewModel(customerDao.getCustomer(customer_id));
    }

    public List<CustomerViewModel> findAllCustomers(){
        List<CustomerViewModel> cvmList= new ArrayList<>();
        customerDao.getAllCustomers().stream().forEach(customer -> cvmList.add(convertObjectToViewModel(customer)));
        return cvmList;
    }

    public void updateCustomer(CustomerViewModel cvm){
        customerDao.updateCustomer(convertViewModelToObject(cvm));
    }

    public void removeCustomer(int customer_id){
        customerDao.deleteCustomer(customer_id);
    }

    private Customer convertViewModelToObject(CustomerViewModel cvm){
        Customer customer = new Customer(
                cvm.getFirst_name(),
                cvm.getLast_name(),
                cvm.getStreet(),
                cvm.getCity(),
                cvm.getZip(),
                cvm.getEmail(),
                cvm.getPhone()
        );
        if (cvm.getCustomer_id() > 0){
            customer.setCustomer_id(cvm.getCustomer_id());
        }
        return customer;
    }

    private CustomerViewModel convertObjectToViewModel (Customer customer){
        CustomerViewModel cvm = new CustomerViewModel(
                customer.getCustomer_id(),
                customer.getFirst_name(),
                customer.getLast_name(),
                customer.getStreet(),
                customer.getCity(),
                customer.getZip(),
                customer.getEmail(),
                customer.getPhone()
        );
        return cvm;
    }
}
