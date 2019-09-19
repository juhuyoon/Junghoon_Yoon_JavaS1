package com.company.JunghoonYoonU1Capstone.ServiceLayer;

import com.company.JunghoonYoonU1Capstone.DAO.*;
import com.company.JunghoonYoonU1Capstone.DTO.*;
import com.company.JunghoonYoonU1Capstone.ViewModel.InvoiceViewModel;
import com.company.JunghoonYoonU1Capstone.ViewModel.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class ServiceLayer {

    private ConsoleDao consoleDao;
    private GameDao gameDao;
    private TshirtDao tshirtDao;
    private InvoiceDao invoiceDao;
    private ProcessingFeesDao pFeesDao;
    private SalesTaxRate_Dao salesTaxDao;

    @Autowired
    public ServiceLayer(ConsoleDao consoleDao, GameDao gameDao, TshirtDao tshirtDao, InvoiceDao invoiceDao, ProcessingFeesDao pFeesDao, SalesTaxRate_Dao salesTaxDao) {
        this.consoleDao = consoleDao;
        this.gameDao = gameDao;
        this.tshirtDao = tshirtDao;
        this.invoiceDao = invoiceDao;
        this.pFeesDao = pFeesDao;
        this.salesTaxDao = salesTaxDao;
    }

        @Transactional
        public OrderViewModel saveOrder(OrderViewModel viewModel) {
            Invoice invoice = new Invoice();
            invoice.setName(viewModel.getName());
            invoice.setStreet(viewModel.getStreet());
            invoice.setCity(viewModel.getCity());
            invoice.setState(viewModel.getState());
            invoice.setZipcode(viewModel.getZipcode());
            invoice.setItem_type(viewModel.getItem_type());
            invoice.setItem_id(viewModel.getItem_id());
//            invoice.setUnit_price();
            invoice.setQuantity(viewModel.getQuantity());
//            invoice.setSubtotal();
//            invoice.setTax();
//            invoice.setProcessing_fee();
//            invoice.setTotal();

            viewModel.setOrder_id(invoice.getInvoice_id());

            switch(viewModel.getItem_type()) {
                case("Games"): {
                    gameDao.getGame(viewModel.getItem_id());
                    Processing_Fee pFee = pFeesDao.getProcessingFees("Games");
                    invoice.setUnit_price(pFee.getFee());
                    BigDecimal subTotal = (new BigDecimal(invoice.getQuantity()).multiply(invoice.getUnit_price()));
                    invoice.setSubtotal(subTotal);

                    break;
                }
                case("Consoles"): {
                    consoleDao.getConsole(viewModel.getItem_id());
                    Processing_Fee pFee = pFeesDao.getProcessingFees("Consoles");
                    invoice.setUnit_price(pFee.getFee());
                    break;
                }
                case("T-Shirts"): {
                    tshirtDao.getTShirt(viewModel.getItem_id());
                    Processing_Fee pFee = pFeesDao.getProcessingFees("T-Shirts");
                    invoice.setUnit_price(pFee.getFee());
                    break;
                }
                default:
                    System.out.println("Could not find the item type in the database. Please try again.");
                    return null;
            }

            return viewModel;
        }
//
//    public InvoiceViewModel getOrder(OrderViewModel viewModel) {
//
//    }

        //Helper Methods

        //Building up the view model for invoice to show back.
        private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
            InvoiceViewModel ivm = new InvoiceViewModel();
            ivm.setName(invoice.getName());
            ivm.setStreet(invoice.getStreet());
            ivm.setCity(invoice.getCity());
            ivm.setState(invoice.getState());
            ivm.setZipcode(invoice.getZipcode());
            ivm.setItem_type(invoice.getItem_type());
            ivm.setUnit_price(invoice.getUnit_price());
            ivm.setQuantity(invoice.getQuantity());
            ivm.setSubtotal(invoice.getSubtotal());
            ivm.setTax(invoice.getTax());
            ivm.setProcessing_fee(invoice.getProcessing_fee());
            ivm.setTotal(invoice.getTotal());

            return ivm;
        }



}
