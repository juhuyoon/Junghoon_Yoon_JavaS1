import java.util.Scanner;

public class Nim_63c {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int countA = 3;
        int countB = 4;
        int countC = 5;

        String pile = "";

        //to choose the winner at the end. Player with more points = loser.
        int scoreOne = 0;
        int scoreTwo = 0;

        System.out.println("Player 1, enter your name: ");
        String userName = scan.next();

        System.out.println("Player 2, enter your name: ");
        String userName2 = scan.next();

        while(countA != 0 || countB != 0 || countC != 0) {
            System.out.println("A: " + countA);
            System.out.println("B: " + countB);
            System.out.println("C: " + countC);

            System.out.println(userName + " Choose a pile: ");
            pile = scan.next().toUpperCase();

            if (pile.equals("A")) {
                System.out.print("How many to remove from pile " + pile + ": ");
                int userNumber = Integer.parseInt(scan.next());
                countA -= userNumber;
                scoreOne++;
            } else if (pile.equals("B")) {
                System.out.print("How many to remove from pile " + pile + ": ");
                int userNumber = Integer.parseInt(scan.next());
                countB -= userNumber;
                scoreOne++;
            } else if(pile.equals("C")) {
                System.out.print("How many to remove from pile " + pile + ": ");
                int userNumber = Integer.parseInt(scan.next());
                countC -= userNumber;
                scoreOne++;
            }

            System.out.println("A: " + countA);
            System.out.println("B: " + countB);
            System.out.println("C: " + countC);

            System.out.println(userName2 + " Choose a pile: ");
            pile = scan.next().toUpperCase();

            if (pile.equals("A")) {
                System.out.print("How many to remove from pile " + pile + ": ");
                int userNumber = Integer.parseInt(scan.next());
                countA -= userNumber;
                scoreTwo++;
            } else if (pile.equals("B")) {
                System.out.print("How many to remove from pile " + pile + ": ");
                int userNumber = Integer.parseInt(scan.next());
                countB -= userNumber;
                scoreTwo++;
            } else if(pile.equals("C")) {
                System.out.print("How many to remove from pile " + pile + ": ");
                int userNumber = Integer.parseInt(scan.next());
                countC -= userNumber;
                scoreTwo++;
            }
        }


        if(scoreOne > scoreTwo) {
            System.out.println(userName + " took the last coin. You win " + userName2 + "!");
        } else {
            System.out.println(userName2 + " took the last coin. You win " + userName + "!");
        }
    }
}
