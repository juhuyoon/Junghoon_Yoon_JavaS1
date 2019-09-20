package com.company.JunghoonYoonU1Capstone.Controller;

import com.company.JunghoonYoonU1Capstone.DAO.TshirtDao;
import com.company.JunghoonYoonU1Capstone.DTO.TShirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value ="/TShirt")
public class TshirtController {
    @Autowired
    private TshirtDao tshirtDao;

    private TShirt tShirt;

    @GetMapping(value = "/get/{tShirtId}")
    @ResponseStatus(value = HttpStatus.OK)
    public TShirt getTshirt(@PathVariable(name="tShirtId") Integer tShirtId) {
        return tshirtDao.getTShirt(tShirtId);
    }

    @GetMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    public List<TShirt> getAllTShirts() {
        return tshirtDao.getAllTShirts();
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public TShirt addTShirt(@RequestBody @Valid TShirt tShirt) {
        tshirtDao.addTShirt(tShirt);
        return tShirt;
    }

    @PutMapping("/update")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public String updateTShirt(@RequestBody @Valid TShirt tShirt) {
        tshirtDao.updateTShirt(tShirt);
        return "T Shirt Updated";
    }

    @DeleteMapping("/delete/{tShirtId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteTShirt(@PathVariable(name="tShirtId") Integer tShirtId) {
        tshirtDao.deleteTShirt(tShirtId);
        return "T Shirt deleted";
    }
}
