package island;

import _enum.Direction;
import nature.Animal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Island {
    private final int width, height;
    private final Cell[][] cells;
    private final ExecutorService executor;
    private final ScheduledExecutorService scheduler;
    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[height][width];
        this.executor = Executors.newFixedThreadPool(4);
        this.scheduler = Executors.newScheduledThreadPool(1);
    }
    public void initialize() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[y][x] = new Cell(this, x, y);
                cells[y][x].addAnimals();
                cells[y][x].addPlants();
            }
        }
    }
    public boolean shouldStopSimulation() {
        Map<Class<? extends Animal>, Integer> speciesCount = new HashMap<>();
        int totalAnimals = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                for (Animal animal : cells[y][x].getAnimals()) {
                    Class<? extends Animal> animalClass = animal.getClass();
                    speciesCount.put(animalClass, speciesCount.getOrDefault(animalClass, 0) + 1);
                    totalAnimals++;
                }
            }
        }

        return totalAnimals == 0 || speciesCount.size() <= 1;
    }
    public void startSimulation(int maxDays) {
        ScheduledFuture<?> simulationTask = scheduler.scheduleAtFixedRate(() -> {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    executor.execute(cells[y][x]);
                }
            }
            printStatistics();
            if (shouldStopSimulation()) {
                System.out.println("Симуляция завершена: на острове остались животные только одного вида или не осталось животных вообще!");
                scheduler.shutdown();
                executor.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);
        scheduler.schedule(() -> {
            if (!scheduler.isShutdown()) {
                System.out.println("Симуляция завершена по истечении максимального времени (" + maxDays + " дней)");
                scheduler.shutdown();
                executor.shutdown();
            }
        }, maxDays, TimeUnit.SECONDS);
    }
    public void printStatistics() {
        Map<String, AtomicInteger> stats = new HashMap<>();
        AtomicInteger plantsCount = new AtomicInteger(0);
        AtomicInteger speciesCount = new AtomicInteger(0);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Считаем животных
                for (Animal animal : cells[y][x].getAnimals()) {
                    String key = animal.getClass().getSimpleName();
                    if (stats.computeIfAbsent(key, k -> new AtomicInteger(0)).getAndIncrement() == 0) {
                        speciesCount.incrementAndGet();
                    }
                }
                plantsCount.addAndGet(cells[y][x].getPlantsCount());
            }
        }
        System.out.println("\n--- Статистика острова ---");
        System.out.println("Количество видов животных: " + speciesCount.get());
        stats.forEach((name, count) -> System.out.println(name + ": " + count));
        System.out.println("Растения: " + plantsCount.get());
        System.out.println("--------------------------");
    }
    public Cell getNeighborCell(int x, int y, Direction direction) {
        switch (direction) {
            case UP: y--; break;
            case DOWN: y++; break;
            case LEFT: x--; break;
            case RIGHT: x++; break;
        }
        return (x >= 0 && x < width && y >= 0 && y < height) ? cells[y][x] : null;
    }
    public boolean isSimulationFinished() {
        return scheduler.isShutdown() && executor.isShutdown();
    }
}
