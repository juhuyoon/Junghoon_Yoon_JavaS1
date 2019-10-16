package com.company.JunghoonYoonGameStore.DAO;

import com.company.JunghoonYoonGameStore.DTO.*;
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
public class InvoiceDaoTest {
    @Autowired
    private GameDao gameDao;

    private Game game;

    @Autowired
    private ConsoleDao consoleDao;

    private Console console;

    @Autowired
    private TshirtDao tshirtDao;

    private TShirt tShirt;

    @Autowired
    private SalesTaxRate_Dao salesTaxRate_dao;

    private Sales_Tax_Rate salesTax;

    @Autowired
    private ProcessingFeesDao processingFeesDao;

    private Processing_Fee pFees;

    @Autowired
    private InvoiceDao invoiceDao;

    private Invoice invoice, invoice2;


    @Before
    public void setUp() throws Exception {
        gameDao.getAllGames().forEach(g -> {
            gameDao.deleteGame(g.getGame_id());
        });

        consoleDao.getAllConsoles().forEach(c -> {
            consoleDao.deleteConsole(c.getConsole_id());
        });

        tshirtDao.getAllTShirts().forEach(t -> {
            tshirtDao.deleteTShirt(t.getT_shirt_id());
        });

        salesTaxRate_dao.getAllSalesTaxRate().forEach(s -> {
            salesTaxRate_dao.deleteSalesTaxRate(s.getState());
        });

        processingFeesDao.getAllProcessingFees().forEach(p -> {
            processingFeesDao.deleteProcessingFees(p.getProduct_type());
        });

        invoiceDao.getAllInvoice().forEach(i -> {
            invoiceDao.deleteInvoice(i.getInvoice_id());
        });




        game = new Game();
        game.setTitle("Harvest Moon");
        game.setEsrb_rating("G");
        game.setDescription("Farming Game");
        game.setPrice(new BigDecimal("15.99"));
        game.setStudio("EA");
        game.setQuantity(10);

        gameDao.addGame(game);

        console = new Console();
        console.setModel("PC");
        console.setManufacturer("Microsoft");
        console.setMemory_amount("500GB");
        console.setProcessor("Intel");
        console.setPrice(new BigDecimal("599.99"));
        console.setQuantity(5);

        consoleDao.addConsole(console);

        tShirt = new TShirt();
        tShirt.setSize("Medium");
        tShirt.setColor("Orange");
        tShirt.setDescription("Pretty and Orange");
        tShirt.setPrice(new BigDecimal("5.55"));
        tShirt.setQuantity(15);

        tshirtDao.addTShirt(tShirt);

        salesTax = new Sales_Tax_Rate();
        salesTax.setState("GA");
        salesTax.setRate(new BigDecimal("0.06"));

        salesTaxRate_dao.addSalesTaxRate(salesTax);

        pFees = new Processing_Fee();
        pFees.setProduct_type("Game");
        pFees.setFee(new BigDecimal("15.99"));

        processingFeesDao.addProcessingFees(pFees);

        invoice = new Invoice();
        invoice.setName("Jung");
        invoice.setStreet("123 Street");
        invoice.setCity("Atlanta");
        invoice.setState(salesTax.getState());
        invoice.setZipcode("30033");
        invoice.setItem_type("game");
        invoice.setItem_id(game.getGame_id());
        invoice.setUnit_price(new BigDecimal("15.99"));
        invoice.setQuantity(10);
        invoice.setSubtotal(new BigDecimal("5.99"));
        invoice.setTax(salesTax.getRate());
        invoice.setProcessing_fee(pFees.getFee());

        BigDecimal addAmount = ((invoice.getSubtotal().multiply(invoice.getTax()).add(invoice.getProcessing_fee())));

       BigDecimal roundAmount = addAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN);

        invoice.setTotal((roundAmount));

        invoice2 = new Invoice();
        invoice2.setName("Yoon");
        invoice2.setStreet("123 Street");
        invoice2.setCity("Denver");
        invoice2.setState(salesTax.getState());
        invoice2.setZipcode("22999");
        invoice2.setItem_type("game");
        invoice2.setItem_id(game.getGame_id());
        invoice2.setUnit_price(new BigDecimal("13.99"));
        invoice2.setQuantity(10);
        invoice2.setSubtotal(new BigDecimal("7.99"));
        invoice2.setTax(salesTax.getRate());
        invoice2.setProcessing_fee(pFees.getFee());
        invoice2.setTotal((roundAmount));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addInvoice() {
        invoice = invoiceDao.addInvoice(invoice);
        assertEquals(1, invoiceDao.getAllInvoice().size());
    }

    @Test
    public void getInvoice() {
        invoice = invoiceDao.addInvoice(invoice);
        Invoice invoiceTest = invoiceDao.getInvoice(invoice.getInvoice_id());

        System.out.println(invoice);
        System.out.println(invoiceTest);

        assertEquals(invoice, invoiceTest);
    }

    @Test
    public void getAllInvoice() {
        invoiceDao.addInvoice(invoice);
        invoiceDao.addInvoice(invoice2);

        List<Invoice> invoiceTest = invoiceDao.getAllInvoice();
        assertNotEquals(invoiceTest, invoice);
    }

    @Test
    public void updateInvoice() {
        invoiceDao.addInvoice(invoice);
        invoice.setQuantity(15);

        Invoice invoiceTest = invoiceDao.getInvoice(invoice.getInvoice_id());
    }

    @Test
    public void deleteInvoice() {
        invoiceDao.addInvoice(invoice);
        invoiceDao.deleteInvoice(invoice.getInvoice_id());

        Invoice invoiceTest = invoiceDao.getInvoice(invoice.getInvoice_id());

        assertNull(invoiceTest);
    }
}