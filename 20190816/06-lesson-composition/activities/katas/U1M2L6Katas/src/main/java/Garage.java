public class Garage {
    private int totalSize;
    private int length;
    private int width;
    private int doors;
    private int light;

    public Garage(int totalSize, int length, int width, int doors, int light) {
        this.totalSize = totalSize;
        this.length = length;
        this.width = width;
        this.doors = doors;
        this.light = light;
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

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public int getArea(int length, int width) {
        this.length = length;
        this.width = width;
        return this.length * this.width;
    }

    public int checkDoorAmount(int input) {
        return this.doors = input;
    }

    public int checkLightAmount(int input) {
        return this.light = input;
    }
}
