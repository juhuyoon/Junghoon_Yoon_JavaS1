public class Home {

    private String homeName;

    private Basement basement;
    private Bathroom bathroom;
    private Kitchen kitchen;
    private Patio patio;
    private Garage garage;

    public Home(String homeName) {
        this.homeName = homeName;
        this.basement = new Basement(10, 10, 100, "Hardwood");
        this.bathroom = new Bathroom(100, 10, 10, "Carpet");
        this.kitchen = new Kitchen("Samsung", 101, 10, 10, "LG", "Dodgers", "Hardwood");
        this.patio = new Patio(100, 10, 10, "Hardwood", "SomeChair");
        this.garage = new Garage(100, 10, 10, 1, 2);
    }


    public static void main(String[] args) {
        Home home = new Home("Jungs");

        System.out.println(home.basement.whatRunningMachine("Travelers"));
        System.out.println(home.bathroom.getArea(10, 10));
        System.out.println(home.kitchen.getMicroWave());
        System.out.println(home.patio.getFlooringType());
        System.out.println(home.bathroom.isWaterRunning("Yes"));
        System.out.println(home.garage.checkDoorAmount(1));

    }
}
