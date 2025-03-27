package island;

import nature.Animal;
import nature.Plant;
import nature.herbivore.*;
import nature.predators.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cell implements Runnable {
    private final Island island;
    private final int x, y;
    private final List<Animal> animals = new CopyOnWriteArrayList<>();
    private List<Plant> plants = new ArrayList<>();
    private final Random random = new Random();
    public Cell(Island island, int x, int y) {
        this.island = island;
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public List<Animal> getAnimals() {
        return animals;
    }
    public void addAnimals() {
        if (random.nextDouble() > 0.7) {
            return;
        }
        int animalsCount = random.nextInt(3) + 1;
        for (int i = 0; i < animalsCount; i++) {
            int animalType = random.nextInt(15);
            switch (animalType) {
                case 0: animals.add(new Wolf()); break;
                case 1: animals.add(new Rabbit()); break;
                case 2: animals.add(new Fox()); break;
                case 3: animals.add(new Deer()); break;
                case 4: animals.add(new Bear()); break;
                case 5: animals.add(new Boa()); break;
                case 6: animals.add(new Eagle()); break;
                case 7: animals.add(new Horse()); break;
                case 8: animals.add(new Mouse()); break;
                case 9: animals.add(new Goat()); break;
                case 10: animals.add(new Sheep()); break;
                case 11: animals.add(new Buffalo()); break;
                case 12: animals.add(new Duck()); break;
                case 13: animals.add(new Caterpillar()); break;
                case 14: animals.add(new Boar()); break;
            }
        }
    }
    public void addPlants() {
        int currentCount = plants.size();
        int canAdd = new Plant().getMaxPerCell() - currentCount;
        if (canAdd > 0) {
            int toAdd = random.nextInt(canAdd) + 1;
            for (int i = 0; i < toAdd; i++) {
                plants.add(new Plant());
            }
        }
    }
    public void removePlant() {
        if (!plants.isEmpty()) {
            plants.remove(plants.size() - 1);
        }
    }
    public int getPlantsCount() {
        return plants.size();
    }
    public Plant getPlant() {
        return plants.isEmpty() ? null : plants.get(0);
    }
    public int getMaxCapacity() {
        return animals.stream()
                .mapToInt(a -> a.maxPerCell)
                .max()
                .orElse(Integer.MAX_VALUE);
    }
    @Override
    public void run() {
        try {
            //движение
            List<Animal> animalsToMove = new ArrayList<>(animals);
            for (Animal animal : animalsToMove) {
                animal.move(this, island);
            }
            //питание
            animals.forEach(animal -> animal.eat(this));
            // размножение
            List<Animal> newAnimals = new ArrayList<>();
            animals.forEach(animal -> animal.reproduce(this, newAnimals));
            animals.addAll(newAnimals);
            //проверка голода и смертность
            animals.removeIf(animal -> {
                if (animal.isStarving()) {
                    System.out.printf("%s умер от голода в клетке [%d,%d]%n",
                            animal.getClass().getSimpleName(), x, y);
                    return true;
                }
                return false;
            });
            //растения
            if (random.nextDouble() < 0.3) {
                addPlants();
            }
        } catch (Exception e) {
            System.err.println("Ошибка в клетке [" + x + "," + y + "]: " + e.getMessage());
        }
    }
}

