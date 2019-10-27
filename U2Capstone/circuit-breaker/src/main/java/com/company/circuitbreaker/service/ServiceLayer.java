package com.company.circuitbreaker.service;

import com.company.circuitbreaker.LevelUpClient;
import com.company.circuitbreaker.util.feign.messages.LevelViewModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

    private LevelUpClient client;

    @HystrixCommand(fallbackMethod = "getDefaultList")
    public List<LevelViewModel> getAllLevelUp(){
        return client.getAllLevelUp();
    }

    @HystrixCommand(fallbackMethod = "getDefaultLevelUp")
    public LevelViewModel getLevelUp(int level_up_id){
        return client.getLevelUp(level_up_id);
    }

    @HystrixCommand(fallbackMethod = "getDefaultLevelUp")
    public LevelViewModel getLevelByCustomer(int customer_id){
        return client.getLevelUpByCustomer(customer_id);
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
}
