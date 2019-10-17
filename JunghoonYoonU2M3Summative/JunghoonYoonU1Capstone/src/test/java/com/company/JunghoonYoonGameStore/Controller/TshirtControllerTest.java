package com.company.JunghoonYoonGameStore.Controller;

import com.company.JunghoonYoonGameStore.DAO.TshirtDao;
import com.company.JunghoonYoonGameStore.DTO.TShirt;
import com.company.JunghoonYoonGameStore.security.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TshirtController.class)
public class TshirtControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SecurityConfig securityConfig;

    @MockBean
    private TshirtDao tshirtDao;

    @MockBean
    private DataSource dataSource;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders
                    .webAppContextSetup(context)
                    .apply(springSecurity())
                    .build();
    }

    @Test
    public void shouldGetTshirt() throws Exception {
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
    public void shouldGetTShirtThatDoesNotExistReturns404() throws Exception {
        when(tshirtDao.getTShirt(100)).thenThrow(new IllegalArgumentException("Message must not be null or empty!"));

        this.mockMvc.perform(get("/Console/get/100"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetAllTShirts() throws Exception{
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
    @WithMockUser(username = "adminUser", roles={"STAFF", "MANAGER", "ADMIN"})
    public void shouldAddTShirt() throws Exception {
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
                    .with(csrf().asHeader())
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().json(outputJson));


    }

    @Test
    @WithMockUser(username = "adminUser", roles={"STAFF", "MANAGER", "ADMIN"})
    public void shouldUpdateTShirt() throws Exception {
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
                    .with(csrf().asHeader())
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isAccepted());
    }

    @Test
    @WithMockUser(username = "adminUser", roles={"STAFF", "MANAGER", "ADMIN"})
    public void shouldDeleteTShirt() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/TShirt/delete/1")
                    .with(csrf().asHeader()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string("T Shirt deleted"));
    }

    @Test
    public void shouldGetTShirtBySize() throws Exception {
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
    public void shouldGetTShirtByColor() throws Exception {
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