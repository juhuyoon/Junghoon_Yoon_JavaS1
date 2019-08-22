import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Car> carInventory = new ArrayList<>();


    public void add(Car car){
        carInventory.add(car);
    }

    public void remove(int num) {
        carInventory.remove(num);
    }

    public void list() {
        for(Car cars: carInventory) {
            System.out.println("==========================");
            System.out.println("Year: " + cars.getYear());
            System.out.println("Make: " + cars.getMake());
            System.out.println("Model: " + cars.getModel());
            System.out.println("Color: " + cars.getColor());
            System.out.println("Miles: " + cars.getMile());
        };
    }

    public void searchByMake(String make) {
      carInventory.stream().filter(m -> m.getMake().equals(make))
                                           .forEach(ma -> {
                                               System.out.println("==========================");
                                               System.out.println("Year: " + ma.getYear());
                                               System.out.println("Make: " + ma.getMake());
                                               System.out.println("Model: " + ma.getModel());
                                               System.out.println("Color: " + ma.getColor());
                                               System.out.println("Miles: " + ma.getMile());
                                           });
    }

    public void searchByModel(String model) {
        carInventory.stream().filter(m -> m.getModel().equals(model))
                .forEach(md -> {
                    System.out.println("==========================");
                    System.out.println("Year: " + md.getYear());
                    System.out.println("Make: " + md.getMake());
                    System.out.println("Model: " + md.getModel());
                    System.out.println("Color: " + md.getColor());
                    System.out.println("Miles: " + md.getMile());
                });
    }

    public void searchByYear(int year) {
        carInventory.stream().filter(m -> m.getYear() == (year))
                .forEach(yr -> {
                    System.out.println("==========================");
                    System.out.println("Year: " + yr.getYear());
                    System.out.println("Make: " + yr.getMake());
                    System.out.println("Model: " + yr.getModel());
                    System.out.println("Color: " + yr.getColor());
                    System.out.println("Miles: " + yr.getMile());
                });
    }

    public void searchByColor(String color) {
        carInventory.stream().filter(m -> m.getColor().equals(color))
                .forEach(co -> {
                    System.out.println("==========================");
                    System.out.println("Year: " + co.getYear());
                    System.out.println("Make: " + co.getMake());
                    System.out.println("Model: " + co.getModel());
                    System.out.println("Color: " + co.getColor());
                    System.out.println("Miles: " + co.getMile());
                });
    }

    public void searchByLessThanMiles(int miles) {
        carInventory.stream().filter(m -> m.getMile() < miles)
                             .forEach(ms -> {
                                 System.out.println("==========================");
                                 System.out.println("Year: " + ms.getYear());
                                 System.out.println("Make: " + ms.getMake());
                                 System.out.println("Model: " + ms.getModel());
                                 System.out.println("Color: " + ms.getColor());
                                 System.out.println("Miles: " + ms.getMile());
                             });
    }
}
