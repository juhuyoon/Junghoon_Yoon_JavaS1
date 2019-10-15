package com.company.JunghoonYoonU1Capstone.DAO;

import com.company.JunghoonYoonU1Capstone.DTO.Item;

import java.util.List;

public interface ItemDao {
    Item add(Item item);
    List<Item> findAll();
    Item findById(int id);
}
