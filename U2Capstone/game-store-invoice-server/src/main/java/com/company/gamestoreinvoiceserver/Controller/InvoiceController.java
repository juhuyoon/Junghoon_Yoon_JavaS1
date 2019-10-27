package com.company.gamestoreinvoiceserver.Controller;

import com.company.gamestoreinvoiceserver.ServiceLayer.InvoiceServiceLayer;
import com.company.gamestoreinvoiceserver.ViewModel.InvoiceViewModel;
import com.company.gamestoreinvoiceserver.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
public class InvoiceController {

    @Autowired
    private InvoiceServiceLayer service;

    @PostMapping(value = "/invoice")
    @ResponseStatus(value = HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@RequestBody InvoiceViewModel ivm) {
        ivm = service.createInvoice(ivm);
        return ivm;
    }

    @GetMapping(value = "/invoice")
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoices() {
        return service.getAllInvoice();
    }

    @GetMapping("/invoice/{invoice_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public InvoiceViewModel getInvoice(@PathVariable(name="invoice_id") Integer invoice_id) {
        InvoiceViewModel ivm = service.getInvoice(invoice_id);
        if(ivm == null) {
            throw new IllegalArgumentException("Invoice does not exist");
        }
        return ivm;
    }

    @GetMapping("/invoice/customer/{customer_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public InvoiceViewModel getInvoiceByCustomerId(@PathVariable(name="customer_id") Integer customer_id) {
        return service.getInvoiceByCustomerId(customer_id);
    }

    @PutMapping("/invoice/{invoice_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateInvoice(@PathVariable(name="invoice_id") Integer invoice_id, @RequestBody @Valid InvoiceViewModel invoiceViewModel){
        invoiceViewModel.setInvoice_id(invoice_id);
        service.updateInvoice(invoiceViewModel);
    }

    @DeleteMapping("/invoice/{invoice_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteInvoice(@PathVariable(name="invoice_id") Integer invoice_id) {
        service.deleteInvoice(invoice_id);
    }

    @GetMapping("/invoice/invoiceItem/{invoice_item_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public InvoiceItem getInvoiceItem(@PathVariable(name = "invoice_item_id") Integer invoice_item_id) {
        return service.getInvoiceItem(invoice_item_id);
    }

    @GetMapping("/invoice/invoiceItem")
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceItem> getAllInvoiceItems() {
        return service.getAllInvoiceItems();
    }

    @PostMapping("/invoice/invoiceItem")
    @ResponseStatus(value = HttpStatus.CREATED)
    public InvoiceItem createInvoiceItem(@RequestBody InvoiceItem invoiceItem) {
        invoiceItem = service.createInvoiceItem(invoiceItem);
        return invoiceItem;
    }

    @PutMapping("/invoice/invoiceItem/{invoice_item_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateInvoiceItem(@PathVariable(name="invoice_item_id") Integer invoice_item_id, @RequestBody @Valid InvoiceItem invoiceItem) {
        invoiceItem.setInvoice_item_id(invoice_item_id);
        service.updateInvoiceItem(invoiceItem);
    }

    @DeleteMapping("/invoice/invoiceItem/{invoice_item_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteInvoiceItem(@PathVariable(name="invoice_item_id") Integer invoice_item_id) {
        service.deleteInvoiceItem(invoice_item_id);
    }
}
