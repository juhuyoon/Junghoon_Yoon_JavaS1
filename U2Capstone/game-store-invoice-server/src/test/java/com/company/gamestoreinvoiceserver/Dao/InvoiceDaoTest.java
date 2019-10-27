package com.company.gamestoreinvoiceserver.Dao;

import com.company.gamestoreinvoiceserver.model.Invoice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTest {
    @Autowired
    InvoiceDao invoiceDao;

    private Invoice invoice1, invoice2;



    @Before
    public void setUp() throws Exception {
        invoiceDao.getAllInvoices().forEach(i -> {
            invoiceDao.deleteInvoice(i.getInvoice_id());
        });

        invoice1 = new Invoice();
        invoice1.setCustomer_id(1);
        invoice1.setPurchase_date(LocalDate.of(2019, 5, 15));

        invoice2 = new Invoice();
        invoice2.setCustomer_id(2);
        invoice2.setPurchase_date(LocalDate.of(2019, 8, 5));
    }

    @Test
    public void shouldCreateGetDeleteInvoice() {
        invoice1 = invoiceDao.createInvoice(invoice1);
        System.out.println(invoice1);
        assertEquals(1, invoiceDao.getAllInvoices().size());

        Invoice fromDao = invoiceDao.getInvoice(invoice1.getInvoice_id());
        assertEquals(invoice1, fromDao);

        invoiceDao.deleteInvoice(invoice1.getInvoice_id());
        fromDao = invoiceDao.getInvoice(invoice1.getInvoice_id());
        assertNull(fromDao);
    }

    @Test
    public void shouldGetAllInvoices() {
        invoiceDao.createInvoice(invoice1);
        invoiceDao.createInvoice(invoice2);
        List<Invoice> iList = invoiceDao.getAllInvoices();
        assertEquals(2, iList.size());
    }

    @Test
    public void shouldUpdateInvoice() {
        invoiceDao.createInvoice(invoice1);
        invoice1.setPurchase_date(LocalDate.of(1999, 5, 25));
        Invoice fromDao = invoiceDao.getInvoice(invoice1.getInvoice_id());

        assertNotEquals(invoice1, fromDao);
    }

    @Test
    public void shouldGetInvoiceByCustomerId() {
        invoiceDao.createInvoice(invoice1);
        Invoice fromDao = invoiceDao.getInvoiceByCustomerId(invoice1.getCustomer_id());
        assertEquals(invoice1, fromDao);
    }

    @After
    public void tearDown() throws Exception {

    }
}