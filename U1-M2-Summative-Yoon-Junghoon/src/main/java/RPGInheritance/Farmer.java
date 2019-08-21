package RPGInheritance;

public class Farmer implements Villager {
    //Farmer Properties
    private String name;
    private int strength = 75;
    private int health = 100;
    private int stamina = 75;
    private int speed = 10;
    private int attackPower = 1;

    public Farmer() {

    }

    //Constructor
    public Farmer(String name, int strength, int health, int stamina, int speed, int attackPower) {
        this.name = name;
        this.strength = strength;
        this.health = health;
        this.stamina = stamina;
        this.speed = speed;
        this.attackPower = attackPower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    //Unique abilities
    public void plow() {
        System.out.println("Plowing!");
    }

    public void harvest() {
        System.out.println("Harvesting!");
    }

    //Implemented methods universal to all.
    @Override
    public void run() {
        System.out.println("Running!");
    }

    @Override
    public void attack() {
        System.out.println("Attack!");
    }

    @Override
    public void heal() {
        System.out.println("Healing!");
    }

    @Override
    public void decreaseHealth() {
        System.out.println("Decreasing Health!");
    }

    @Override
    public void increaseStamina() {
        System.out.println("Increasing Stamina!");
    }

    @Override
    public void decreaseStamina() {
        System.out.println("Decreasing Stamina!");
    }
}
