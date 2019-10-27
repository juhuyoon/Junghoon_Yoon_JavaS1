package com.company.levelupservice.dao;

import com.company.levelupservice.model.LevelUp;
import com.company.levelupservice.model.LevelViewModel;

import java.util.List;

public interface LevelUpDao {

    //create
    LevelUp createLevelUp(LevelUp lvl);

    //read one
    LevelUp readLevelUp(int level_up_id);

    //read all
    List<LevelUp> readAll();

    //read by customer
    LevelUp readByCustomer(int customer_id);

    //update
    void updateLevelUp(LevelUp lvl);

    //delete
    void deleteLevelUp(int level_up_id);
}
