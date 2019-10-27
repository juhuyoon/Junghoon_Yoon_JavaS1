package com.company.adminapi.util.feign;

import com.company.adminapi.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerClient {

    //@PostMapping
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public Customer createCustomer(@RequestBody @Valid Customer customer);

    //@DeleteMapping(path = "/{customer_id}")
    @RequestMapping(value = "/customer/{customer_id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable int customer_id);

    //@GetMapping
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public List<Customer> getCustomerList();

    //@GetMapping(path = "/{id}")
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable int id);

    //@PutMapping
    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public void updateCustomer(@RequestBody @Valid Customer customer);


}
