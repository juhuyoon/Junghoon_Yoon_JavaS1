import java.util.ArrayList;

public class CopyingArrayLists_181 {
    public static void main(String[] args) {
        ArrayList<Integer> myArray = new ArrayList<>(10);
        ArrayList<Integer> myArray2 = new ArrayList<>(10);

        for(int i = myArray.size(); i < 10; i++) {
            int min = 1;
            int max = 100;
            int range = max - min +1;
            int randomA = (int)(Math.random() * range) + min;
            myArray.add(randomA);
        }

        for(int j = myArray2.size(); j < 10; j++) {
            myArray2.add(myArray.get(j));
        }

        myArray.remove(myArray.size()-1);
        myArray.add(-7);


        System.out.println("ArrayList 1: " + myArray);
        System.out.println("ArrayList 2: " + myArray2);
    }
}
