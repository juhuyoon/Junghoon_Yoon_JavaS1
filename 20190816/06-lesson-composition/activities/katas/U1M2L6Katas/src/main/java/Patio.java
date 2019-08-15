public class Patio {
    private int totalSize;
    private int length;
    private int width;
    private String flooringType;
    private String rockingChair;

    public Patio(int totalSize, int length, int width, String flooringType, String rockingChair) {
        this.totalSize = totalSize;
        this.length = length;
        this.width = width;
        this.flooringType = flooringType;
        this.rockingChair = rockingChair;
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

    public String getRockingChair() {
        return rockingChair;
    }

    public void setRockingChair(String rockingChair) {
        this.rockingChair = rockingChair;
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
