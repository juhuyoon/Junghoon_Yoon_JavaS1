package com.company;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class ComputerReader {
    public static void main(String[] args) {
        try {
            List<Computer> computerList =
                    new CsvToBeanBuilder<Computer>(new FileReader("computers.csv")).withType(Computer.class).build().parse();
            for(Computer comp : computerList) {
                System.out.println("=================================");
                System.out.println(comp.getBrand());
                System.out.println(comp.getModel());
                System.out.println(comp.getCPU());
                System.out.println(comp.getRAM());
                System.out.println(comp.getStorageSize());
            }

        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
