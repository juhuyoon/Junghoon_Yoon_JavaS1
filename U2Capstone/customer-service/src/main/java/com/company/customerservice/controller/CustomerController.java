package com.company.customerservice.controller;

import com.company.customerservice.model.CustomerViewModel;
import com.company.customerservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private ServiceLayer serviceLayer;

    //createMethod()
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerViewModel createCustomer(@Valid @RequestBody CustomerViewModel cvm){
        return serviceLayer.addCustomer(cvm);
    }

    //getMethod()
    @GetMapping(value = "/{customer_id}")
    @ResponseStatus(HttpStatus.FOUND)
    public CustomerViewModel getCustomer(@PathVariable int customer_id){
        return serviceLayer.findCustomer(customer_id);
    }


    //getAllMethod()
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<CustomerViewModel> getAllCustomers(){
        return serviceLayer.findAllCustomers();
    }

    //updateMethod()
    @PutMapping(value = "/{customer_id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@Valid @RequestBody CustomerViewModel cvm){
        serviceLayer.updateCustomer(cvm);
    }

    //deleteMethod()
    @DeleteMapping(value = "/{customer_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable int customer_id){
        serviceLayer.removeCustomer(customer_id);
    }
}
