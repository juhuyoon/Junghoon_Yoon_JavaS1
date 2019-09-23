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

    //Getting T Shirt Object by ID
    @GetMapping(value = "/get/{tShirtId}")
    @ResponseStatus(value = HttpStatus.OK)
    public TShirt getTshirt(@PathVariable(name="tShirtId") Integer tShirtId) {
        return tshirtDao.getTShirt(tShirtId);
    }

    //Getting back a list of the T Shirt Objects
    @GetMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    public List<TShirt> getAllTShirts() {
        return tshirtDao.getAllTShirts();
    }

    //Adding a T Shirt Object
    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public TShirt addTShirt(@RequestBody @Valid TShirt tShirt) {
        tshirtDao.addTShirt(tShirt);
        return tShirt;
    }

    //Updating a T Shirt Object
    @PutMapping("/update")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public String updateTShirt(@RequestBody @Valid TShirt tShirt) {
        tshirtDao.updateTShirt(tShirt);
        return "T Shirt Updated";
    }

    //Deleting a T Shirt Object By ID
    @DeleteMapping("/delete/{tShirtId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteTShirt(@PathVariable(name="tShirtId") Integer tShirtId) {
        tshirtDao.deleteTShirt(tShirtId);
        return "T Shirt deleted";
    }

    //Getting a List of T Shirt Objects by size
    @GetMapping("/size/{size}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<TShirt> getTShirtBySize(@PathVariable(name="size") String size) {
        return tshirtDao.getTShirtsBySize(size);
    }

    //Getting a List of T Shirt Objects by Color
    @GetMapping("/color/{color}")
    @ResponseStatus(value=HttpStatus.OK)
    public List<TShirt> getTShirtByColor(@PathVariable(name = "color") String color) {
        return tshirtDao.getTShirtsByColor(color);
    }


}
