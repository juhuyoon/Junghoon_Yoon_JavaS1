package com.company;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TelevisionReader {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Television> teleList;

            teleList = mapper.readValue(new File("televisions.json"), new TypeReference<List<Television>>(){});

            for(Television tele : teleList) {
                System.out.println("==============");
                System.out.println(tele.getBrand());
                System.out.println(tele.getModel());
                System.out.println(tele.getPrice());
                System.out.println(tele.getScreenSize());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
