package com.company.gamestoreinvoiceserver.Dao;

import com.company.gamestoreinvoiceserver.model.Invoice;
import com.company.gamestoreinvoiceserver.model.InvoiceItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceItemDaoTest {

    @Autowired
    private InvoiceItemDao invoiceItemDao;

    @Autowired
    private InvoiceDao invoiceDao;

    private Invoice invoice1;

    private InvoiceItem invoiceItem1, invoiceItem2;

    @Before
    public void setUp() throws Exception {
        invoiceItemDao.getAllInvoiceItems().forEach(i -> {
            invoiceItemDao.deleteInvoiceItem(i.getInvoice_item_id());
        });

        invoice1 = new Invoice();
        invoice1.setCustomer_id(1);
        invoice1.setPurchase_date(LocalDate.of(2019,5,5));
        invoice1 = invoiceDao.createInvoice(invoice1);

        invoiceItem1 =  new InvoiceItem();
        invoiceItem1.setInvoice_id(invoice1.getInvoice_id());
        invoiceItem1.setInventory_id(1);
        invoiceItem1.setQuantity(5);
        invoiceItem1.setUnit_price(new BigDecimal(15.99).setScale(2, BigDecimal.ROUND_HALF_DOWN));

        invoiceItem2 = new InvoiceItem();
        invoiceItem2.setInvoice_id(invoice1.getInvoice_id());
        invoiceItem2.setInventory_id(1);
        invoiceItem2.setQuantity(5);
        invoiceItem2.setUnit_price(new BigDecimal(16.99).setScale(2, BigDecimal.ROUND_HALF_DOWN));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createGetDeleteInvoiceItem() {
        invoiceItem1 = invoiceItemDao.createInvoiceItem(invoiceItem1);
        assertEquals(1, invoiceItemDao.getAllInvoiceItems().size());

        InvoiceItem fromDao = invoiceItemDao.getInvoiceItem(invoiceItem1.getInvoice_item_id());
        System.out.println(fromDao);
        assertEquals(invoiceItem1, fromDao);

        invoiceItemDao.deleteInvoiceItem(invoiceItem1.getInvoice_item_id());
        fromDao = invoiceItemDao.getInvoiceItem(invoiceItem1.getInvoice_item_id());
        assertNull(fromDao);
    }


    @Test
    public void getAllInvoiceItems() {
        invoiceItemDao.createInvoiceItem(invoiceItem1);
        invoiceItemDao.createInvoiceItem(invoiceItem2);
        List<InvoiceItem> iList = invoiceItemDao.getAllInvoiceItems();
        assertEquals(2, iList.size());
    }

    @Test
    public void updateInvoiceItem() {
        invoiceItemDao.createInvoiceItem(invoiceItem1);
        invoiceItem1.setQuantity(25);
        InvoiceItem fromDao = invoiceItemDao.getInvoiceItem(invoiceItem1.getInvoice_item_id());

        assertNotEquals(invoiceItem1, fromDao);
    }

    @Test
    public void getAllInvoiceItemsById() {
        invoiceItemDao.createInvoiceItem(invoiceItem1);
        List<InvoiceItem> fromDao = invoiceItemDao.getInvoiceItemsByInvoiceId(invoiceItem1.getInvoice_id());
        assertEquals(1, fromDao.size());
    }


}