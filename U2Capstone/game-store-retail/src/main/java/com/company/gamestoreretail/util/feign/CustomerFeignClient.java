package com.company.gamestoreretail.util.feign;

import com.company.gamestoreretail.model.CustomerViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("customer-service")
@RequestMapping(value="/customer")
public interface CustomerFeignClient {
    @GetMapping(value = "/{customer_id}")
    public CustomerViewModel getCustomer(@PathVariable int customer_id);

    @PostMapping
    public CustomerViewModel createCustomer(@RequestBody CustomerViewModel cvm);

    @GetMapping
    public List<CustomerViewModel> getAllCustomers();

    @PutMapping(value="/{customer_id}")
    public void updateCustomer(@RequestBody CustomerViewModel cvm);

    @DeleteMapping(value = "/{customer_id}")
    public void deleteCustomer(@PathVariable int customer_id);
}
