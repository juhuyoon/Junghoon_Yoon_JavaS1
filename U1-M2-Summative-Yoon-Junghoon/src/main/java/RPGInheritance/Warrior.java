package RPGInheritance;

public class Warrior implements Villager {
    private String name;
    private int strength = 75;
    private int health = 100;
    private int stamina = 100;
    private int speed = 50;
    private int attackPower = 10;
    private int shieldStrength = 100;

    public Warrior() {

    }

    //Constructor
    public Warrior(String name, int strength, int health, int stamina, int speed, int attackPower, int shieldStrength) {
        this.name = name;
        this.strength = strength;
        this.health = health;
        this.stamina = stamina;
        this.speed = speed;
        this.attackPower = attackPower;
        this.shieldStrength = shieldStrength;
    }

    //Getters and Setters
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

    public int getShieldStrength() {
        return shieldStrength;
    }

    public void setShieldStrength(int shieldStrength) {
        this.shieldStrength = shieldStrength;
    }

    public void decreaseShieldStrength() {
        System.out.println("Decreasing Shield Strength!");
    }

    //Inherited abilities universal to all.
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
