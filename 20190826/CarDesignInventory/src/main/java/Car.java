public class Car {
//    @CsvBindByName(column = "YEAR")
    private String year;
   /* @CsvBindByName(column = "Make")*/
    private String make;
//    @CsvBindByName(column = "Model")
    private String model;
//    @CsvBindByName(column = "Color")
    private String color;
//    @CsvBindByName(column = "Miles")
    private String mile;

    public Car(String year, String make, String model, String color, String mile) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.mile = mile;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMile() {
        return mile;
    }

    public void setMile(String mile) {
        this.mile = mile;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
