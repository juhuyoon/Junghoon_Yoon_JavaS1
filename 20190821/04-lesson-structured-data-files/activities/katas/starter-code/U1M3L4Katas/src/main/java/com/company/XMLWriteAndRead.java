package com.company;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class XMLWriteAndRead {
    public static void main(String[] args) {
        PrintWriter writer = null;
        List<Car> carList = new ArrayList<>();

        carList.add(new Car(2012, "Toyota", "Camry", "Blue"));
        carList.add(new Car(2001, "Honda", "Civic", "Silver"));
        carList.add(new Car(2009, "Jeep", "Wrangler", "Rust"));
        carList.add(new Car(2018, "Tesla", "Roadster", "Black"));
        carList.add(new Car(1964, "Ford", "Mustang", "Red"));

        try {
            ObjectMapper mapper = new XmlMapper();
            String xmlCarList = mapper.writeValueAsString(carList);
            writer = new PrintWriter("carList.xml");
            writer.println(xmlCarList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if(writer != null) {
                writer.flush();
                writer.close();
            }
        }

        try {
            ObjectMapper mapper = new XmlMapper();
            carList = mapper.readValue(new File("carList.xml"), new TypeReference<List<Car>>() {});
            for(Car cars: carList) {
                System.out.println("=================");
                System.out.println(cars.getYear());
                System.out.println(cars.getColor());
                System.out.println(cars.getModel());
                System.out.println(cars.getMake());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Go see your xml file and read on the terminal!");
        }
    }
}
