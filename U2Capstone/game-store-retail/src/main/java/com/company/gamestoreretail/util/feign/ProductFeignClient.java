package com.company.gamestoreretail.util.feign;

import com.company.gamestoreretail.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@FeignClient("product-service")
public interface ProductFeignClient {

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable int id);

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public List<Product> getProductList();

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public Product createProduct(@RequestBody @Valid Product product);

    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    public void updateProduct(@RequestBody @Valid Product product);

    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable int product_id);
}
