package com.company.gamestoreretail.util.feign;

import com.company.gamestoreretail.model.InvoiceItem;
import com.company.gamestoreretail.model.InvoiceViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("invoice-server-service")
public interface InvoiceFeignClient {

    // ====== Invoice Edge Services ===== //
    @PostMapping(value = "/invoices")
    public InvoiceViewModel createInvoice(@RequestBody InvoiceViewModel ivm);

    @GetMapping(value = "/invoices")
    public List<InvoiceViewModel> getAllInvoices();

    @GetMapping(value = "/invoices/{invoice_id}")
    public InvoiceViewModel getInvoice(@PathVariable(name="invoice_id") Integer invoice_id);

    @GetMapping(value = "/invoices/customer/{customer_id}")
    public InvoiceViewModel getInvoiceByCustomerId(@PathVariable(name="customer_id") Integer customer_id);

    @PutMapping(value = "/invoices/{invoice_id}")
    public void updateInvoice(@PathVariable(name="invoice_id") Integer invoice_id, @RequestBody @Valid InvoiceViewModel invoiceViewModel);

    @DeleteMapping(value = "/invoices/{invoice_id}")
    public void deleteInvoice(@PathVariable(name = "invoice_id") Integer invoice_id);


    // ====== Invoice Item Edge Services ===== //
    @GetMapping(value = "/invoices/invoiceItem/{invoice_item_id}")
    public InvoiceItem getInvoiceItem(@PathVariable(name="invoice_item_id") Integer invoice_item_id);

    @GetMapping(value = "/invoices/invoiceItem")
    public List<InvoiceItem> getAllInvoiceItems();

    @PostMapping(value = "/invoices/invoiceItem")
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem);

    @PutMapping(value = "/invoices/invoiceItem/{invoice_item_id}")
    public void updateInvoiceItem(@PathVariable(name="invoice_item_id") Integer invoice_item_id);

    @DeleteMapping(value = "/invoices/invoiceItem/{invoice_item_id}")
    public void deleteInvoiceItem(@PathVariable(name="invoice_item_id") Integer invoice_item_id);
}
