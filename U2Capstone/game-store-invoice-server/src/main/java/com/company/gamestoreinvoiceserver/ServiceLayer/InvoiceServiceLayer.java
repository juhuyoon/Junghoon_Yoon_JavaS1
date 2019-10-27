package com.company.gamestoreinvoiceserver.ServiceLayer;

import com.company.gamestoreinvoiceserver.Dao.InvoiceDao;
import com.company.gamestoreinvoiceserver.Dao.InvoiceItemDao;
import com.company.gamestoreinvoiceserver.ViewModel.InvoiceViewModel;
import com.company.gamestoreinvoiceserver.model.Invoice;
import com.company.gamestoreinvoiceserver.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceServiceLayer {
    InvoiceDao invoiceDao;

    InvoiceItemDao invoiceItemDao;

    InvoiceServiceLayer service;

    @Autowired
    public InvoiceServiceLayer(InvoiceDao invoiceDao, InvoiceItemDao invoiceItemDao) {
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
    }


    public InvoiceViewModel createInvoice(InvoiceViewModel ivm) {
        Invoice invoice = new Invoice(ivm.getInvoice_id(), ivm.getCustomer_id(), ivm.getPurchase_date());
        invoice = invoiceDao.createInvoice(invoice);
        return buildInvoiceViewModel(invoice);
    }

    public InvoiceViewModel getInvoice(Integer invoice_id){
        return buildInvoiceViewModel(invoiceDao.getInvoice(invoice_id));
    }

    public List<InvoiceViewModel> getAllInvoice() {
        List<Invoice> iList = invoiceDao.getAllInvoices();
        List<InvoiceViewModel> ivmList = new ArrayList<>();

        for(Invoice invoice : iList) {
            InvoiceViewModel ivm = buildInvoiceViewModel(invoice);
                ivmList.add(ivm);
        }

        return ivmList;
    }

    public InvoiceViewModel getInvoiceByCustomerId(Integer customer_id) {
        return buildInvoiceViewModel(invoiceDao.getInvoiceByCustomerId(customer_id));
    }

    public void updateInvoice(InvoiceViewModel ivm) {
        Invoice invoice = new Invoice(ivm.getInvoice_id(), ivm.getCustomer_id(), ivm.getPurchase_date());
        invoiceDao.updateInvoice(invoice);
    }

    public void deleteInvoice(Integer invoice_id) {
        if (invoiceDao.getInvoice(invoice_id) == null) {
            System.out.println("Invoice does not exist!");
        }
        List<InvoiceItem> iList = invoiceItemDao.getAllInvoiceItems();

        iList.stream().forEach(i -> invoiceItemDao.deleteInvoiceItem(i.getInvoice_item_id()));

        invoiceDao.deleteInvoice(invoice_id);
    }

    //====================== INVOICE ITEMS ======================= //
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) {
        invoiceItemDao.createInvoiceItem(invoiceItem);
        return invoiceItem;

    }

    public InvoiceItem getInvoiceItem(Integer invoice_item_id) {
        InvoiceItem invoiceItem = invoiceItemDao.getInvoiceItem(invoice_item_id);
        return invoiceItem;
    }

    public List<InvoiceItem> getAllInvoiceItems() {
        return invoiceItemDao.getAllInvoiceItems();
    }

    public void updateInvoiceItem(InvoiceItem invoiceItem) {
        invoiceItemDao.updateInvoiceItem(invoiceItem);
    }

    public void deleteInvoiceItem(Integer invoice_item_id) {
        invoiceItemDao.deleteInvoiceItem(invoice_item_id);
    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setInvoice_id(invoice.getInvoice_id());
        ivm.setCustomer_id(invoice.getCustomer_id());
        ivm.setPurchase_date(invoice.getPurchase_date());
        ivm.setInvoiceItems(invoiceItemDao.getAllInvoiceItems());

        return ivm;
    }




}
