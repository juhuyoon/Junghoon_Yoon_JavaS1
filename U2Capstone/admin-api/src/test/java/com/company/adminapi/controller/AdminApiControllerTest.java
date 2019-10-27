package com.company.adminapi.controller;

import com.company.adminapi.dto.Inventory;
import com.company.adminapi.util.feign.InventoryClient;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminApiController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class AdminApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    InventoryClient inventoryClient;

    Inventory inventory, inventory2, inventory3;

    private static ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        inventory = new Inventory();
        inventory2 = new Inventory();  // inventory2 is the input to a create object.
        inventory3 = new Inventory();

        inventory.setInventory_id(1);
        inventory.setProduct_id(1);
        inventory.setQuantity(10);

        inventory2.setProduct_id(1);
        inventory2.setQuantity(10);

        inventory3.setInventory_id(2);
        inventory3.setProduct_id(2);
        inventory3.setQuantity(20);

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
    public void getInventory() throws Exception{
        when(inventoryClient.getInventory(1)).thenReturn(inventory);

        mockMvc.perform(get("/inventory/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(inventory)));
    }

    @Test
    public void createInventory() throws Exception {
        when(inventoryClient.createInventory(inventory2)).thenReturn(inventory);

//        String input = asJsonString(inventory2);
//        String result = asJsonString(inventory);

        String input = mapper.writeValueAsString(inventory2);
        String result = mapper.writeValueAsString(inventory);

        mockMvc.perform(MockMvcRequestBuilders.post("/inventory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(inventory2))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                //use the objectmapper output with the json method
                .andExpect(content().json(asJsonString(inventory)));

    }


    @Test
    public void updateInventory() throws Exception{
        mockMvc.perform(put("/inventory")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8) //UTF_8 is used to view request in the print below
                .content(asJsonString(inventory))) //write as JSON object
                .andDo(print()) //print the request
                .andExpect(status().isNoContent()) //expect status ok
                .andExpect(content().string(""));
    }

    @Test
    public void deleteInventory() throws Exception{
        mockMvc.perform(delete("/inventory/"+1))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));

    }

    @Test
    public void getAllInventory() throws Exception {
        List<Inventory> allInventory = new ArrayList<>();
        allInventory.add(inventory);
        allInventory.add(inventory3);

        when(inventoryClient.getInventoryList()).thenReturn(allInventory);

        mockMvc.perform(get("/inventory"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(allInventory)));

    }
}