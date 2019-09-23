package com.company.JunghoonYoonU1Capstone.Controller;

import com.company.JunghoonYoonU1Capstone.DAO.ConsoleDao;
import com.company.JunghoonYoonU1Capstone.DTO.Console;
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
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsoleDao consoleDao;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getConsole() throws Exception {
        Console console = new Console();
        console.setModel("XBOX");
        console.setManufacturer("Windows");
        console.setMemory_amount("500");
        console.setProcessor("INTEL");
        console.setPrice(new BigDecimal("150.99"));
        console.setQuantity(15);
        console.setConsole_id(1);

       Console returnVal = console;

        String outputJson = mapper.writeValueAsString(console);

        when(consoleDao.getConsole(1)).thenReturn(returnVal);
        this.mockMvc.perform(get("/Console/get/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));
    }

    @Test
    public void getConsoleThatDoesNotExistReturns404() throws Exception {
        when(consoleDao.getConsole(100)).thenThrow(new IllegalArgumentException("Message must not be null or empty!"));

        this.mockMvc.perform(get("/Console/get/100"))
                    .andDo(print())
                    .andExpect(status().isNotFound());
    }

    @Test
    public void getAllConsoles() throws Exception {
        Console console = new Console();
        console.setModel("XBOX");
        console.setManufacturer("Windows");
        console.setMemory_amount("500");
        console.setProcessor("INTEL");
        console.setPrice(new BigDecimal("150.99"));
        console.setQuantity(15);
        console.setConsole_id(1);

        Console console2 = new Console();
        console2.setModel("PS4");
        console2.setManufacturer("SONY");
        console2.setMemory_amount("155");
        console2.setProcessor("XO");
        console2.setPrice(new BigDecimal("300.99"));
        console2.setQuantity(5);
        console2.setConsole_id(2);

        List<Console> cList = new ArrayList<>();
        cList.add(console);
        cList.add(console2);

        when(consoleDao.getAllConsoles()).thenReturn(cList);

        List<Console> listChecker = new ArrayList<>();
        listChecker.addAll(cList);

        String outputJson = mapper.writeValueAsString(listChecker);

        this.mockMvc.perform(get("/Console"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));
    }

    @Test
    public void addConsole() throws Exception{
        Console console = new Console();
        console.setModel("XBOX");
        console.setManufacturer("Windows");
        console.setMemory_amount("500");
        console.setPrice(new BigDecimal("150.99"));
        console.setProcessor("INTEL");
        console.setQuantity(10);

        String inputJson = mapper.writeValueAsString(console);

        Console console2 = new Console();
        console2.setModel("XBOX");
        console2.setManufacturer("Windows");
        console2.setMemory_amount("500");
        console2.setPrice(new BigDecimal("150.99"));
        console2.setProcessor("INTEL");
        console2.setQuantity(10);

        String outputJson = mapper.writeValueAsString(console2);

        when(consoleDao.addConsole(console)).thenReturn(console2);

        this.mockMvc.perform(post("/Console")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().json(outputJson));
    }

    @Test
    public void updateConsole() throws Exception {
        Console console = new Console();
        console.setModel("XBOX");
        console.setManufacturer("Windows");
        console.setMemory_amount("500");
        console.setProcessor("INTEL");
        console.setPrice(new BigDecimal("150.99"));
        console.setQuantity(15);
        console.setConsole_id(1);

        String inputJson = mapper.writeValueAsString(console);
        String outputJson = mapper.writeValueAsString(console);

        this.mockMvc.perform(put("/Console/update/")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print()).andExpect(status().isAccepted());
    }

    @Test
    public void deleteConsole() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/Console/delete/1"))
                    .andDo(print())
                .andExpect(status().isOk())
                    .andExpect(content().string("Console has been deleted!"));

    }

    @Test
    public void getConsolesByManufacturer() throws Exception {
        Console console = new Console();
        console.setModel("XBOX");
        console.setManufacturer("Windows");
        console.setMemory_amount("500");
        console.setProcessor("INTEL");
        console.setPrice(new BigDecimal("150.99"));
        console.setQuantity(15);
        console.setConsole_id(1);


        List<Console> returnVal = consoleDao.getConsolesByManufacturer(console.getManufacturer());

        String outputJson = mapper.writeValueAsString(returnVal);

        when(consoleDao.getConsolesByManufacturer("Windows")).thenReturn(returnVal);
        this.mockMvc.perform(get("/Console/manufacturer/Windows"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
}