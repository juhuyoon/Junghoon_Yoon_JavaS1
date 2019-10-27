package com.company.adminapi.util.feign;

import com.company.adminapi.dto.Invoice;
import com.company.adminapi.dto.InvoiceItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "invoice-service")
public interface InvoiceClient {
    //@PostMapping
    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    public Invoice createInvoice(@RequestBody @Valid Invoice invoice);

    //@DeleteMapping(path = "/{invoice_id}")
    @RequestMapping(value = "/invoice/{invoice_id}", method = RequestMethod.DELETE)
    public void deleteInvoice(@PathVariable int invoice_id);

    //@GetMapping
    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    public List<Invoice> getInvoiceList();

    //@GetMapping(path = "/{id}")
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    public Invoice getInvoice(@PathVariable int id);

    //@PutMapping
    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
    public void updateInvoice(@RequestBody @Valid Invoice invoice);


    // Invoice Item API's starts here.

    //@PostMapping
    @RequestMapping(value = "/invoice_item", method = RequestMethod.POST)
    public InvoiceItem createInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem);

    //@DeleteMapping(path = "/{invoiceItem_id}")
    @RequestMapping(value = "/invoiceItem/{invoiceItem_id}", method = RequestMethod.DELETE)
    public void deleteInvoiceItem(@PathVariable int invoiceItem_id);

    //@GetMapping
    @RequestMapping(value = "/invoiceItem", method = RequestMethod.GET)
    public List<InvoiceItem> getInvoiceItemList();

    //@GetMapping(path = "/{id}")
    @RequestMapping(value = "/invoiceItem/{id}", method = RequestMethod.GET)
    public InvoiceItem getInvoiceItem(@PathVariable int id);

    //@PutMapping
    @RequestMapping(value = "/invoiceItem", method = RequestMethod.PUT)
    public void updateInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem);


}
