import java.util.ArrayList;

public class BasicArrayLists3_180 {
    public static void main(String[] args) {
        ArrayList<Integer> myArray = new ArrayList<>(1000);
        for(int i = myArray.size(); i < 1000; i++) {
            int min = 10;
            int max = 99;
            int range = max - min + 1;
            int randomA = (int)(Math.random() * range) + min;
            myArray.add(randomA);
        }

        System.out.println(myArray);
    }
}
