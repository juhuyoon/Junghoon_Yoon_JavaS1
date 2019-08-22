import java.util.Scanner;

public class Main {
    static Inventory carInventory =  new Inventory();

    public static void menu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("==========================");
        System.out.println("What would you like to do?");
        System.out.println("Press 1 to add");
        System.out.println("Press 2 to remove");
        System.out.println("Press 3 to list all");
        System.out.println("Press 4 to search");
        System.out.println("Press 5 to quit");
        int userInput = Integer.parseInt(scan.nextLine());

        Car carAdded = new Car();

        switch(userInput) {
            case (1):
                System.out.println("What year is the car?");
                carAdded.setYear(Integer.parseInt((scan.nextLine())));
                System.out.println("What make is the car?");
                carAdded.setMake(scan.nextLine());
                System.out.println("What model is the car?");
                carAdded.setModel(scan.nextLine());
                System.out.println("What color is the car?");
                carAdded.setColor(scan.nextLine());
                System.out.println("How many miles is on the car?");
                carAdded.setMile(Integer.parseInt(scan.nextLine()));

                carInventory.add(carAdded);
                menu();
                break;
            case(2):
                System.out.println("At which index do you want to remove from the inventory?");
                int removeFromIndex = Integer.parseInt(scan.nextLine());
                carInventory.remove(removeFromIndex);
                menu();
                break;
            case(3):
                carInventory.list();
                menu();
                break;
            case(4):
                System.out.println("What do you want to search by?");
                System.out.println("Press 1 for Make");
                System.out.println("Press 2 for Model");
                System.out.println("Press 3 for Year");
                System.out.println("Press 4 for Color");
                System.out.println("Press 5 for Less Than Certain Number of Miles");
                System.out.println("Press 6 to return to main menu.");
                int searchInput = Integer.parseInt(scan.nextLine());
                switch(searchInput) {
                    case (1):
                        System.out.println("What is the Make you want to search by?");
                        String userMake = scan.nextLine();
                        carInventory.searchByMake(userMake);
                        menu();
                        break;
                    case(2):
                        System.out.println("What is the Model you want to search by?");
                        String userModel = scan.nextLine();
                        carInventory.searchByModel(userModel);
                        menu();
                        break;
                    case(3):
                        System.out.println("What is the Year you want to search by?");
                        int userYear = Integer.parseInt(scan.nextLine());
                        carInventory.searchByYear(userYear);
                        menu();
                        break;
                    case(4):
                        System.out.println("What is the Color you want to search by?");
                        String userColor = scan.nextLine();
                        carInventory.searchByColor(userColor);
                        menu();
                        break;
                    case(5):
                        System.out.println("Input the maximum amount of miles you are ok with having:");
                        int userMiles = Integer.parseInt(scan.nextLine());
                        carInventory.searchByLessThanMiles(userMiles);
                        menu();
                        break;
                    case(6):
                        menu();
                        break;
                    default:
                        System.out.println("Not a valid input. Going back to main menu");
                        menu();
                        break;
                }
                break;
            case(5):
                System.out.println("Good bye!");
                break;
            default:
                System.out.println("Please enter a valid input!");
                menu();
                break;
        }
    }


    public static void main(String[] args) {
        System.out.println("Welcome to Car Inventory!");
        menu();
    }
}
