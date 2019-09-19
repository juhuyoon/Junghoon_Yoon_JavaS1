package com.company.JunghoonYoonU1Capstone.DAO;

import com.company.JunghoonYoonU1Capstone.DTO.Processing_Fee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProcessingFees_DaoTest {

    @Autowired
    ProcessingFeesDao processingFees_dao;
    Processing_Fee pFees, pFees2;

    @Before
    public void setUp() throws Exception {
        processingFees_dao.getAllProcessingFees().forEach(p -> {
            processingFees_dao.deleteProcessingFees(p.getProduct_type());
        });

        pFees = new Processing_Fee();
        pFees.setProduct_type("Game");
        pFees.setFee(new BigDecimal("15.99"));

        pFees2 = new Processing_Fee();
        pFees2.setProduct_type("Console");
        pFees2.setFee(new BigDecimal("10.00"));

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getProcessingFees() {
        pFees = processingFees_dao.addProcessingFees(pFees);
        Processing_Fee pTest = processingFees_dao.getProcessingFees(pFees.getProduct_type());

        assertEquals(pTest, pFees);
    }

    @Test
    public void getAllProcessingFees() {
        processingFees_dao.addProcessingFees(pFees);
        processingFees_dao.addProcessingFees(pFees2);

        List<Processing_Fee> pList = processingFees_dao.getAllProcessingFees();
        assertEquals(2, pList.size());
    }

    @Test
    public void addProcessingFees() {
        pFees = processingFees_dao.addProcessingFees(pFees);
        assertEquals(1, processingFees_dao.getAllProcessingFees().size());
    }

    @Test
    public void updateProcessingFees() {
        processingFees_dao.addProcessingFees(pFees);
        pFees.setFee(new BigDecimal("5.55"));
        Processing_Fee pTest = processingFees_dao.getProcessingFees(pFees.getProduct_type());

        assertNotEquals(pTest, pFees);
    }

    @Test
    public void deleteProcessingFees() {
        processingFees_dao.addProcessingFees(pFees);
        processingFees_dao.deleteProcessingFees(pFees.getProduct_type());

        Processing_Fee pTest = processingFees_dao.getProcessingFees(pFees.getProduct_type());

        assertNull(pTest);
    }
}