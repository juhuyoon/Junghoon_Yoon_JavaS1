package com.company.JunghoonYoonU1Capstone.Controller;

import com.company.JunghoonYoonU1Capstone.DAO.InvoiceDao;
import com.company.JunghoonYoonU1Capstone.DTO.Invoice;
import com.company.JunghoonYoonU1Capstone.ServiceLayer.ServiceLayer;
import com.company.JunghoonYoonU1Capstone.ViewModel.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value ="/Invoice")
public class InvoiceController {
    @Autowired
    private ServiceLayer service;
    @Autowired
    private InvoiceDao invoiceDao;

    //Adding an Order View Model
    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public OrderViewModel addOrderViewModel(@RequestBody @Valid OrderViewModel ovm) {
        return service.addOrder(ovm);
    }

    //Getting back the invoice
    @GetMapping(value ="/get/{invoiceId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Invoice getInvoice(@PathVariable(name = "invoiceId") Integer invoiceId) {
        return invoiceDao.getInvoice(invoiceId);
    }

    //Getting back all the invoices
    @GetMapping(value = "")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Invoice> getAllInvoice() {
        return invoiceDao.getAllInvoice();
    }
}
