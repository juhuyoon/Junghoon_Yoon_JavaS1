package com.trilogyed.authorizationservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserInfoController {
    @GetMapping("/principal")
    public Principal getUserInfo(Principal principal) {
        System.out.println("Called userinfo");
        return principal;
    }
}
