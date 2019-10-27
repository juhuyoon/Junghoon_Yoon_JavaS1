package com.company.adminapi.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtility {

    public static void main(String[] args) {
        PasswordEncoder enc = new BCryptPasswordEncoder();

        String password = "manager";
        String encodedPassword = enc.encode(password);
        System.out.println("manager password is set as " + encodedPassword);

        password = "admin";
        encodedPassword = enc.encode(password);
        System.out.println("admin password is set as " + encodedPassword);

        password = "teamlead";
        encodedPassword = enc.encode(password);
        System.out.println("teamlead password is set as " + encodedPassword);

        password = "employee";
        encodedPassword = enc.encode(password);
        System.out.println("employee password is set as " + encodedPassword);

    }
}