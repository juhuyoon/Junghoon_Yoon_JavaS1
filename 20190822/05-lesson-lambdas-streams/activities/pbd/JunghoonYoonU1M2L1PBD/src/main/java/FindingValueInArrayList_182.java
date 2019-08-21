import java.util.ArrayList;
import java.util.Scanner;

public class FindingValueInArrayList_182 {
    public static void main(String[] args) {
        ArrayList<Integer> myArray = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        for(int i = myArray.size(); i < 10; i++) {
            int min = 1;
            int max = 50;
            int range = (max - min) + 1;
            int randomA = (int)(Math.random() * range) + min;
            myArray.add(randomA);
        }

        System.out.println("Arraylist: " + myArray);
        System.out.print("Value to find: ");
        int userInput = Integer.parseInt(scan.next());


        for(int i = 0; i < myArray.size(); i++) {
            if(userInput == myArray.get(i)) {
                System.out.println(userInput + " is in the ArrayList.");
            }
        }
    }
}
