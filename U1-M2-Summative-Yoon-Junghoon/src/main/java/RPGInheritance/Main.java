package RPGInheritance;

public class Main {
    public static void main(String[] args) {
        Farmer farmer = new Farmer();
        Warrior warrior = new Warrior();
        Constable constable = new Constable();

        warrior.attack();
        warrior.getAttackPower();

        farmer.setName("Kaya");
        System.out.println(farmer.getName());

        constable.setName("Judge Judy");
        System.out.println(constable.getName());


    }
}
