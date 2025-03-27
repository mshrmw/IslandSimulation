package nature;

public class Plant {
    private double weight;
    private int maxPerCell;
    public Plant() {
        this.weight = 1.0;
        this.maxPerCell = 200;
    }
    public double getWeight() {
        return weight;
    }
    public int getMaxPerCell() {
        return maxPerCell;
    }
}
