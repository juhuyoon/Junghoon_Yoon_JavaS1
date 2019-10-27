package com.company.circuitbreaker;

import com.company.circuitbreaker.util.feign.messages.LevelViewModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@FeignClient(
        name = "level-up-service"
)

@Service
public interface LevelUpClient {
    @GetMapping("/level-up")
    List<LevelViewModel> getAllLevelUp();

    @GetMapping("/level-up/{level_up_id")
    LevelViewModel getLevelUp(@PathVariable int level_up_id);

    @GetMapping("/level-up/customer/{customer_id}")
    LevelViewModel getLevelUpByCustomer(@PathVariable int customer_id);
}
