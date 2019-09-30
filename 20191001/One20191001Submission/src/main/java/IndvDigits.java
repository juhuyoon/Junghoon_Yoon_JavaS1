public class IndvDigits {
    public static void returnLoop() {
        for(int i = 10; i < 100; i++) {
            System.out.printf(i + ", ");
            for(int j = i; j < i+1; j++) {
                int posOne = (i / 10);
                int posTwo = (i % 10);
                int sum = posOne + posTwo;
                System.out.println(posOne + "+" + posTwo + " = " + sum);
            }
        }
    }
    public static void main(String[] args) {
        returnLoop();
    }
}
