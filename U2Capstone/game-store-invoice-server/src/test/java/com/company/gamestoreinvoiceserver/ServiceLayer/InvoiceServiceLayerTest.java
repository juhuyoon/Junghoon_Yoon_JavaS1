package com.company.gamestoreinvoiceserver.ServiceLayer;

import com.company.gamestoreinvoiceserver.Dao.InvoiceDao;
import com.company.gamestoreinvoiceserver.Dao.InvoiceItemDao;
import com.company.gamestoreinvoiceserver.ViewModel.InvoiceViewModel;
import com.company.gamestoreinvoiceserver.model.Invoice;
import com.company.gamestoreinvoiceserver.model.InvoiceItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceLayerTest {

    @InjectMocks
    private InvoiceServiceLayer service;

    @Mock
    private InvoiceDao invoiceDao;

    @Mock
    private InvoiceItemDao invoiceItemDao;

    @Before
    public void setUp() throws Exception {
        setUpInvoiceDaoMock();
        setUpInvoiceItemDaoMock();
    }

    @Test
    public void createGetInvoice() {
        Invoice input = new Invoice();
        input.setInvoice_id(1);
        input.setCustomer_id(1);
        input.setPurchase_date(LocalDate.of(2015, 8,5));

        input = invoiceDao.createInvoice(input);
        System.out.println(input);


        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice_item_id(1);
        invoiceItem.setInvoice_id(1);
        invoiceItem.setInventory_id(1);
        invoiceItem.setQuantity(1);
        invoiceItem.setUnit_price(new BigDecimal("15.99"));

        List<InvoiceItem> iList = new ArrayList<>();
        iList.add(invoiceItem);

        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setInvoice_id(input.getInvoice_id());
        ivm.setCustomer_id(input.getCustomer_id());
        ivm.setPurchase_date(input.getPurchase_date());
        ivm.setInvoiceItems(iList);

        InvoiceViewModel fromService = service.getInvoice(input.getInvoice_id());

        assertEquals(ivm, fromService);
    }


    @Test
    public void getAllInvoice() {
        Invoice input = new Invoice();
        input.setInvoice_id(1);
        input.setCustomer_id(1);
        input.setPurchase_date(LocalDate.of(2015, 8,5));

        input = invoiceDao.createInvoice(input);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice_item_id(1);
        invoiceItem.setInvoice_id(1);
        invoiceItem.setInventory_id(1);
        invoiceItem.setQuantity(1);
        invoiceItem.setUnit_price(new BigDecimal("15.99"));

        List<InvoiceItem> iList = new ArrayList<>();
        iList.add(invoiceItem);

        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setInvoice_id(input.getInvoice_id());
        ivm.setCustomer_id(input.getCustomer_id());
        ivm.setPurchase_date(input.getPurchase_date());
        ivm.setInvoiceItems(iList);

        List<InvoiceViewModel> ivmList = new ArrayList<>();
        ivmList.add(ivm);

        List<InvoiceViewModel> fromService = service.getAllInvoice();


        assertEquals(fromService.size(), ivmList.size());
    }

    @Test
    public void updateInvoice() {
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setPurchase_date(LocalDate.of(1993,8,5));
        Invoice invoice = new Invoice();
        invoice.setPurchase_date(LocalDate.of(1993,8,5));

        service.updateInvoice(ivm);

        verify(invoiceDao, times(1)).updateInvoice(invoice);

        InvoiceViewModel newIvm = ivm;
        assertEquals(ivm, newIvm);
    }

    @Test
    public void deleteInvoice() {
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(invoiceDao).deleteInvoice(integerArgumentCaptor.capture());
        service.deleteInvoice(1);
        verify(invoiceDao, times(1)).deleteInvoice(integerArgumentCaptor.getValue());

        assertEquals(1, integerArgumentCaptor.getValue().intValue());

    }

    @Test
    public void shouldCreateGetInvoiceItem() {
        InvoiceItem iItemInput = new InvoiceItem();
        iItemInput.setInvoice_item_id(1);
        iItemInput.setInvoice_id(1);
        iItemInput.setInventory_id(1);
        iItemInput.setQuantity(1);
        iItemInput.setUnit_price(new BigDecimal("15.99"));

        iItemInput = service.createInvoiceItem(iItemInput);
        System.out.println(iItemInput);

        InvoiceItem fromService = service.createInvoiceItem(iItemInput);
        System.out.println(fromService);
        assertEquals(iItemInput, fromService);
    }

    @Test
    public void shouldGetAllInvoiceItems() {
        InvoiceItem input = new InvoiceItem();
        input.setInvoice_item_id(1);
        input.setInvoice_id(1);
        input.setInventory_id(1);
        input.setQuantity(1);
        input.setUnit_price(new BigDecimal("15.99"));
        input = invoiceItemDao.createInvoiceItem(input);

        List<InvoiceItem> iList = new ArrayList<>();
        iList.add(input);

        List<InvoiceItem> fromDao = invoiceItemDao.getAllInvoiceItems();
        assertEquals(iList.size(), fromDao.size());
    }

    @Test
    public void shouldUpdateInvoiceItem() {
        InvoiceItem input = new InvoiceItem();
        input.setQuantity(100);
        service.updateInvoiceItem(input);

        verify(invoiceItemDao, times(1)).updateInvoiceItem(input);
        InvoiceItem output = input;
        assertEquals(input, output);
    }

    @Test
    public void shouldDeleteInvoiceItem() {
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(invoiceItemDao).deleteInvoiceItem(integerArgumentCaptor.capture());
        service.deleteInvoiceItem(1);
        verify(invoiceItemDao, times(1)).deleteInvoiceItem(integerArgumentCaptor.getValue());

        assertEquals(1, integerArgumentCaptor.getValue().intValue());
    }



    @After
    public void tearDown() throws Exception {

    }

    /**
     * Helper Methods
     */

    private void setUpInvoiceDaoMock() {
        Invoice output = new Invoice();
        output.setInvoice_id(1);
        output.setCustomer_id(1);
        output.setPurchase_date(LocalDate.of(2015,8,5));

        Invoice input = new Invoice();
        input.setInvoice_id(1);
        input.setCustomer_id(1);
        input.setPurchase_date(LocalDate.of(2015,8,5));

        List<Invoice> iList = new ArrayList<>();
        iList.add(input);


        doReturn(output).when(invoiceDao).createInvoice(input);
        doReturn(output).when(invoiceDao).getInvoice(input.getInvoice_id());
        doReturn(iList).when(invoiceDao).getAllInvoices();
    }

    private void setUpInvoiceItemDaoMock() {
        InvoiceItem input = new InvoiceItem();
        input.setInvoice_item_id(1);
        input.setInvoice_id(1);
        input.setInventory_id(1);
        input.setQuantity(1);
        input.setUnit_price(new BigDecimal("15.99"));

        InvoiceItem output = new InvoiceItem();
        output.setInvoice_item_id(1);
        output.setInvoice_id(1);
        output.setInventory_id(1);
        output.setQuantity(1);
        output.setUnit_price(new BigDecimal("15.99"));

        List<InvoiceItem> iList = new ArrayList<>();
        iList.add(input);

        doReturn(iList).when(invoiceItemDao).getAllInvoiceItems();
    }

    private void setUpInvoiceViewModelMock() {
        InvoiceViewModel input = new InvoiceViewModel();
        input.setInvoice_id(1);
        input.setCustomer_id(1);
        input.setPurchase_date(LocalDate.of(2015, 8,5));

        InvoiceItem iItemInput = new InvoiceItem();
        iItemInput.setInvoice_item_id(1);
        iItemInput.setInvoice_id(1);
        iItemInput.setInventory_id(1);
        iItemInput.setQuantity(1);
        iItemInput.setUnit_price(new BigDecimal("15.99"));

        List<InvoiceItem> iList = new ArrayList<>();
        iList.add(iItemInput);

        input.setInvoiceItems(iList);

        InvoiceViewModel output = new InvoiceViewModel();
        output.setInvoice_id(1);
        output.setCustomer_id(1);
        output.setPurchase_date(LocalDate.of(2015, 8,5));

        InvoiceItem iItemInput2 = new InvoiceItem();
        iItemInput2.setInvoice_item_id(1);
        iItemInput2.setInvoice_id(1);
        iItemInput2.setInventory_id(1);
        iItemInput2.setQuantity(1);
        iItemInput2.setUnit_price(new BigDecimal("15.99"));

        List<InvoiceItem> iList2 = new ArrayList<>();
        iList2.add(iItemInput2);

        output.setInvoiceItems(iList2);
    }
}