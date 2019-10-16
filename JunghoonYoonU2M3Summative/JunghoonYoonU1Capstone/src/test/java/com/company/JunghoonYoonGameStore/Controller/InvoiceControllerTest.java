package com.company.JunghoonYoonGameStore.Controller;

import com.company.JunghoonYoonGameStore.DAO.InvoiceDao;
import com.company.JunghoonYoonGameStore.DTO.Invoice;
import com.company.JunghoonYoonGameStore.ServiceLayer.ServiceLayer;
import com.company.JunghoonYoonGameStore.ViewModel.OrderViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceDao invoiceDao;

    @MockBean
    private ServiceLayer serviceLayer;


    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void addOrderViewModel() throws Exception {
        OrderViewModel ovm = new OrderViewModel();
        ovm.setName("Jung");
        ovm.setStreet("123 Kappa ST");
        ovm.setCity("Atlanta");
        ovm.setState("GA");
        ovm.setZipcode("30033");
        ovm.setItem_type("Games");
        ovm.setItem_id(1);
        ovm.setQuantity(10);
        ovm.setOrder_id(1);

        String inputJson = mapper.writeValueAsString(ovm);

        OrderViewModel ovm2 = new OrderViewModel();
        ovm2.setName("Jung");
        ovm2.setStreet("123 Kappa ST");
        ovm2.setCity("Atlanta");
        ovm2.setState("GA");
        ovm2.setZipcode("30033");
        ovm2.setItem_type("Games");
        ovm2.setItem_id(1);
        ovm2.setQuantity(10);
//        ovm2.setUnit_price(new BigDecimal("15.99"));
//        ovm2.setSubtotal(new BigDecimal("159.99"));
//        ovm2.setTax(new BigDecimal("0.07"));
//        ovm2.setProcessing_fee(new BigDecimal("15.99"));
//        ovm2.setTotal(new BigDecimal("500.00"));
//        ovm2.setInvoice_id(1);

        String outputJson = mapper.writeValueAsString(ovm2);

        when(serviceLayer.addOrder(ovm)).thenReturn(ovm2);

        this.mockMvc.perform(post("/Invoice/")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().json(outputJson));

    }

    @Test
    public void getInvoiceThatDoesNotExistReturns404() throws Exception {
        when(invoiceDao.getInvoice(100)).thenThrow(new IllegalArgumentException("Message must not be null or empty!"));

        this.mockMvc.perform(get("/Console/get/100"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getInvoice() throws Exception{
        Invoice invoice = new Invoice();
        invoice.setInvoice_id(1);
        invoice.setName("Jung");
        invoice.setState("123 Kappa ST");
        invoice.setCity("Atlanta");
        invoice.setState("GA");
        invoice.setZipcode("30033");
        invoice.setItem_type("Games");
        invoice.setItem_id(1);
        invoice.setUnit_price(new BigDecimal("15.99"));
        invoice.setQuantity(10);
        invoice.setSubtotal(new BigDecimal("159.99"));
        invoice.setTax(new BigDecimal("0.07"));
        invoice.setProcessing_fee(new BigDecimal("15.99"));
        invoice.setTotal(new BigDecimal("500.00"));
        invoice.setInvoice_id(1);

        Invoice returnVal = invoice;

        String outputJson = mapper.writeValueAsString(invoice);

        when(invoiceDao.getInvoice(1)).thenReturn(returnVal);

        this.mockMvc.perform(get("/Invoice/get/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));
    }

    @Test
    public void getAllInvoice() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setInvoice_id(1);
        invoice.setName("Jung");
        invoice.setState("123 Kappa ST");
        invoice.setCity("Atlanta");
        invoice.setState("GA");
        invoice.setZipcode("30033");
        invoice.setItem_type("Games");
        invoice.setItem_id(1);
        invoice.setUnit_price(new BigDecimal("15.99"));
        invoice.setQuantity(10);
        invoice.setSubtotal(new BigDecimal("159.99"));
        invoice.setTax(new BigDecimal("0.07"));
        invoice.setProcessing_fee(new BigDecimal("15.99"));
        invoice.setTotal(new BigDecimal("500.00"));
        invoice.setInvoice_id(1);

        Invoice invoice2 = new Invoice();
        invoice2.setInvoice_id(1);
        invoice2.setName("Jung");
        invoice2.setState("123 Kappa ST");
        invoice2.setCity("Atlanta");
        invoice2.setState("GA");
        invoice2.setZipcode("30033");
        invoice2.setItem_type("Games");
        invoice2.setItem_id(1);
        invoice2.setUnit_price(new BigDecimal("15.99"));
        invoice2.setQuantity(10);
        invoice2.setSubtotal(new BigDecimal("159.99"));
        invoice2.setTax(new BigDecimal("0.07"));
        invoice2.setProcessing_fee(new BigDecimal("15.99"));
        invoice2.setTotal(new BigDecimal("500.00"));
        invoice2.setInvoice_id(2);

        List<Invoice> iList = new ArrayList<>();
        iList.add(invoice);
        iList.add(invoice2);

        when(invoiceDao.getAllInvoice()).thenReturn(iList);

        List<Invoice> listChecker = new ArrayList<>();
        listChecker.addAll(iList);

        String outputJson = mapper.writeValueAsString(listChecker);

        this.mockMvc.perform(get("/Invoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
}