import java.util.Scanner;

public class BabyNim_63b {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int countA = 3;
        int countB = 3;
        int countC = 3;

        while(countA != 0 || countB != 0 || countC != 0) {
            System.out.println("A: " + countA);
            System.out.println("B: " + countB);
            System.out.println("C: " + countC);

            System.out.print("Choose a pile: ");
            String userInput = scan.next().toUpperCase();

            System.out.print("How many to remove from pile " + userInput + ": ");
            int userNumber = Integer.parseInt(scan.next());

            if (userInput.equals("A")) {
                 countA -= userNumber;
            } else if (userInput.equals("B")) {
                countB -= userNumber;
            } else if(userInput.equals("C")) {
                countC -= userNumber;
            }
        };
        if(countA == 0 && countB == 0 && countC == 0) {
            System.out.println("A: " + countA);
            System.out.println("B: " + countB);
            System.out.println("C: " + countC);
            System.out.println("All piles are empty. Good job!");
        }
    }
}
