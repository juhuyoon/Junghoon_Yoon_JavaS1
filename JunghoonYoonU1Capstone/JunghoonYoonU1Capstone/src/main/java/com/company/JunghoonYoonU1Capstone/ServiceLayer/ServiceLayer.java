package com.company.JunghoonYoonU1Capstone.ServiceLayer;

import com.company.JunghoonYoonU1Capstone.DAO.*;
import com.company.JunghoonYoonU1Capstone.DTO.*;
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
        public OrderViewModel addOrder(OrderViewModel viewModel) {
            Invoice invoice = new Invoice();
            invoice.setName(viewModel.getName());
            invoice.setStreet(viewModel.getStreet());
            invoice.setCity(viewModel.getCity());
            invoice.setState(viewModel.getState());
            invoice.setZipcode(viewModel.getZipcode());
            invoice.setItem_type(viewModel.getItem_type());
            invoice.setItem_id(viewModel.getItem_id());
            invoice.setQuantity(viewModel.getQuantity());


//            invoice.setUnit_price(new BigDecimal("15.99"));
//            invoice.setSubtotal(new BigDecimal("5.99"));
//            invoice.setTax(new BigDecimal("0.06"));
//            invoice.setProcessing_fee(new BigDecimal("15.55"));
//            invoice.setTotal(new BigDecimal("100.00"));


            switch(invoice.getItem_type()) {
                case("Games"): {
                    //Grab game ID
                    Game game1 = gameDao.getGame(viewModel.getItem_id());
                    //Set processing fee by type
                    Processing_Fee pFee = pFeesDao.getProcessingFees("Games");

                    //Set Unit Price
                    invoice.setUnit_price(game1.getPrice());

                    //Subtotal math
                    BigDecimal subTotal = (new BigDecimal(invoice.getQuantity()).multiply(invoice.getUnit_price()));
                    invoice.setSubtotal(subTotal);

                    //Taxes
                    Sales_Tax_Rate salesTax1 = salesTaxDao.getSalesTaxRate(invoice.getState());

                    invoice.setTax(salesTax1.getRate());

                    //Processing Fee
                    invoice.setProcessing_fee(pFee.getFee());
                    if(invoice.getQuantity() > 10) {
                        BigDecimal moreThanTen = invoice.getProcessing_fee().add(new BigDecimal("15.49"));
                        invoice.setProcessing_fee(moreThanTen);
                    }

                    //Total
                    BigDecimal newTotal = invoice.getSubtotal().add((invoice.getSubtotal().multiply(invoice.getTax()).add(invoice.getProcessing_fee())));
                    newTotal = newTotal.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                    invoice.setTotal(newTotal);
                    invoiceDao.addInvoice(invoice);
                    System.out.println(invoice);
                    break;
                }
                case("Consoles"): {
                    //Grab ConsoleID
                    Console console1 = consoleDao.getConsole(viewModel.getItem_id());
                    //Grab Processing Fee by Type
                    Processing_Fee pFee = pFeesDao.getProcessingFees("Consoles");
                    //Set Unit Price
                    invoice.setUnit_price(console1.getPrice());

                    //Subtotal Math
                    BigDecimal subTotal = (new BigDecimal(invoice.getQuantity()).multiply(invoice.getUnit_price()));
                    invoice.setSubtotal(subTotal);

                    //Processing Fee
                    invoice.setProcessing_fee(pFee.getFee());
                    if(invoice.getQuantity() > 10) {
                        BigDecimal moreThanTen = invoice.getProcessing_fee().add(new BigDecimal("15.49"));
                        invoice.setProcessing_fee(moreThanTen);
                    }

                    //Taxes
                    Sales_Tax_Rate salesTax1 = salesTaxDao.getSalesTaxRate(invoice.getState());
                    invoice.setTax(salesTax1.getRate());

                    BigDecimal newTotal = (invoice.getSubtotal().multiply(invoice.getTax())).add(invoice.getProcessing_fee());

                    invoice.setTotal(newTotal);
                    break;
                }
                case("T-Shirts"): {
                    //Grab Tshirt ID
                    TShirt tShirt1 = tshirtDao.getTShirt(viewModel.getItem_id());
                    //Grab Processing Fee By Type
                    Processing_Fee pFee = pFeesDao.getProcessingFees("T-Shirts");
                    //Set Unit Price
                    invoice.setUnit_price(tShirt1.getPrice());

                    //Subtotal Math
                    BigDecimal subTotal = (new BigDecimal(invoice.getQuantity()).multiply(invoice.getUnit_price()));
                    invoice.setSubtotal(subTotal);

                    //Processing Fee
                    invoice.setProcessing_fee(pFee.getFee());
                    if(invoice.getQuantity() > 10) {
                        BigDecimal moreThanTen = invoice.getProcessing_fee().add(new BigDecimal("15.49"));
                        invoice.setProcessing_fee(moreThanTen);
                    }

                    //Taxes
                    Sales_Tax_Rate salesTax1 = salesTaxDao.getSalesTaxRate(invoice.getState());
                    invoice.setTax(salesTax1.getRate());

                    BigDecimal newTotal = (invoice.getSubtotal().multiply(invoice.getTax())).add(invoice.getProcessing_fee());

                    invoice.setTotal(newTotal);
                    break;
                }
                default:
                    System.out.println("Could not find the item type in the database. Please try again.");
                    return null;
            }
            viewModel.setOrder_id(invoice.getInvoice_id());
            return viewModel;
        }

    public Invoice getOrder(Integer invoiceId) {
        Invoice invoice = invoiceDao.getInvoice(invoiceId);
        return invoice;
    }
}
