package nature;

import _enum.Direction;
import island.Cell;
import island.Island;

import java.util.List;
import java.util.Random;

public abstract class Animal {
    protected double satiety;
    protected final double maxSatiety;
    protected final int speed;
    public final double weight;
    public final int maxPerCell;
    protected final Random random = new Random();
    public Animal(double maxSatiety, int speed, double weight, int maxPerCell) {
        this.maxSatiety = maxSatiety;
        this.satiety = maxSatiety / 2;
        this.speed = speed;
        this.weight = weight;
        this.maxPerCell = maxPerCell;
    }
    public abstract void eat(Cell cell);
    public void reproduce(Cell currentCell, List<Animal> newAnimals) {
        if (satiety < maxSatiety * 0.5) {
            return;
        }
        long sameTypeCount = currentCell.getAnimals().stream()
                .filter(a -> a.getClass() == this.getClass())
                .count();
        if (sameTypeCount < maxPerCell) {
            try {
                Animal offspring = this.getClass().getDeclaredConstructor().newInstance();
                newAnimals.add(offspring);
                System.out.printf("%s размножился в клетке [%d,%d] (новый %s)%n",
                        this.getClass().getSimpleName(),
                        currentCell.getX(), currentCell.getY(),
                        offspring.getClass().getSimpleName());
            } catch (Exception e) {
                System.err.printf("Ошибка при размножении %s в клетке [%d,%d]: %s%n", this.getClass().getSimpleName(), currentCell.getX(), currentCell.getY(), e.getMessage());
            }
        }
    }
    public void move(Cell currentCell, Island island) {
        int moves = random.nextInt(speed) + 1;
        Cell targetCell = currentCell;
        for (int i = 0; i < moves; i++) {
            Direction dir = Direction.values()[random.nextInt(4)];
            Cell nextCell = island.getNeighborCell(targetCell.getX(), targetCell.getY(), dir);
            if (nextCell != null && nextCell.getAnimals().size() < nextCell.getMaxCapacity()) {
                targetCell = nextCell;
            }
        }
        if (targetCell != currentCell) {
            targetCell.getAnimals().add(this);
            currentCell.getAnimals().remove(this);
            System.out.printf("%s переместился из [%d,%d] в [%d,%d]%n", this.getClass().getSimpleName(), currentCell.getX(), currentCell.getY(), targetCell.getX(), targetCell.getY());
        }
    }
    public boolean isStarving() {
        return satiety <= 0;
    }
    public void decreaseSatiety() {
        satiety -= maxSatiety * 0.05;
        if (isStarving()) {
            satiety = 0;
        }
    }
}

