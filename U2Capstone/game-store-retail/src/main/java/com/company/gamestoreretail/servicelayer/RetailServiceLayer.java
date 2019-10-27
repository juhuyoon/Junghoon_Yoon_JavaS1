package com.company.gamestoreretail.servicelayer;

import com.company.gamestoreretail.model.InvoiceItem;
import com.company.gamestoreretail.model.InvoiceViewModel;
import com.company.gamestoreretail.util.feign.*;
import com.company.gamestoreretail.util.message.LevelViewModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class RetailServiceLayer {

    private InvoiceViewModel ivm;

    private CustomerFeignClient customerClient;

    private InventoryFeignClient inventoryClient;

    private InvoiceFeignClient invoiceClient;

    private LevelUpFeignClient levelUpClient;

    private ProductFeignClient productClient;

    //RabbitMQ Queue Producer Data
    public static final String EXCHANGE = "level-up-exchange";
    public static final String ROUTING_KEY = "level.up.#";

    private RabbitTemplate rabbitTemplate;

    public RetailServiceLayer() {
    }

    @Autowired
    public RetailServiceLayer(InvoiceViewModel ivm, CustomerFeignClient customerClient, InventoryFeignClient inventoryClient, InvoiceFeignClient invoiceClient, LevelUpFeignClient levelUpClient, ProductFeignClient productClient, RabbitTemplate rabbitTemplate) {
        this.ivm = ivm;
        this.customerClient = customerClient;
        this.inventoryClient = inventoryClient;
        this.invoiceClient = invoiceClient;
        this.levelUpClient = levelUpClient;
        this.productClient = productClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    @HystrixCommand(fallbackMethod = "getDefaultList")
    public List<LevelViewModel> getAllLevelUp(){
        return levelUpClient.getAllLevelUp();
    }

    @HystrixCommand(fallbackMethod = "getDefaultLevelUp")
    public LevelViewModel getLevelUp(int level_up_id){
        return levelUpClient.getLevelUp(level_up_id);
    }

    @HystrixCommand(fallbackMethod = "getDefaultLevelUp")
    public LevelViewModel getLevelByCustomer(int customer_id){
        return levelUpClient.getLevelUpByCustomer(customer_id);
    }


    //fallback
    private LevelViewModel getDefaultLevelUp(int level_up_id){
        LevelViewModel defaultlvm = new LevelViewModel(
                level_up_id,
                0,
                0,
                LocalDate.of(2000,01,01),
                "Sorry, Level Up! is down. Please check back again later to see your rewards points."
        );
        return defaultlvm;
    }

    private List<LevelViewModel> getDefaultList(){
        List<LevelViewModel> list = new ArrayList<>();
        list.add(new LevelViewModel(
                0,
                0,
                0,
                LocalDate.of(2000,01,01),
                "Sorry, Level Up! is down. Please check back again later to see your rewards points."
        ));
        return list;
    }

    @Transactional
    public InvoiceViewModel submitInvoice(InvoiceViewModel ivm) {
        ivm = invoiceClient.createInvoice(ivm);
        BigDecimal total = new BigDecimal("0.00");

        InvoiceViewModel newIvm = new InvoiceViewModel();
        newIvm.setInvoice_id(ivm.getInvoice_id());
        if(customerClient.getCustomer(ivm.getCustomer_id()) != null) {
            newIvm.setCustomer_id(ivm.getCustomer_id());
        } else {
            throw new IllegalArgumentException("Hey, this customer doesn't exist!");
        }
        newIvm.setPurchase_date(ivm.getPurchase_date());
        List<InvoiceItem> invoiceItems = ivm.getInvoiceItems();
        invoiceItems.forEach(i -> {
           if(inventoryClient.getInventory(i.getInventory_id()) == null) {
            throw new IllegalArgumentException("No inventory of the invoice item found.");
           } else {
               total.add(i.getUnit_price().multiply(new BigDecimal(i.getQuantity().toString())));
           }
        });

        Integer totalNumber = total.intValue();
        Integer multiplesOf = ((totalNumber / 50) * 10);
        Integer levelUpPointsFromPurchases = 0;

        if(multiplesOf < 1) {
            levelUpPointsFromPurchases = 0;
        }
        if(multiplesOf > 1){
            levelUpPointsFromPurchases = (50 * multiplesOf);
        }

        try {
            LevelViewModel lvm = new LevelViewModel();
            lvm.setCustomer_id(newIvm.getCustomer_id());
            lvm.setPoints(lvm.getPoints() + levelUpPointsFromPurchases);
            LevelViewModel msg = lvm;
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);
            newIvm.setPoints(lvm.getPoints());
        } catch(Exception e) {
            getAllLevelUp();
        }
        newIvm.setInvoiceItems(ivm.getInvoiceItems());
        return newIvm;
    }

    public InvoiceViewModel getInvoiceByCustomerId(int customer_id) {
        InvoiceViewModel invoice = invoiceClient.getInvoiceByCustomerId(customer_id);
        InvoiceViewModel returnModel = new InvoiceViewModel();
        returnModel.setInvoice_id(invoice.getInvoice_id());
        returnModel.setCustomer_id(invoice.getCustomer_id());
        returnModel.setPurchase_date(invoice.getPurchase_date());
        returnModel.setInvoiceItems(invoice.getInvoiceItems());

        return returnModel;
    }


}
