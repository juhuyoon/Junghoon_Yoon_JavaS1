package com.company.JunghoonYoonGameStore.DAO;

import com.company.JunghoonYoonGameStore.DTO.Sales_Tax_Rate;
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
public class SalesTaxRate_DaoTest {

    @Autowired
    private SalesTaxRate_Dao salesTaxRate_dao;

    private Sales_Tax_Rate sales_tax_rate, sales_tax_rate2;

    @Before
    public void setUp() throws Exception {
        salesTaxRate_dao.getAllSalesTaxRate().forEach(s -> {
            salesTaxRate_dao.deleteSalesTaxRate(s.getState());
        });

        sales_tax_rate = new Sales_Tax_Rate();
        sales_tax_rate.setState("GA");
        sales_tax_rate.setRate(new BigDecimal("0.06"));

        sales_tax_rate2 = new Sales_Tax_Rate();
        sales_tax_rate2.setState("FL");
        sales_tax_rate2.setRate(new BigDecimal("0.01"));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldGetSalesTaxRate() {
        sales_tax_rate = salesTaxRate_dao.addSalesTaxRate(sales_tax_rate);
        Sales_Tax_Rate salesTest = salesTaxRate_dao.getSalesTaxRate(sales_tax_rate.getState());
        assertEquals(salesTest, sales_tax_rate);
    }

    @Test
    public void shouldAddSalesTaxRate() {
        sales_tax_rate = salesTaxRate_dao.addSalesTaxRate(sales_tax_rate);
        assertEquals(1, salesTaxRate_dao.getAllSalesTaxRate().size());
    }

    @Test
    public void shouldGetAllSalesTaxRate() {
        salesTaxRate_dao.addSalesTaxRate(sales_tax_rate);
        salesTaxRate_dao.addSalesTaxRate(sales_tax_rate2);

        List<Sales_Tax_Rate> sList = salesTaxRate_dao.getAllSalesTaxRate();
        assertEquals(2, sList.size());

    }

    @Test
    public void shouldUpdateSalesTaxRate() {
        salesTaxRate_dao.addSalesTaxRate(sales_tax_rate);
        sales_tax_rate.setRate(new BigDecimal("0.01"));

        Sales_Tax_Rate salesTest = salesTaxRate_dao.getSalesTaxRate(sales_tax_rate.getState());
        assertNotEquals(salesTest, sales_tax_rate);
    }


    @Test
    public void shouldDeleteSalesTaxRate() {
        salesTaxRate_dao.addSalesTaxRate(sales_tax_rate);
        salesTaxRate_dao.deleteSalesTaxRate(sales_tax_rate.getState());

        Sales_Tax_Rate salesTest = salesTaxRate_dao.getSalesTaxRate(sales_tax_rate.getState());

        assertNull(salesTest);
    }
}