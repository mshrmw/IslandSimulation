package nature.predators;

import nature.Animal;

public abstract class Predator extends Animal {
    public Predator(double maxSatiety, int speed, double weight, int maxPerCell) {
        super(maxSatiety, speed, weight, maxPerCell);
    }
    protected boolean tryEatAnimal(Animal prey, double probability) {
        if (random.nextDouble() < probability) {
            satiety = Math.min(maxSatiety, satiety + prey.weight * 0.5);
            return true;
        }
        return false;
    }
}

