import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Car> carInventory = new ArrayList<>();


    public List<Car> add(Car car){
        carInventory.add(car);
        try {
            for(Car cars : carInventory) {
                //append true = no overwriting each time, just adding on.
                Writer writer = new FileWriter("cars.csv", true);

                StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
                beanToCsv.write(carInventory);
                writer.close();
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvRequiredFieldEmptyException e) {
            System.out.println(e.getMessage());
        } catch (CsvDataTypeMismatchException e) {
            System.out.println(e.getMessage());
        }
        return carInventory;
    }

    public List<Car> remove(String num) {
        carInventory.removeIf(n -> n.equals(num));
        try{
            for(Car cars: carInventory) {
                Writer writer = new FileWriter("cars.csv", true);
                StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
                beanToCsv.write(carInventory);
                writer.close();
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvRequiredFieldEmptyException e) {
            System.out.println(e.getMessage());
        } catch (CsvDataTypeMismatchException e) {
            System.out.println(e.getMessage());
        }
        return carInventory;
    }

    public void list() {
        try{
            List<Car> cars = new CsvToBeanBuilder<Car>(new FileReader("cars.csv")).withType(Car.class).build().parse();

            for(Car car: cars) {
                System.out.println("==========================");
                System.out.println(car.getYear());
                System.out.println(car.getMake());
                System.out.println(car.getModel());
                System.out.println(car.getColor());
                System.out.println(car.getMile());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchByMake(String make) {
        try {
            List<Car> cars = new CsvToBeanBuilder<Car>(new FileReader("cars.csv")).withType(Car.class).build().parse();

            cars.stream().filter(m -> m.getMake().equals(make)).forEach(ma -> {
                System.out.println("==========================");
                System.out.println("Year: " + ma.getYear());
                System.out.println("Make: " + ma.getMake());
                System.out.println("Model: " + ma.getModel());
                System.out.println("Color: " + ma.getColor());
                System.out.println("Miles: " + ma.getMile());
            });
        }
        catch(IOException e) {
            e.getMessage();
        }
    }

    public void searchByModel(String model) {
        try {
            List<Car> cars = new CsvToBeanBuilder<Car>(new FileReader("cars.csv")).withType(Car.class).build().parse();

            cars.stream().filter(m -> m.getModel().equals(model)).forEach(ml -> {
                System.out.println("==========================");
                System.out.println("Year: " + ml.getYear());
                System.out.println("Make: " + ml.getMake());
                System.out.println("Model: " + ml.getModel());
                System.out.println("Color: " + ml.getColor());
                System.out.println("Miles: " + ml.getMile());
            });
        }
        catch(IOException e) {
            e.getMessage();
        }
    }

    public void searchByYear(String year) {
        try {
            List<Car> cars = new CsvToBeanBuilder<Car>(new FileReader("cars.csv")).withType(Car.class).build().parse();

            cars.stream().filter(m -> m.getYear().equals(year)).forEach(yr -> {
                System.out.println("==========================");
                System.out.println("Year: " + yr.getYear());
                System.out.println("Make: " + yr.getMake());
                System.out.println("Model: " + yr.getModel());
                System.out.println("Color: " + yr.getColor());
                System.out.println("Miles: " + yr.getMile());
            });
        }
        catch(IOException e) {
            e.getMessage();
        }
    }

    public void searchByColor(String color) {
        try {
            List<Car> cars = new CsvToBeanBuilder<Car>(new FileReader("cars.csv")).withType(Car.class).build().parse();

            cars.stream().filter(m -> m.getYear().equals(color)).forEach(cr -> {
                System.out.println("==========================");
                System.out.println("Year: " + cr.getYear());
                System.out.println("Make: " + cr.getMake());
                System.out.println("Model: " + cr.getModel());
                System.out.println("Color: " + cr.getColor());
                System.out.println("Miles: " + cr.getMile());
            });
        }
        catch(IOException e) {
            e.getMessage();
        }
    }

    public void searchByLessThanMiles(String miles) {
        try {
            List<Car> cars = new CsvToBeanBuilder<Car>(new FileReader("cars.csv")).withType(Car.class).build().parse();

            cars.stream().filter(m -> Integer.parseInt(m.getMile()) < Integer.parseInt(miles)).forEach(ms -> {
                System.out.println("==========================");
                System.out.println("Year: " + ms.getYear());
                System.out.println("Make: " + ms.getMake());
                System.out.println("Model: " + ms.getModel());
                System.out.println("Color: " + ms.getColor());
                System.out.println("Miles: " + ms.getMile());
            });
        }
        catch(IOException e) {
            e.getMessage();
        } catch (NumberFormatException e) {
            e.getMessage();
        }
    }
}

