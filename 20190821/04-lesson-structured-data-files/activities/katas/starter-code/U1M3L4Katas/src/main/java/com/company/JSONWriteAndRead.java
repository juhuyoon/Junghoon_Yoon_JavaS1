package com.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class JSONWriteAndRead {
    public static void main(String[] args) {
        PrintWriter writer = null;
        try {
            List<Car> carList = new ArrayList<>();

            carList.add(new Car(2012, "Toyota", "Camry", "Blue"));
            carList.add(new Car(2001, "Honda", "Civic", "Silver"));
            carList.add(new Car(2009, "Jeep", "Wrangler", "Rust"));
            carList.add(new Car(2018, "Tesla", "Roadster", "Black"));
            carList.add(new Car(1964, "Ford", "Mustang", "Red"));

            ObjectMapper mapper = new ObjectMapper();
            String jsonCarList = mapper.writeValueAsString(carList);

            writer = new PrintWriter(new FileWriter("carList.json"));
            writer.println(jsonCarList);

        } catch(JsonProcessingException e) {
            System.out.println(e.getMessage());
        }catch(IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if(writer != null) {
                writer.flush();
                writer.close();
            }
        }

        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Car> carList;

            carList = mapper.readValue(new File("carList.json"), new TypeReference<List<Car>>(){});

            for (Car cars : carList) {
                System.out.println("================");
                System.out.println(cars.getMake());
                System.out.println(cars.getModel());
                System.out.println(cars.getColor());
                System.out.println(cars.getYear());
            }

        } catch (IOException e) {
            System.out.println("ERROR: Problem encountered reading JSON file - " + e.getMessage());
        } finally {
            System.out.println( "Program has finished! Check your JSON file!");
        }
    }
}
