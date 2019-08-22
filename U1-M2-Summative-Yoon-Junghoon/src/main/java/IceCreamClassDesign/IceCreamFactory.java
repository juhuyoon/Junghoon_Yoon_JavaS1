package IceCreamClassDesign;

public class IceCreamFactory {
    //Ingredients
    private double flour;
    private double ice;
    private double cream;
    private double milk;
    private double sugar;
    private double chocolate;

    //Packaging and Shipping
    private int packaging;
    private String deliveryRoutes;

    //Constructor
    public IceCreamFactory(double flour, double ice, double cream, double milk, double sugar, double chocolate, int packaging, String deliveryRoutes) {
        this.flour = flour;
        this.ice = ice;
        this.cream = cream;
        this.milk = milk;
        this.sugar = sugar;
        this.chocolate = chocolate;
        this.packaging = packaging;
        this.deliveryRoutes = deliveryRoutes;
    }

    public void mixer() {

    }

    public void measureIngredients() {

    }

    public int packAndCount() {
        int count = 0;
        return packaging + count;
    }

    public String gatherDeliveryRoutes() {
        return getDeliveryRoutes();
    }

    //Getters & Setters
    public double getFlour() {
        return flour;
    }

    public void setFlour(double flour) {
        this.flour = flour;
    }

    public double getIce() {
        return ice;
    }

    public void setIce(double ice) {
        this.ice = ice;
    }

    public double getCream() {
        return cream;
    }

    public void setCream(double cream) {
        this.cream = cream;
    }

    public double getMilk() {
        return milk;
    }

    public void setMilk(double milk) {
        this.milk = milk;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getChocolate() {
        return chocolate;
    }

    public void setChocolate(double chocolate) {
        this.chocolate = chocolate;
    }

    public int getPackaging() {
        return packaging;
    }

    public void setPackaging(int packaging) {
        this.packaging = packaging;
    }

    public String getDeliveryRoutes() {
        return deliveryRoutes;
    }

    public void setDeliveryRoutes(String deliveryRoutes) {
        this.deliveryRoutes = deliveryRoutes;
    }
}
