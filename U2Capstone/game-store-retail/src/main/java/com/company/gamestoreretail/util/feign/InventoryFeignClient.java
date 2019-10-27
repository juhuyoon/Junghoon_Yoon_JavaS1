package com.company.gamestoreretail.util.feign;

import com.company.gamestoreretail.model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@FeignClient("inventory-service")
public interface InventoryFeignClient {

    @RequestMapping(value ="/inventory", method = RequestMethod.POST)
    public Inventory createInventory(@RequestBody @Valid Inventory inventory);

    @RequestMapping(value = "/inventory/{inventory_id}", method = RequestMethod.DELETE)
    public void deleteInventory(@PathVariable int inventory_id);

    @RequestMapping(value = "/inventory", method = RequestMethod.GET)
    public List<Inventory> getInventoryList();

    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET)
    public Inventory getInventory(@PathVariable int id);

    @RequestMapping(value = "/inventory", method = RequestMethod.PUT)
    public void updateInventory(@RequestBody @Valid Inventory inventory);


}
