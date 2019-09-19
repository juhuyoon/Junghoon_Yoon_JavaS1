package com.company.JunghoonYoonU1Capstone.DAO;

import com.company.JunghoonYoonU1Capstone.DTO.Invoice;

import java.util.List;

public interface InvoiceDao {
    Invoice addInvoice(Invoice invoice);

    Invoice getInvoice(Integer invoice_id);

    List<Invoice> getAllInvoice();

    void updateInvoice(Invoice invoice);

    void deleteInvoice(Integer invoice_id);


}
