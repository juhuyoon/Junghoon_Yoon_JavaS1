package com.company.levelupservice.service;

import com.company.levelupservice.dao.LevelUpDao;
import com.company.levelupservice.model.LevelUp;
import com.company.levelupservice.model.LevelViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

    private LevelUpDao levelUpDao;

    @Autowired
    public ServiceLayer(LevelUpDao levelUpDao) {
        this.levelUpDao = levelUpDao;
    }

    public LevelViewModel addLevelUpEntry(LevelViewModel lvm){
        return convertObjectToViewModel(levelUpDao.createLevelUp(convertViewModelToObject(lvm)));
    }

    public LevelViewModel findLevelUpEntry(int level_up_id){
        return convertObjectToViewModel(levelUpDao.readLevelUp(level_up_id));
    }

    public List<LevelViewModel> findAllLevelUpEntries(){
        List<LevelViewModel> lvmList = new ArrayList<>();
        levelUpDao.readAll().stream().forEach(lvl ->
                lvmList.add(convertObjectToViewModel(lvl)));
        return lvmList;
    }

    public LevelViewModel findLevelUpByCustomer(int customer_id){
        return convertObjectToViewModel(levelUpDao.readByCustomer(customer_id));
    }

    public void updateLevelUpEntry(LevelViewModel lvm){
        levelUpDao.updateLevelUp(convertViewModelToObject(lvm));
    }

    public void removeLevelUpEntry(int level_up_id){
        levelUpDao.deleteLevelUp(level_up_id);
    }

    //helper methods
    //to object
    private LevelUp convertViewModelToObject(LevelViewModel lvm){
        LevelUp lvl = new LevelUp(
                lvm.getCustomer_id(),
                lvm.getPoints(),
                lvm.getMember_date());
        if (lvm.getLevel_up_id() > 0){
            lvl.setLevel_up_id(lvm.getLevel_up_id());
        }
        return lvl;
    }

    //to view model
    private LevelViewModel convertObjectToViewModel(LevelUp lvl){
        LevelViewModel lvm = new LevelViewModel(
                lvl.getLevel_up_id(),
                lvl.getCustomer_id(),
                lvl.getPoints(),
                lvl.getMember_date()
        );
        return lvm;
    }
}

