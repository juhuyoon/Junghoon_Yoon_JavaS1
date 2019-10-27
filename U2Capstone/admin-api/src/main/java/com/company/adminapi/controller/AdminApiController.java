package com.company.adminapi.controller;

import com.company.adminapi.dto.*;
import com.company.adminapi.util.feign.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
public class AdminApiController {
    private final CustomerClient customerClient;
    private final InventoryClient inventoryClient;
    private final InvoiceClient invoiceClient;
    private final LevelUpClient levelUpClient;
    private final ProductClient productClient;


    @Autowired
    public AdminApiController(CustomerClient customerClient, InventoryClient inventoryClient,
                              InvoiceClient invoiceClient, LevelUpClient levelUpClient,
                              ProductClient productClient) {
        this.customerClient = customerClient;
        this.inventoryClient = inventoryClient;
        this.invoiceClient = invoiceClient;
        this.levelUpClient = levelUpClient;
        this.productClient = productClient;
    }

    // Customer Client
    // ================
    @RequestMapping(value="/customer/{id}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable int id)throws Exception{
        Customer customer = customerClient.getCustomer(id);
        if (customer == null) {
            throw new Exception("Customer not found.");
        }
        return customer;
    }

    @RequestMapping(value="/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer (@RequestBody @Valid Customer customer) throws Exception {
        return customerClient.createCustomer(customer);
    }

    @RequestMapping(value="/customer", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody @Valid Customer customer) {
        customerClient.updateCustomer(customer);
    }

    @RequestMapping(value="/customer/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {
        customerClient.deleteCustomer(id);
    }

    @RequestMapping(value="/customer", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Customer> getAllCustomer() {
        return customerClient.getCustomerList();
    }



    // Inventory Client
    // ================

    @RequestMapping(value="/inventory/{id}", method = RequestMethod.GET)
    public Inventory getInventory(@PathVariable int id)throws Exception{
        Inventory inventory = inventoryClient.getInventory(id);
        if (inventory == null) {
            throw new Exception("Inventory not found.");
        }
        return inventory;
    }

    @RequestMapping(value="/inventory", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory createInventory (@RequestBody @Valid Inventory inventory) throws Exception {
        return inventoryClient.createInventory(inventory);
    }

    @RequestMapping(value="/inventory", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateInventory(@RequestBody @Valid Inventory inventory) {
        inventoryClient.updateInventory(inventory);
    }

    @RequestMapping(value="/inventory/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteInventory(@PathVariable int id) {
        inventoryClient.deleteInventory(id);
    }

    @RequestMapping(value="/inventory", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Inventory> getAllInventory() {
        return inventoryClient.getInventoryList();
    }

    // Invoice Client
    // ================

    @RequestMapping(value="/invoice/{id}", method = RequestMethod.GET)
    public Invoice getInvoice(@PathVariable int id)throws Exception{
        Invoice invoice = invoiceClient.getInvoice(id);
        if (invoice == null) {
            throw new Exception("Invoice not found.");
        }
        return invoice;
    }

    @RequestMapping(value="/invoice", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice (@RequestBody @Valid Invoice invoice) throws Exception {
        return invoiceClient.createInvoice(invoice);
    }

    @RequestMapping(value="/invoice", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateInvoice(@RequestBody @Valid Invoice invoice) {
        invoiceClient.updateInvoice(invoice);
    }

    @RequestMapping(value="/invoice/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int id) {
        invoiceClient.deleteInvoice(id);
    }

    @RequestMapping(value="/invoice", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Invoice> getAllInvoice() {
        return invoiceClient.getInvoiceList();
    }

    // Invoice Item Client
    // ================

    @RequestMapping(value="/invoiceitem/{id}", method = RequestMethod.GET)
    public InvoiceItem getInvoiceItem(@PathVariable int id)throws Exception{
        InvoiceItem invoiceItem =  invoiceClient.getInvoiceItem(id);
        if (invoiceItem == null) {
            throw new Exception("Invoice Item not found.");
        }
        return invoiceItem;
    }

    @RequestMapping(value="/invoiceitem", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceItem createInvoiceItem (@RequestBody @Valid InvoiceItem invoiceItem) throws Exception {
        return invoiceClient.createInvoiceItem(invoiceItem);
    }

    @RequestMapping(value="/invoiceitem", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem) {
        invoiceClient.updateInvoiceItem(invoiceItem);
    }

    @RequestMapping(value="/invoiceitem/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteInvoiceItem(@PathVariable int id) {
        invoiceClient.deleteInvoiceItem(id);
    }

    @RequestMapping(value="/invoiceitem", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceItem> getAllInvoiceItem() {
        return invoiceClient.getInvoiceItemList();
    }


    // Level Up Client
    // ================

    //@PostMapping
    @RequestMapping(value = "/levelup", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public LevelUp createLevelUp(@RequestBody @Valid LevelUp levelUp) {
        return levelUpClient.createLevelUp(levelUp);

    }

    //@DeleteMapping(path = "/{levelup_id}")
    @RequestMapping(value = "/levelup/{levelup_id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteLevelUp(@PathVariable int levelup_id) {
        levelUpClient.deleteLevelUp(levelup_id);
    }

    //@GetMapping
    @RequestMapping(value = "/levelup", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<LevelUp> getLevelUpList() {
        return levelUpClient.getLevelUpList();

    }

    //@GetMapping(path = "/{id}")
    @RequestMapping(value = "/levelup/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public LevelUp getLevelUp(@PathVariable int id) throws Exception {
        LevelUp levelUp = levelUpClient.getLevelUp(id);
        if (levelUp == null) {
            throw new Exception("Product not found.");
        }
        return levelUp;

    }

    //@PutMapping
    @RequestMapping(value = "/levelup", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateLevelUp(@RequestBody @Valid LevelUp levelUp) {
        levelUpClient.updateLevelUp(levelUp);

    }

    // Product Client
    // ================

    //@PostMapping
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody @Valid Product product) {
        return productClient.createProduct(product);

    }

    //@DeleteMapping(path = "/{product_id}")
    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int product_id) {
        productClient.deleteProduct(product_id);
    }

    //@GetMapping
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Product> getProductList() {
        return productClient.getProductList();

    }

    //@GetMapping(path = "/{id}")
    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Product getProduct(@PathVariable int id) throws Exception {
        Product product = productClient.getProduct(id);
        if (product == null) {
            throw new Exception("Product not found.");
        }
        return product;

    }

    //@PutMapping
    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody @Valid Product product) {
        productClient.updateProduct(product);

    }


}
