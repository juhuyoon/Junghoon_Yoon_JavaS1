package com.company;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVWriteAndRead {
    public static void main(String[] args) {
        Writer writer = null;

            List<Car> carList = new ArrayList<>();

            carList.add(new Car(2012, "Toyota", "Camry", "Blue"));
            carList.add(new Car(2001, "Honda", "Civic", "Silver"));
            carList.add(new Car(2009, "Jeep", "Wrangler", "Rust"));
            carList.add(new Car(2018, "Tesla", "Roadster", "Black"));
            carList.add(new Car(1964, "Ford", "Mustang", "Red"));
        try{
            //Write the motorcycle list to another file
            writer = new FileWriter("carList.csv");
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            beanToCsv.write(carList);
            writer.flush();
            writer.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        } catch(
        CsvDataTypeMismatchException e) {
            System.out.println(e.getMessage());
        } catch(
        CsvRequiredFieldEmptyException e) {
            System.out.println(e.getMessage());
        }finally {
            System.out.println("Program ran and finished!");
        }

        try{
            carList = new CsvToBeanBuilder<Car>(new FileReader("carList.csv")).withType(Car.class).build().parse();
            for(Car carParts: carList) {
                System.out.println("=======================");
                System.out.println(carParts.getColor());
                System.out.println(carParts.getModel());
                System.out.println(carParts.getMake());
                System.out.println(carParts.getYear());

            }
        }catch(FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
