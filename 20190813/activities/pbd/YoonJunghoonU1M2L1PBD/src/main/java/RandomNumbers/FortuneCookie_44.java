package RandomNumbers;

import java.util.Random;

public class FortuneCookie_44 {
    public static void main(String[] args) {
        Random rand = new Random();

        int bonusLotto1 = 1 + rand.nextInt(54);
        int bonusLotto2 = 1 + rand.nextInt(54);
        int bonusLotto3 = 1 + rand.nextInt(54);
        int bonusLotto4 = 1 + rand.nextInt(54);
        int bonusLotto5 = 1 + rand.nextInt(54);
        int bonusLotto6 = 1 + rand.nextInt(54);


        int counter = 1 + rand.nextInt(6);

        switch(counter) {
            case 1 :
                System.out.println("Fortune cookie says: You will find happiness with a new love.");
                System.out.println(bonusLotto1 + " " + bonusLotto2 + " " + bonusLotto3 + " " + bonusLotto4 + " " + bonusLotto5 + " " + bonusLotto6);
                break;
            case 2 :
                System.out.println("Fortune cookie says: Stick with your wife.");
                System.out.println(bonusLotto1 + " " + bonusLotto2 + " " + bonusLotto3 + " " + bonusLotto4 + " " + bonusLotto5 + " " + bonusLotto6);
                break;
            case 3 :
                System.out.println("Fortune cookie says: The fortune you seek is in another cookie.");
                System.out.println(bonusLotto1 + " " + bonusLotto2 + " " + bonusLotto3 + " " + bonusLotto4 + " " + bonusLotto5 + " " + bonusLotto6);
                break;
            case 4 :
                System.out.println("Fortune cookie says: A closed mouth gathers no feet.");
                System.out.println(bonusLotto1 + " " + bonusLotto2 + " " + bonusLotto3 + " " + bonusLotto4 + " " + bonusLotto5 + " " + bonusLotto6);
                break;
            case 5 :
                System.out.println("Fortune cookie says: A cynic is only a frustrated optimist.");
                System.out.println(bonusLotto1 + " " + bonusLotto2 + " " + bonusLotto3 + " " + bonusLotto4 + " " + bonusLotto5 + " " + bonusLotto6);
                break;
            case 6 :
                System.out.println("Fortune cookie says: You will die alone and poorly dressed.");
                System.out.println(bonusLotto1 + " " + bonusLotto2 + " " + bonusLotto3 + " " + bonusLotto4 + " " + bonusLotto5 + " " + bonusLotto6);
                break;
        }
    }
}
