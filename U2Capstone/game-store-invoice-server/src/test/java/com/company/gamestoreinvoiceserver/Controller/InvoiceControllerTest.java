package com.company.gamestoreinvoiceserver.Controller;

import com.company.gamestoreinvoiceserver.Dao.InvoiceDao;
import com.company.gamestoreinvoiceserver.Dao.InvoiceItemDao;
import com.company.gamestoreinvoiceserver.ServiceLayer.InvoiceServiceLayer;
import com.company.gamestoreinvoiceserver.ViewModel.InvoiceViewModel;
import com.company.gamestoreinvoiceserver.model.InvoiceItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceDao invoiceDao;

    @MockBean
    private InvoiceItemDao invoiceItemDao;

    @MockBean
    private InvoiceServiceLayer service;

    @MockBean
    private DataSource dataSource;

    @Autowired
    private InvoiceController controller;

    @InjectMocks
    private InvoiceControllerTest invoiceControllerTest;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {

    }
    @Test
    public void shouldCreateInvoiceAndReturnInvoice() throws Exception {
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setCustomer_id(1);
        ivm.setPurchase_date(LocalDate.of(2019,8,5));

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice_id(1);
        invoiceItem.setInventory_id(1);
        invoiceItem.setQuantity(1);
        invoiceItem.setUnit_price(new BigDecimal("15.99"));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        List<InvoiceItem> iList = new ArrayList<>();
        iList.add(invoiceItem);

        ivm.setInvoiceItems(iList);
        String inputJson;
        try {
            inputJson = mapper.writeValueAsString(ivm);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON");
        }


        InvoiceViewModel outputIvm = new InvoiceViewModel();
        outputIvm.setInvoice_id(1);
        outputIvm.setCustomer_id(1);
        outputIvm.setPurchase_date(LocalDate.of(2019,8,5));

        InvoiceItem invoiceItem2 = new InvoiceItem();
        invoiceItem2.setInvoice_id(1);
        invoiceItem2.setInventory_id(1);
        invoiceItem2.setQuantity(1);
        invoiceItem2.setUnit_price(new BigDecimal("15.99"));
        invoiceItem2 = invoiceItemDao.createInvoiceItem(invoiceItem2);

        List<InvoiceItem> iList2 = new ArrayList<>();
        iList2.add(invoiceItem2);

        outputIvm.setInvoiceItems(iList2);

        String outputJson;
        try {
            outputJson = mapper.writeValueAsString(outputIvm);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON");
        }

        when(service.createInvoice(ivm)).thenReturn(outputIvm);
        //testing the controller using mockMvc
        try {
            this.mockMvc.perform(post("/invoice")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().json(outputJson));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getInvoiceThatDoesNotExistShouldReturn404() {
        int idForInvoiceThatDoesNotExist = 111;

        when(invoiceDao.getInvoice(idForInvoiceThatDoesNotExist)).thenReturn(null);

        try {
            this.mockMvc.perform(get("/invoice/" + idForInvoiceThatDoesNotExist))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void shouldGetAllInvoicesAndReturnListOfInvoices() throws Exception {
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setCustomer_id(1);
        ivm.setPurchase_date(LocalDate.of(2019,8,5));

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice_id(1);
        invoiceItem.setInventory_id(1);
        invoiceItem.setQuantity(1);
        invoiceItem.setUnit_price(new BigDecimal("15.99"));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        List<InvoiceItem> iList = new ArrayList<>();
        iList.add(invoiceItem);

        InvoiceViewModel ivm2 = new InvoiceViewModel();
        ivm2.setCustomer_id(1);
        ivm2.setPurchase_date(LocalDate.of(2019,8,5));

        InvoiceItem invoiceItem2 = new InvoiceItem();
        invoiceItem2.setInvoice_id(1);
        invoiceItem2.setInventory_id(1);
        invoiceItem2.setQuantity(1);
        invoiceItem2.setUnit_price(new BigDecimal("15.99"));
        invoiceItem2 = invoiceItemDao.createInvoiceItem(invoiceItem2);

        List<InvoiceItem> iList2 = new ArrayList<>();
        iList2.add(invoiceItem2);

        ivm2.setInvoiceItems(iList2);

        List<InvoiceViewModel> ivmList = new ArrayList<>();
        ivmList.add(ivm);
        ivmList.add(ivm2);

        String outputJson;
        try {
            outputJson = mapper.writeValueAsString(ivmList);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON");
        }

        when(service.getAllInvoice()).thenReturn(ivmList);

        this.mockMvc.perform(get("/invoice"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetInvoiceAndReturnAnInvoice() throws Exception{
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setInvoice_id(1);
        ivm.setCustomer_id(1);
        ivm.setPurchase_date(LocalDate.of(2019,8,5));

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInventory_id(1);
        invoiceItem.setQuantity(1);
        invoiceItem.setUnit_price(new BigDecimal("15.99"));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        List<InvoiceItem> iList = new ArrayList<>();
        iList.add(invoiceItem);

        String outputJson;
        try {
            outputJson = mapper.writeValueAsString(ivm);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON");
        }

        when(service.getInvoice(1)).thenReturn(ivm);

        try {
            this.mockMvc.perform(get("/invoice/" + ivm.getInvoice_id()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void shouldGetInvoiceByCustomerIdAndReturnAnInvoice() {
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setInvoice_id(1);
        ivm.setCustomer_id(1);
        ivm.setPurchase_date(LocalDate.of(2019,8,5));

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInventory_id(1);
        invoiceItem.setQuantity(1);
        invoiceItem.setUnit_price(new BigDecimal("15.99"));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        List<InvoiceItem> iList = new ArrayList<>();
        iList.add(invoiceItem);

        when(service.getInvoiceByCustomerId(1)).thenReturn(ivm);

        String outputJson;
        try  {
            outputJson = mapper.writeValueAsString(ivm);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON");
        }

        try {
            this.mockMvc.perform(get("/invoice/customer/1"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().json(outputJson));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateInvoiceIsOk() {
        InvoiceViewModel inputIvm = new InvoiceViewModel();
        inputIvm.setInvoice_id(1);
        inputIvm.setCustomer_id(1);
        inputIvm.setPurchase_date(LocalDate.of(2019,8,5));

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInventory_id(1);
        invoiceItem.setQuantity(1);
        invoiceItem.setUnit_price(new BigDecimal("15.99"));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        List<InvoiceItem> iList = new ArrayList<>();
        iList.add(invoiceItem);

        String inputJson;
        try {
            inputJson = mapper.writeValueAsString(inputIvm);
        } catch(JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON");
        }

        try {
            this.mockMvc.perform(MockMvcRequestBuilders.put("/invoice/" + inputIvm.getInvoice_id())
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().string(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteInvoiceIsOk() throws Exception {
        controller.deleteInvoice(1);
         verify(service, times(1)).deleteInvoice(1);

         this.mockMvc.perform(delete("/invoice/1"))
                 .andDo(print())
                 .andExpect(status().isOk())
                 .andExpect(content().string(""));
    }

    @Test
    public void shouldCreateInvoiceItemAndReturnInvoiceItem() throws Exception {
     InvoiceItem input = new InvoiceItem();
     input.setInvoice_id(1);
     input.setInventory_id(1);
     input.setQuantity(1);
     input.setUnit_price(new BigDecimal("15.99"));

     String inputJson = mapper.writeValueAsString(input);
        System.out.println(inputJson);

     InvoiceItem output = new InvoiceItem();
     output.setInvoice_item_id(1);
     output.setInvoice_id(1);
     output.setInventory_id(1);
     output.setQuantity(1);
     output.setUnit_price(new BigDecimal("15.99"));

     String outputJson = mapper.writeValueAsString(output);
        System.out.println(outputJson);

     when(service.createInvoiceItem(input)).thenReturn(output);

     this.mockMvc.perform(post("/invoice/invoiceItem")
                 .content(inputJson)
                 .contentType(MediaType.APPLICATION_JSON))
                 .andDo(print())
                 .andExpect(status().isCreated())
                 .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetInvoiceItemAndReturnInvoiceItem() throws Exception {
        InvoiceItem output = new InvoiceItem();
        output.setInvoice_item_id(1);
        output.setInvoice_id(1);
        output.setInventory_id(1);
        output.setQuantity(1);
        output.setUnit_price(new BigDecimal("15.99"));

        String outputJson = mapper.writeValueAsString(output);

        when(service.getInvoiceItem(1)).thenReturn(output);

        this.mockMvc.perform(get("/invoice/invoiceItem/" + output.getInvoice_item_id()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetAllInvoiceItemsAndReturnListOfInvoiceItem() throws Exception {
        InvoiceItem input1 = new InvoiceItem();
        input1.setInvoice_id(1);
        input1.setInventory_id(1);
        input1.setQuantity(1);
        input1.setUnit_price(new BigDecimal("15.99"));

        InvoiceItem input2 = new InvoiceItem();
        input2.setInvoice_item_id(1);
        input2.setInvoice_id(1);
        input2.setInventory_id(1);
        input2.setQuantity(1);
        input2.setUnit_price(new BigDecimal("15.99"));

        List<InvoiceItem> iList = new ArrayList<>();
        iList.add(input1);
        iList.add(input2);

        String outputJson = mapper.writeValueAsString(iList);

        when(service.getAllInvoiceItems()).thenReturn(iList);

        this.mockMvc.perform(get("/invoice/invoiceItem"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldUpdateInvoiceItemIsOk() throws Exception {
        InvoiceItem input = new InvoiceItem();
        input.setInvoice_item_id(1);
        input.setInvoice_id(1);
        input.setInventory_id(1);
        input.setQuantity(1);
        input.setUnit_price(new BigDecimal("15.99"));

        String inputJson = mapper.writeValueAsString(input);

        doNothing().when(service).updateInvoiceItem(any(InvoiceItem.class));

        this.mockMvc.perform(put("/invoice/invoiceItem/1")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteInvoiceItemIsOk() throws Exception {
        controller.deleteInvoiceItem(1);

        verify(service,times(1)).deleteInvoiceItem(1);

        this.mockMvc.perform(delete("/invoice/invoiceItem/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(""));
    }

    @After
    public void tearDown() throws Exception {
    }

}