package com.company.productservice.controller;

import com.company.productservice.dao.ProductDao;
import com.company.productservice.dto.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductWebServiceController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class ProductWebServiceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductDao productDao;

    Product product, product2, product3;

    private static ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        product = new Product();
        product2 = new Product();  // product2 is the input to a create object.
        product3 = new Product();

        product.setProduct_id(1);
        product.setProduct_name("PS4 Pro");
        product.setProduct_description("Standard Sony Playstation 4 console.");
        product.setList_price(new BigDecimal("349.99"));
        product.setUnit_cost(new BigDecimal("200.00"));

        product2.setProduct_name("PS4 Pro");
        product2.setProduct_description("Standard Sony Playstation 4 console.");
        product2.setList_price(new BigDecimal("349.99"));
        product2.setUnit_cost(new BigDecimal("200.00"));

        product3.setProduct_id(2);
        product3.setProduct_name("XBox-1");
        product3.setProduct_description("Standard Microsoft XBox console.");
        product3.setList_price(new BigDecimal("249.99"));
        product3.setUnit_cost(new BigDecimal("170.00"));

    }

    /**
     * This method converts any object into a json format.
     * @param object
     * @return JSON format of the object that handles the LocalDate to be YYYY-MM-dd
     * @throws Exception
     */

    private String asJsonString(final Object object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(object);
    }

    //ObjectdMapper mapper = new ObjectMapper();


    @Test
    public void createProduct() throws Exception {
        when(productDao.addProduct(product2)).thenReturn(product);

//        String input = asJsonString(product2);
//        String result = asJsonString(product);

//        String input = mapper.writeValueAsString(product2);
//        String result = mapper.writeValueAsString(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product2))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                //use the objectmapper output with the json method
                .andExpect(content().json(asJsonString(product)));

    }

    @Test
    public void deleteProduct() throws Exception {
        mockMvc.perform(delete("/product/"+1))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));

    }

    @Test
    public void getProductList() throws Exception {
        List<Product> allProduct = new ArrayList<>();
        allProduct.add(product);
        allProduct.add(product3);

        when(productDao.getAllProduct()).thenReturn(allProduct);

        mockMvc.perform(get("/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(allProduct)));

    }

    @Test
    public void getProduct() throws Exception{
        when(productDao.getProduct(1)).thenReturn(product);

        mockMvc.perform(get("/product/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(product)));
    }

    @Test
    public void updateProduct() throws Exception{
        mockMvc.perform(put("/product")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8) //UTF_8 is used to view request in the print below
                .content(asJsonString(product))) //write as JSON object
                .andDo(print()) //print the request
                .andExpect(status().isNoContent()) //expect status ok
                .andExpect(content().string(""));

    }
}