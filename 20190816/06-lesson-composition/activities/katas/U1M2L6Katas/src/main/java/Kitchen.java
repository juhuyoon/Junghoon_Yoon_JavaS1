public class Kitchen {
    private String microWave;
    private int totalSize;
    private int length;
    private int width;
    private String refrigerator;
    private String coffeeMaker;
    private String flooringType;

    public Kitchen(String microWave, int totalSize, int length, int width, String refrigerator, String coffeeMaker, String flooringType) {
        this.microWave = microWave;
        this.totalSize = totalSize;
        this.length = length;
        this.width = width;
        this.refrigerator = refrigerator;
        this.coffeeMaker = coffeeMaker;
        this.flooringType = flooringType;
    }

    public String getMicroWave() {
        return microWave;
    }

    public void setMicroWave(String microWave) {
        this.microWave = microWave;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getRefrigerator() {
        return refrigerator;
    }

    public void setRefrigerator(String refrigerator) {
        this.refrigerator = refrigerator;
    }

    public String getCoffeeMaker() {
        return coffeeMaker;
    }

    public void setCoffeeMaker(String coffeeMaker) {
        this.coffeeMaker = coffeeMaker;
    }

    public int getArea(int length, int width) {
        this.length = length;
        this.width = width;
        return this.length * this.width;
    }

    public String askFloorType(String input) {
        this.flooringType = input;
        return this.flooringType;
    }

}
