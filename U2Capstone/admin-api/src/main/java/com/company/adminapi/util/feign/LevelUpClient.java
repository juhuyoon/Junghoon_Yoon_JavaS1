package com.company.adminapi.util.feign;

import com.company.adminapi.dto.LevelUp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "levelup-service")
public interface LevelUpClient {
    //@PostMapping
    @RequestMapping(value = "/levelup", method = RequestMethod.POST)
    public LevelUp createLevelUp(@RequestBody @Valid LevelUp levelUp);

    //@DeleteMapping(path = "/{levelup_id}")
    @RequestMapping(value = "/inventory/{levelup_id}", method = RequestMethod.DELETE)
    public void deleteLevelUp(@PathVariable int levelup_id);

    //@GetMapping
    @RequestMapping(value = "/levelup", method = RequestMethod.GET)
    public List<LevelUp> getLevelUpList();

    //@GetMapping(path = "/{id}")
    @RequestMapping(value = "/levelup/{id}", method = RequestMethod.GET)
    public LevelUp getLevelUp(@PathVariable int id);

    //@PutMapping
    @RequestMapping(value = "/levelup", method = RequestMethod.PUT)
    public void updateLevelUp(@RequestBody @Valid LevelUp levelUp);

}
