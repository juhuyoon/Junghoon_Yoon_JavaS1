package com.company.HelloWebServices.Controller;

import com.company.HelloWebServices.DTO.HelloName;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/hello")
public class HelloWebController {

    @RequestMapping(value="/{name}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String sayHello(@PathVariable String name) {
        HelloName newName = new HelloName();
        newName.setName(name);
        return "Hello " + newName.getName();
    }
}
