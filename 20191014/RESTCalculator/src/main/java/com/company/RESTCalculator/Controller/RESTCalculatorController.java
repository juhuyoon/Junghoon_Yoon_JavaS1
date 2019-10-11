package com.company.RESTCalculator.Controller;


import com.company.RESTCalculator.DTO.RESTCalculator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController

public class RESTCalculatorController {
    @PostMapping(value = "/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public int add(Principal principal, @Valid @RequestBody RESTCalculator calc)  {
        return calc.add(calc.getOperand1(), calc.getOperand2());
    }

    @PostMapping(value = "/subtract")
    @ResponseStatus(value = HttpStatus.CREATED)
    public int subtract(Principal principal, @Valid @RequestBody RESTCalculator calc) {
        return calc.subtract(calc.getOperand1(), calc.getOperand2());
    }

    @PostMapping(value = "/multiply")
    @ResponseStatus(value = HttpStatus.CREATED)
    public int multiply(Principal principal, @Valid @RequestBody RESTCalculator calc) {
        return calc.mult(calc.getOperand1(), calc.getOperand2());
    }

    @PostMapping(value = "/divide")
    @ResponseStatus(value = HttpStatus.CREATED)
    public int divide(Principal principal, @Valid @RequestBody RESTCalculator calc) {
        return calc.divide(calc.getOperand1(), calc.getOperand2());
    }
}
