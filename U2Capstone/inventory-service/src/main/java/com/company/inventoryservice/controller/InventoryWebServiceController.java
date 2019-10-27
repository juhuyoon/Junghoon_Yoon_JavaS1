package com.company.inventoryservice.controller;

import com.company.inventoryservice.dao.InventoryDao;
import com.company.inventoryservice.dto.Inventory;
import com.company.inventoryservice.exceptions.InventoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InventoryWebServiceController {
    // All DAOs for each model is created with ServiceLayer's contructor.
    private final InventoryDao dao;

    public InventoryWebServiceController(InventoryDao dao) {
        this.dao = dao;
    }

    //@PostMapping
    @RequestMapping(value = "/inventory", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory createInventory(@RequestBody @Valid Inventory inventory) {
        return dao.addInventory(inventory);

    }

    //@DeleteMapping(path = "/{inventory_id}")
    @RequestMapping(value = "/inventory/{inventory_id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteInventory(@PathVariable int inventory_id) {
        dao.deleteInventory(inventory_id);
    }

    //@GetMapping
    @RequestMapping(value = "/inventory", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Inventory> getInventoryList() {
        return dao.getAllInventory();

    }

    //@GetMapping(path = "/{id}")
    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Inventory getInventory(@PathVariable int id) throws Exception {
        Inventory inventory = dao.getInventory(id);
        if (inventory == null) {
            throw new InventoryNotFoundException("Inventory not found.");
        }
        return inventory;

    }

    //@PutMapping
    @RequestMapping(value = "/inventory", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateInventory(@RequestBody @Valid Inventory inventory) {
        dao.updateInventory(inventory);

    }

}