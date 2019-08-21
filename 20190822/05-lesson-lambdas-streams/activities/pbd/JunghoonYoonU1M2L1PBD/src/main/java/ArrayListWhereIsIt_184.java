import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListWhereIsIt_184 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> myArray = new ArrayList<>();
        for(int i = myArray.size(); i < 10; i++) {
            int min = 1;
            int max = 50;
            int range = (max - min) + 1;
            int randomA = (int)(Math.random() * range) + min;

            myArray.add(randomA);
        }
        System.out.println("ArrayList: " + myArray);
        System.out.println("Value to find: ");
        int userInput = Integer.parseInt(scan.next());

        if(myArray.contains(userInput)) {
            int indexLocation = myArray.indexOf(userInput);
            System.out.println(userInput + " is in slot " + indexLocation + ".");
        }
        if(!myArray.contains(userInput)) {
            System.out.println(userInput + " is not in the ArrayList.");
        }
    }
}
