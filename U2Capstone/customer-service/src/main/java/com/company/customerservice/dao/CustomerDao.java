package com.company.customerservice.dao;

import com.company.customerservice.model.Customer;
import com.company.customerservice.model.CustomerViewModel;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CustomerDao {
    //create
    Customer createCustomer(Customer customer);

    //get one
    Customer getCustomer(int customer_id);

    //get all
    List<Customer> getAllCustomers();

    //update
    void updateCustomer(Customer customer);

    //delete
    void deleteCustomer(int customer_id);
}
