public class Bathroom {
    private int totalSize;
    private int length;
    private int width;
    private String flooringType;
    private String halfOrFull;

    public Bathroom(int totalSize, int length, int width, String flooringType) {
        this.totalSize = totalSize;
        this.length = length;
        this.width = width;
        this.flooringType = flooringType;
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

    public String halfOrFull(String input) {
        return input;
    }

    public boolean isWaterRunning(String input) {
        if(input.equals("Yes")) {
            return true;
        } else {
            return false;
        }
    }
}
