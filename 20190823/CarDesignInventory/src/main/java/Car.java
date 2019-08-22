public class Car {
    private String make;
    private String model;
    private int year;
    private int mile;
    private String color;

    public Car(String make, String model, int year, int mile, String color) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.mile = mile;
        this.color = color;
    }

    public Car() {

    }



    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMile() {
        return mile;
    }

    public void setMile(int mile) {
        this.mile = mile;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
