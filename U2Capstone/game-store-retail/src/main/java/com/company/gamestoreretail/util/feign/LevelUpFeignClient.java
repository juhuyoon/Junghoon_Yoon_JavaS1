package com.company.gamestoreretail.util.feign;

import com.company.gamestoreretail.util.message.LevelViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("level-up-service")
@RequestMapping(value = "/level-up")
public interface LevelUpFeignClient {

    @PostMapping
    public LevelViewModel createLevelUp(@RequestBody LevelViewModel levelViewModel);

    @GetMapping
    public List<LevelViewModel> getAllLevelUp();

    @GetMapping(value = "/{level_up_id}")
    public LevelViewModel getLevelUp(@PathVariable int level_up_id);

    @GetMapping(value ="/customer/{customer_id}")
    public LevelViewModel getLevelUpByCustomer(@PathVariable int customer_id);

    @PutMapping(value = "/{level_up_id}")
    public void updateLevelUp(@RequestBody LevelViewModel levelViewModel);

    @DeleteMapping(value = "/{level_up_id}")
    public void deleteLevelUp(@PathVariable int level_up_id);

}
