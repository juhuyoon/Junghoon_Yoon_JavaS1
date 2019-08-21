package RPGInheritance;

public class Constable implements Villager {
    //Constable Properties
    private String name;
    private int strength = 60;
    private int health = 100;
    private int stamina = 60;
    private int speed = 20;
    private int attackPower = 5;
    private boolean jurisdiction;

    public Constable() {

    }

    //Constructor
    public Constable(String name, int strength, int health, int stamina, int speed, int attackPower, boolean jurisdiction) {
        this.name = name;
        this.strength = strength;
        this.health = health;
        this.stamina = stamina;
        this.speed = speed;
        this.attackPower = attackPower;
        this.jurisdiction = jurisdiction;
    }


    //Getters & Setters
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

    public boolean isJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(boolean jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    //Unique ability for Constable
    public void arrest() {
        System.out.println("Arresting!");
    }

    //Implemented methods from Villager
    @Override
    public void run() {
        System.out.println("Running!");
    }

    @Override
    public void attack() {
        System.out.println("Attacking!");
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
