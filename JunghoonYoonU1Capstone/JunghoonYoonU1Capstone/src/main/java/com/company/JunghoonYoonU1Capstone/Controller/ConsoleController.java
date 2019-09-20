package com.company.JunghoonYoonU1Capstone.Controller;

import com.company.JunghoonYoonU1Capstone.DAO.ConsoleDao;
import com.company.JunghoonYoonU1Capstone.DTO.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value ="/Console")
public class ConsoleController {
    @Autowired
    private ConsoleDao consoleDao;
    private Console console;

    @GetMapping("/get/{consoleId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Console getConsole(@PathVariable(name="consoleId") Integer consoleId) {
        return consoleDao.getConsole(consoleId);
    }

    @GetMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Console> getAllConsoles() {
        return consoleDao.getAllConsoles();
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Console addConsole(@RequestBody @Valid Console console) {
        return consoleDao.addConsole(console);
    }

    @PutMapping("/update")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public String updateConsole( @RequestBody Console console) {
        consoleDao.updateConsole(console);
        return "Console has been updated!";
    }

    @DeleteMapping("/delete/{consoleId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteConsole(@PathVariable(name = "consoleId") Integer consoleId) {
        consoleDao.deleteConsole(consoleId);
        return "Console has been deleted!";
    }

    @GetMapping("/manufacturer/{manufacturer}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Console> getConsolesByManufacturer(@PathVariable(name = "manufacturer") String manufacturer){
        return consoleDao.getConsolesByManufacturer(manufacturer);
    }
}
