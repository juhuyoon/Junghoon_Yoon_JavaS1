package com.company.JunghoonYoonGameStore.Controller;

import com.company.JunghoonYoonGameStore.DAO.TshirtDao;
import com.company.JunghoonYoonGameStore.DTO.TShirt;
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
@WebMvcTest(TshirtController.class)
public class TshirtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TshirtDao tshirtDao;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getTshirt() throws Exception {
        TShirt tShirt = new TShirt();
        tShirt.setT_shirt_id(1);
        tShirt.setSize("M");
        tShirt.setColor("Orange");
        tShirt.setDescription("Orange and casual");
        tShirt.setPrice(new BigDecimal("15.99"));
        tShirt.setQuantity(5);
        tShirt.setT_shirt_id(1);

        TShirt returnVal = tShirt;

        String outputJson = mapper.writeValueAsString(tShirt);

        when(tshirtDao.getTShirt(1)).thenReturn(returnVal);

        this.mockMvc.perform(get("/TShirt/get/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void getTShirtThatDoesNotExistReturns404() throws Exception {
        when(tshirtDao.getTShirt(100)).thenThrow(new IllegalArgumentException("Message must not be null or empty!"));

        this.mockMvc.perform(get("/Console/get/100"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllTShirts() throws Exception{
        TShirt tShirt = new TShirt();
        tShirt.setT_shirt_id(1);
        tShirt.setSize("M");
        tShirt.setColor("Orange");
        tShirt.setDescription("Orange and casual");
        tShirt.setPrice(new BigDecimal("15.99"));
        tShirt.setQuantity(5);
        tShirt.setT_shirt_id(1);

        TShirt tShirt2 = new TShirt();
        tShirt2.setSize("M");
        tShirt2.setColor("Orange");
        tShirt2.setDescription("Orange and casual");
        tShirt2.setPrice(new BigDecimal("15.99"));
        tShirt2.setQuantity(5);
        tShirt2.setT_shirt_id(2);

        List<TShirt> tList = new ArrayList<>();
        tList.add(tShirt);
        tList.add(tShirt2);

        when(tshirtDao.getAllTShirts()).thenReturn(tList);

        List<TShirt> listChecker = new ArrayList<>();
        listChecker.addAll(tList);

        String outputJson = mapper.writeValueAsString(listChecker);

        this.mockMvc.perform(get("/TShirt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void addTShirt() throws Exception {
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("Orange");
        tShirt.setDescription("Orange and casual");
        tShirt.setPrice(new BigDecimal("15.99"));
        tShirt.setQuantity(5);


        String inputJson = mapper.writeValueAsString(tShirt);

        TShirt tShirt2 = new TShirt();
        tShirt2.setSize("M");
        tShirt2.setColor("Orange");
        tShirt2.setDescription("Orange and casual");
        tShirt2.setPrice(new BigDecimal("15.99"));
        tShirt2.setQuantity(5);

        String outputJson = mapper.writeValueAsString(tShirt2);

        when(tshirtDao.addTShirt(tShirt)).thenReturn(tShirt2);

        this.mockMvc.perform(post("/TShirt")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().json(outputJson));


    }

    @Test
    public void updateTShirt() throws Exception {
        TShirt tShirt = new TShirt();
        tShirt.setT_shirt_id(1);
        tShirt.setSize("M");
        tShirt.setColor("Orange");
        tShirt.setDescription("Orange and casual");
        tShirt.setPrice(new BigDecimal("15.99"));
        tShirt.setQuantity(5);
        tShirt.setT_shirt_id(1);

        String inputJson = mapper.writeValueAsString(tShirt);
        String outputJson = mapper.writeValueAsString(tShirt);

        this.mockMvc.perform(put("/TShirt/update/")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isAccepted());
    }

    @Test
    public void deleteTShirt() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/TShirt/delete/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string("T Shirt deleted"));
    }

    @Test
    public void getTShirtBySize() throws Exception {
        TShirt tShirt = new TShirt();
        tShirt.setT_shirt_id(1);
        tShirt.setSize("M");
        tShirt.setColor("Orange");
        tShirt.setDescription("Orange and casual");
        tShirt.setPrice(new BigDecimal("15.99"));
        tShirt.setQuantity(5);
        tShirt.setT_shirt_id(1);

        List<TShirt> returnVal = tshirtDao.getTShirtsBySize(tShirt.getSize());

        String outputJson = mapper.writeValueAsString(returnVal);

        when(tshirtDao.getTShirtsBySize("M")).thenReturn(returnVal);
        this.mockMvc.perform(get("/TShirt/size/M"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));
    }

    @Test
    public void getTShirtByColor() throws Exception {
        TShirt tShirt = new TShirt();
        tShirt.setT_shirt_id(1);
        tShirt.setSize("M");
        tShirt.setColor("Orange");
        tShirt.setDescription("Orange and casual");
        tShirt.setPrice(new BigDecimal("15.99"));
        tShirt.setQuantity(5);
        tShirt.setT_shirt_id(1);

        List<TShirt> returnVal = tshirtDao.getTShirtsBySize(tShirt.getSize());

        String outputJson = mapper.writeValueAsString(returnVal);

        when(tshirtDao.getTShirtsByColor("Orange")).thenReturn(returnVal);
        this.mockMvc.perform(get("/TShirt/color/Orange"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));
    }
}