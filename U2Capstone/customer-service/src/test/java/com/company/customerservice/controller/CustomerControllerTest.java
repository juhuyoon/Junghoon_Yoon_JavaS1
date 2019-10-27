package com.company.customerservice.controller;

import com.company.customerservice.dao.CustomerDao;
import com.company.customerservice.model.CustomerViewModel;
import com.company.customerservice.service.ServiceLayer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerDao customerDao;

    @MockBean
    private ServiceLayer serviceLayer;

    @MockBean
    private DataSource dataSource;

    @Autowired
    private CustomerController customerController;

    @InjectMocks
    private CustomerControllerTest customerControllerTest;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createCustomer() throws Exception {
         CustomerViewModel cvmNew = new CustomerViewModel(
                "Lorem",
                "Ipsum",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );
         String inputJson = mapper.writeValueAsString(cvmNew);

         CustomerViewModel cvmSaved = new CustomerViewModel(
                1,
                "Lorem",
                "Ipsum",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );
         String outputJson = mapper.writeValueAsString(cvmSaved);

         when(serviceLayer.addCustomer(cvmNew)).thenReturn(cvmSaved);

         this.mockMvc.perform(post("/customer")
                 .content(inputJson)
                 .contentType(MediaType.APPLICATION_JSON))
                 .andDo(print())
                 .andExpect(status().isCreated())
                 .andExpect(content().json(outputJson)
         );
    }

    @Test
    public void getCustomer() throws Exception {
        CustomerViewModel cvmSaved = new CustomerViewModel(
                1,
                "Lorem",
                "Ipsum",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );
        String outputJson = mapper.writeValueAsString(cvmSaved);

        when(serviceLayer.findCustomer(1)).thenReturn(cvmSaved);

        this.mockMvc.perform(get("/customer/" + cvmSaved.getCustomer_id()))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().json(outputJson)
        );
    }

    @Test
    public void getAllCustomers() throws Exception {
        CustomerViewModel cvmSaved = new CustomerViewModel(
                1,
                "Lorem",
                "Ipsum",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );

        CustomerViewModel cvmSaved2 = new CustomerViewModel(
                2,
                "Dolor",
                "Sit",
                "123 Dolor Sit Ln.",
                "Atlanta",
                "30301",
                "dolorsit@email.com",
                "404-987-6543"
        );

        List<CustomerViewModel> cvmExpected = new ArrayList<>();
        cvmExpected.add(cvmSaved);
        cvmExpected.add(cvmSaved2);
        String outputJson = mapper.writeValueAsString(cvmExpected);

        when(serviceLayer.findAllCustomers()).thenReturn(cvmExpected);

        this.mockMvc.perform(get("/customer"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().json(outputJson)
        );
    }

    @Test
    public void updateCustomer() throws Exception {
        CustomerViewModel cvmSaved = new CustomerViewModel(
                1,
                "John",
                "Smith",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );

        String inputJson = mapper.writeValueAsString(cvmSaved);

        doNothing().when(serviceLayer).updateCustomer(any(CustomerViewModel.class));

        this.mockMvc.perform(put("/customer/1")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()
        );
    }

    @Test
    public void deleteCustomer() throws Exception {

        customerController.deleteCustomer(1);

        verify(serviceLayer, times(1)).removeCustomer(1);

        this.mockMvc.perform(delete("/customer/" + 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""))
        ;
    }
}