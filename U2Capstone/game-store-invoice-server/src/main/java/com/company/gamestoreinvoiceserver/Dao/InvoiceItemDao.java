package com.company.gamestoreinvoiceserver.Dao;

import com.company.gamestoreinvoiceserver.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {

    InvoiceItem createInvoiceItem(InvoiceItem invoiceItem);

    InvoiceItem getInvoiceItem(Integer invoice_item_id);

    List<InvoiceItem> getAllInvoiceItems();

    void updateInvoiceItem(InvoiceItem invoiceItem);

    void deleteInvoiceItem(Integer invoice_item_id);

    List<InvoiceItem> getInvoiceItemsByInvoiceId(Integer invoice_id);
}
