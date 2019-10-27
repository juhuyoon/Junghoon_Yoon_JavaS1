package com.company.adminapi.util.feign;

import com.company.adminapi.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {
    //@PostMapping
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public Product createProduct(@RequestBody @Valid Product product);

    //@DeleteMapping(path = "/{product_id}")
    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable int product_id);

    //@GetMapping
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public List<Product> getProductList();

    //@GetMapping(path = "/{id}")
    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable int id);

    //@PutMapping
    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    public void updateProduct(@RequestBody @Valid Product product);



}
