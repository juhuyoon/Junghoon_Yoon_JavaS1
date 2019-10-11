package com.trilogyed.authorizationservice;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtility {

    public static void main(String[] args) {
        PasswordEncoder enc = new BCryptPasswordEncoder();

        String password = "html_5_secret";
        String encodedPassword = enc.encode(password);

        System.out.println(encodedPassword);
    }
}
