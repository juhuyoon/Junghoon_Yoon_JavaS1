public class Basement {
    private int length;
    private int width;
    private int totalSize;
    private String flooringType;

    public Basement(int length, int width, int totalSize, String flooringType) {
        this.length = length;
        this.width = width;
        this.totalSize = totalSize;
        this.flooringType = flooringType;
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

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public String getFlooringType() {
        return flooringType;
    }

    public void setFlooringType(String flooringType) {
        this.flooringType = flooringType;
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

    public String whatRunningMachine(String input) {
        String runningMachineName = input;
        return runningMachineName;
    }

    public boolean isLightOn(boolean input) {
        boolean lightOn = input;
        return lightOn;
    }

}
