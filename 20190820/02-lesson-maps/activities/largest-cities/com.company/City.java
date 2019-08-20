import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class City {
    String name;
    Integer population;

    public City(String name, Integer population) {
        this.name = name;
        this.population = population;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        City newYork = new City("New York", 8654321);
        City lA = new City("Los Angeles", 4563218);
        City chicago = new City("Chicago", 2716520);
        City denver = new City("Denver", 704621);
        City desMoines = new City("Des Moines", 217521);
        City atlanta = new City("Atlanta", 486213);

//        Map<String, Integer> City = new HashMap<>();
//        //The Key values have to be the state
//        City.put("New York", 8654321);
//        City.put("Los Angeles", 4563218);
//        City.put("Chicago", 2716520);
//        City.put("Denver", 704621);
//        City.put("Des Moines", 217521);
//        City.put("Atlanta", 486213);
//
//        Set<String> cityKeys = City.keySet();

//        for(String city: cityKeys) {
//            System.out.println("City name: " + city + " Value: " + City.get(city));
//        }

        //System.out.println(("=========================="));

        System.out.println("Give us a population number and we'll give you back all the cities over that value!");
        Integer userPop = Integer.parseInt(scan.nextLine());

        Map<String, City> State = new HashMap<>();
        State.put("New York", newYork);
        State.put("California", lA);
        State.put("Michigan", chicago);
        State.put("Colorado", denver);
        State.put("Minnesota", desMoines);
        State.put("Georgia", atlanta);


        Set<Map.Entry<String, City>> myStates = State.entrySet();
        for(Map.Entry<String, City> stateNames: myStates) {
            if (stateNames.getValue().population > userPop) {
                System.out.println("State: " + stateNames.getKey() + " City name: " + stateNames.getValue().name + " Population: " + stateNames.getValue().population);
            }
        }
    }
}
