package com.company.JunghoonYoonU1Capstone.ServiceLayer;

import com.company.JunghoonYoonU1Capstone.DAO.*;
import com.company.JunghoonYoonU1Capstone.DTO.*;
import com.company.JunghoonYoonU1Capstone.ViewModel.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ServiceLayer {

    private GameDao gameDao;
    private ConsoleDao consoleDao;
    private TshirtDao tshirtDao;
    private ProcessingFeesDao pFeesDao;
    private SalesTaxRate_Dao salesTaxDao;
    private InvoiceDao invoiceDao;

    @Autowired
    public ServiceLayer(GameDao gameDao, ConsoleDao consoleDao, TshirtDao tshirtDao, ProcessingFeesDao pFeesDao, SalesTaxRate_Dao salesTaxDao, InvoiceDao invoiceDao) {
        this.gameDao = gameDao;
        this.consoleDao = consoleDao;
        this.tshirtDao = tshirtDao;
        this.pFeesDao = pFeesDao;
        this.salesTaxDao = salesTaxDao;
        this.invoiceDao = invoiceDao;
    }

        //Adding the order view model to the database
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

            //Finding the correct table reference to access from item type.
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

        //Gets the Invoice to display from the order.
    public Invoice getOrder(Integer invoiceId) {
        Invoice invoice = invoiceDao.getInvoice(invoiceId);
        return invoice;
    }

    // Console API
    public Console addConsole(Console console) {
        return consoleDao.addConsole(console);
    }

    public Console getConsole(Integer console_id){
        return consoleDao.getConsole(console_id);
    }

    public List<Console> getAllConsoles() {
        return consoleDao.getAllConsoles();
    }

    public void updateConsole(Console console){
        consoleDao.updateConsole(console);
    }

    public void deleteConsole(Integer console_id){
        consoleDao.deleteConsole(console_id);
    }

    // Game API
    public Game addGame(Game game) {
        return gameDao.addGame(game);
    }

    public Game getGame(Integer game_id){
        return gameDao.getGame(game_id);
    }

    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    public void updateGame(Game game){
        gameDao.updateGame(game);
    }

    public void deleteGame(Integer game_id){
        gameDao.deleteGame(game_id);
    }

    // TShirt API
    public TShirt addTShirt(TShirt tShirt) {
        return tshirtDao.addTShirt(tShirt);
    }

    public TShirt getTShirt(Integer tShirt_id){
        return tshirtDao.getTShirt(tShirt_id);
    }

    public List<TShirt> getAllTShirts() {
        return tshirtDao.getAllTShirts();
    }

    public void updateTShirt(TShirt tShirt){
        tshirtDao.updateTShirt(tShirt);
    }

    public void deleteShirt(Integer tShirt_id){
        tshirtDao.deleteTShirt(tShirt_id);
    }

    // Processing Fees API
    public Processing_Fee addPFees(Processing_Fee pFees) {
        return pFeesDao.addProcessingFees(pFees);
    }

    public Processing_Fee getPFees(String pType){
        return pFeesDao.getProcessingFees(pType);
    }

    public List<Processing_Fee> getAllpFees() {
        return pFeesDao.getAllProcessingFees();
    }

    public void updatePFees(Processing_Fee pFee){
        pFeesDao.updateProcessingFees(pFee);
    }

    public void deletePFees(String pType){
        pFeesDao.deleteProcessingFees(pType);
    }

    // Sales Tax Rate API
    public Sales_Tax_Rate addSalesTax(Sales_Tax_Rate sTaxes) {
        return salesTaxDao.addSalesTaxRate(sTaxes);
    }

    public Sales_Tax_Rate getSalesTax(String state){
        return salesTaxDao.getSalesTaxRate(state);
    }

    public List<Sales_Tax_Rate> getAllSalesTax() {
        return salesTaxDao.getAllSalesTaxRate();
    }

    public void updateSalesTax(Sales_Tax_Rate sTaxes){
        salesTaxDao.updateSalesTaxRate(sTaxes);
    }

    public void deleteSalesTax(String state){
        salesTaxDao.deleteSalesTaxRate(state);
    }

    @Override
    public String toString() {
        return "ServiceLayer{" +
                "gameDao=" + gameDao +
                ", consoleDao=" + consoleDao +
                ", tshirtDao=" + tshirtDao +
                ", pFeesDao=" + pFeesDao +
                ", salesTaxDao=" + salesTaxDao +
                ", invoiceDao=" + invoiceDao +
                '}';
    }
}
