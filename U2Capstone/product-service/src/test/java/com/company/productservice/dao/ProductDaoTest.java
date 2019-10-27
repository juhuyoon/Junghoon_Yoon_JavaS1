package com.company.productservice.dao;

import com.company.productservice.dto.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductDaoTest {

    @Autowired
    ProductDao productDao;

    @Before
    public void setUp() throws Exception {
        // Clean up the test db
        List<Product> iList = productDao.getAllProduct();
        for (Product i : iList) {
            productDao.deleteProduct(i.getProduct_id());
        }
    }

    @Test
    public void addGetDeleteProduct() {
        Product product = new Product();
        product.setProduct_name("PS4 Pro");
        product.setProduct_description("Standard PS4 Playstation.");
        product.setList_price(new BigDecimal("349.99"));
        product.setUnit_cost(new BigDecimal("200.00"));

        product = productDao.addProduct(product);

        Product product1 = productDao.getProduct(product.getProduct_id());

        assertEquals(product1, product);

        productDao.deleteProduct(product.getProduct_id());

        product1 = productDao.getProduct(product.getProduct_id());

        assertNull(product1);

    }
    @Test
    public void getAllProduct() {
        Product product = new Product();
        product.setProduct_name("PS4 Pro");
        product.setProduct_description("Standard Sony Playstation 4 console.");
        product.setList_price(new BigDecimal("349.99"));
        product.setUnit_cost(new BigDecimal("200.00"));
        productDao.addProduct(product);

        product = new Product();
        product.setProduct_name("XBox-1");
        product.setProduct_description("Standard Microsoft XBox console.");
        product.setList_price(new BigDecimal("249.99"));
        product.setUnit_cost(new BigDecimal("170.00"));
        productDao.addProduct(product);

        List<Product> lList = productDao.getAllProduct();

        assertEquals(2, lList.size());

    }

    @Test
    public void updateProduct() {
        Product product = new Product();
        product.setProduct_name("PS4 Pro");
        product.setProduct_description("Standard Sony Playstation 4 console.");
        product.setList_price(new BigDecimal("349.99"));
        product.setUnit_cost(new BigDecimal("200.00"));
        productDao.addProduct(product);

        product.setList_price(new BigDecimal("299.99"));
        productDao.updateProduct(product);

        Product product1 = productDao.getProduct(product.getProduct_id());

        assertEquals(new BigDecimal("299.99"), product1.getList_price());
    }

}
