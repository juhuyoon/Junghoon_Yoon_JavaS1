package com.company.inventoryservice.dao;

import com.company.inventoryservice.dto.Inventory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InventoryDaoTest {

    @Autowired
    InventoryDao inventoryDao;

    @Before
    public void setUp() throws Exception {
        // Clean up the test db
        List<Inventory> iList = inventoryDao.getAllInventory();
        for (Inventory i : iList) {
            inventoryDao.deleteInventory(i.getInventory_id());
        }
    }

    @Test
    public void addGetDeleteInventory() {
        Inventory inventory = new Inventory();
        inventory.setProduct_id(1);
        inventory.setQuantity(10);

        inventory = inventoryDao.addInventory(inventory);

        Inventory inventory1 = inventoryDao.getInventory(inventory.getInventory_id());

        assertEquals(inventory1, inventory);

        inventoryDao.deleteInventory(inventory.getInventory_id());

        inventory1 = inventoryDao.getInventory(inventory.getInventory_id());

        assertNull(inventory1);

    }

    @Test
    public void getAllInventory() {
        Inventory inventory = new Inventory();
        inventory.setProduct_id(1);
        inventory.setQuantity(10);
        inventoryDao.addInventory(inventory);

        inventory = new Inventory();
        inventory.setProduct_id(2);
        inventory.setQuantity(20);
        inventoryDao.addInventory(inventory);

        List<Inventory> lList = inventoryDao.getAllInventory();

        assertEquals(2, lList.size());
    }

    @Test
    public void updateInventory() {
        Inventory inventory = new Inventory();
        inventory.setProduct_id(1);
        inventory.setQuantity(10);
        inventoryDao.addInventory(inventory);

        inventory.setQuantity(9);
        inventoryDao.updateInventory(inventory);

        Inventory inventory1 = inventoryDao.getInventory(inventory.getInventory_id());

        assertEquals(9, inventory1.getQuantity());
    }

}