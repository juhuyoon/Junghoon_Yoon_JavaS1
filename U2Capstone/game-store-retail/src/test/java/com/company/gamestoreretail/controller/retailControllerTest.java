package com.company.gamestoreretail.controller;

import com.company.gamestoreretail.model.InvoiceItem;
import com.company.gamestoreretail.model.InvoiceViewModel;
import com.company.gamestoreretail.servicelayer.RetailServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(retailController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class retailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RetailServiceLayer service;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @MockBean
    private DataSource dataSource;

    private ObjectMapper mapper = new ObjectMapper();


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldSubmitInvoiceAndReturnInvoice() throws Exception {
        InvoiceItem inputItem = new InvoiceItem(1, 1, 1, 5, new BigDecimal("15.99"));
        InvoiceItem inputItem2 = new InvoiceItem(2, 2, 2, 5, new BigDecimal("5.99"));

        List<InvoiceItem> inputInvoiceItems = new ArrayList<>();
        inputInvoiceItems.add(inputItem);
        inputInvoiceItems.add(inputItem2);


        InvoiceViewModel input = new InvoiceViewModel();
        input.setCustomer_id(1);
        input.setPurchase_date(LocalDate.of(2015,8,5));
        input.setInvoiceItems(inputInvoiceItems);

        String inputJson = mapper.writeValueAsString(input);

        InvoiceViewModel output = new InvoiceViewModel();
        output.setInvoice_id(1);
        output.setCustomer_id(1);
        output.setPurchase_date(LocalDate.of(2015,8,5));
        output.setPoints(50);
        output.setInvoiceItems(inputInvoiceItems);

        String outputJson = mapper.writeValueAsString(output);

        doReturn(output).when(service).submitInvoice(output);

        this.mockMvc.perform(post("/invoices")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldSubmitBadInvoiceAndReturn400() throws Exception {
        InvoiceItem inputItem = new InvoiceItem(1, 1, 1, 5, new BigDecimal("15.99"));
        InvoiceItem inputItem2 = new InvoiceItem(2, 2, 2, 5, new BigDecimal("5.99"));

        List<InvoiceItem> inputInvoiceItems = new ArrayList<>();
        inputInvoiceItems.add(inputItem);
        inputInvoiceItems.add(inputItem2);


        InvoiceViewModel input = new InvoiceViewModel();
        input.setCustomer_id(1);
        input.setPurchase_date(LocalDate.of(2015,8,5));
        input.setInvoiceItems(inputInvoiceItems);

        String inputJson = mapper.writeValueAsString(input);

        InvoiceViewModel output = new InvoiceViewModel();
        output.setInvoice_id(1);
        output.setCustomer_id(1);
        output.setPurchase_date(LocalDate.of(2015,8,5));
        output.setPoints(50);
        output.setInvoiceItems(inputInvoiceItems);

        String outputJson = mapper.writeValueAsString(output);

        doReturn(output).when(service).submitInvoice(output);

        this.mockMvc.perform(post("/invoices")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void shouldGetInvoiceByCustomerId() throws Exception {
        InvoiceItem inputItem = new InvoiceItem(1, 1, 1, 5, new BigDecimal("15.99"));
        InvoiceItem inputItem2 = new InvoiceItem(2, 2, 2, 5, new BigDecimal("5.99"));

        List<InvoiceItem> inputInvoiceItems = new ArrayList<>();
        inputInvoiceItems.add(inputItem);
        inputInvoiceItems.add(inputItem2);

        InvoiceViewModel output = new InvoiceViewModel();
        output.setInvoice_id(1);
        output.setCustomer_id(1);
        output.setPurchase_date(LocalDate.of(2015,8,5));
        output.setPoints(50);
        output.setInvoiceItems(inputInvoiceItems);

        String outputJson = mapper.writeValueAsString(output);

        this.mockMvc.perform(get("/invoices/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetInvoiceByCustomerIdAndReturn404() throws Exception {
        int customerIdThatDoesNotExist = 111;
        when(service.getInvoiceByCustomerId(customerIdThatDoesNotExist)).thenReturn(null);

        this.mockMvc.perform(get("/invoices/111"))
                    .andDo(print())
                    .andExpect(status().isNotFound());
    }
}