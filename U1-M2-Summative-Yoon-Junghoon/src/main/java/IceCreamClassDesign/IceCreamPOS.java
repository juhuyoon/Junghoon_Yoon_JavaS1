package IceCreamClassDesign;

public class IceCreamPOS {
    private int vanillaIceCream;
    private int chocolateIceCream;
    private int strawberryIceCream;

    private int iceCreamCones;
    private int waffleCones;

    private String iceCreamSign;
    private String menu;
    private  double registrar;

    public IceCreamPOS(int vanillaIceCream, int chocolateIceCream, int strawberryIceCream, int iceCreamCones, int waffleCones, String iceCreamSign, String menu, double registrar) {
        this.vanillaIceCream = vanillaIceCream;
        this.chocolateIceCream = chocolateIceCream;
        this.strawberryIceCream = strawberryIceCream;
        this.iceCreamCones = iceCreamCones;
        this.waffleCones = waffleCones;
        this.iceCreamSign = iceCreamSign;
        this.menu = menu;
        this.registrar = registrar;
    }

    public void putUpSign() {
        System.out.println("Welcome to !");
    }

    public double registerMoney() {
        return registrar;
    }

    public int countCones() {
        int count = 0;
        return count;
    }

    public int getVanillaIceCream() {
        return vanillaIceCream;
    }

    public void setVanillaIceCream(int vanillaIceCream) {
        this.vanillaIceCream = vanillaIceCream;
    }

    public int getChocolateIceCream() {
        return chocolateIceCream;
    }

    public void setChocolateIceCream(int chocolateIceCream) {
        this.chocolateIceCream = chocolateIceCream;
    }

    public int getStrawberryIceCream() {
        return strawberryIceCream;
    }

    public void setStrawberryIceCream(int strawberryIceCream) {
        this.strawberryIceCream = strawberryIceCream;
    }

    public int getIceCreamCones() {
        return iceCreamCones;
    }

    public void setIceCreamCones(int iceCreamCones) {
        this.iceCreamCones = iceCreamCones;
    }

    public int getWaffleCones() {
        return waffleCones;
    }

    public void setWaffleCones(int waffleCones) {
        this.waffleCones = waffleCones;
    }

    public String getIceCreamSign() {
        return iceCreamSign;
    }

    public void setIceCreamSign(String iceCreamSign) {
        this.iceCreamSign = iceCreamSign;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public double getRegistrar() {
        return registrar;
    }

    public void setRegistrar(double registrar) {
        this.registrar = registrar;
    }
}
