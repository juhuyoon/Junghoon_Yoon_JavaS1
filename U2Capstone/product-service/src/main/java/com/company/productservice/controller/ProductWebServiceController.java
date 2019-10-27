package com.company.productservice.controller;

import com.company.productservice.dao.ProductDao;
import com.company.productservice.dto.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductWebServiceController {
    // All DAOs for each model is created with ServiceLayer's contructor.
    private final ProductDao dao;

    public ProductWebServiceController(ProductDao dao) {
        this.dao = dao;
    }

    //@PostMapping
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody @Valid Product product) {
        return dao.addProduct(product);

    }

    //@DeleteMapping(path = "/{product_id}")
    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int product_id) {
        dao.deleteProduct(product_id);
    }

    //@GetMapping
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Product> getProductList() {
        return dao.getAllProduct();

    }

    //@GetMapping(path = "/{id}")
    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Product getProduct(@PathVariable int id) throws Exception {
        Product product = dao.getProduct(id);
        if (product == null) {
            throw new Exception("Product not found.");
        }
        return product;

    }

    //@PutMapping
    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody @Valid Product product) {
        dao.updateProduct(product);

    }

}
