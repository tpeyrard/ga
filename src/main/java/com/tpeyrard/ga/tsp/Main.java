package com.tpeyrard.ga.tsp;

import com.google.common.base.Stopwatch;
import com.tpeyrard.ga.FitnessComputation;
import com.tpeyrard.ga.GeneticAlgorithm;
import com.tpeyrard.ga.Population;

public class Main {
    public static void main(String[] args) {

        final Stopwatch stopwatch = Stopwatch.createStarted();

        TourManager tourManager = new TourManager();
        tourManager.addCity(new City(200, 100, 0));
        tourManager.addCity(new City(195, 130, 1));
        tourManager.addCity(new City(180, 158, 2));
        tourManager.addCity(new City(158, 180, 3));
        tourManager.addCity(new City(130, 195, 4));
        tourManager.addCity(new City(100, 200, 5));
        tourManager.addCity(new City(69, 195, 6));
        tourManager.addCity(new City(41, 180, 7));
        tourManager.addCity(new City(19, 158, 8));
        tourManager.addCity(new City(4, 130, 9));
        tourManager.addCity(new City(0, 100, 10));
        tourManager.addCity(new City(4, 69, 11));
        tourManager.addCity(new City(19, 41, 12));
        tourManager.addCity(new City(41, 19, 13));
        tourManager.addCity(new City(69, 4, 14));
        tourManager.addCity(new City(99, 0, 15));
        tourManager.addCity(new City(130, 4, 16));
        tourManager.addCity(new City(158, 19, 17));
        tourManager.addCity(new City(180, 41, 18));
        tourManager.addCity(new City(195, 69, 19));

        FitnessComputation fitnessCalc = new SalesmanFitness();

        // Initialize population
        Population pop = Tours.newRandomPopulation(50, tourManager);
        int fittest = pop.fittest(fitnessCalc).aptitude();
        System.out.println("Initial distance: " + fittest);

        GeneticAlgorithm algorithm = new SalesmanAlgorithm(fitnessCalc, tourManager);

        for (int i = 0; i < 500; i++) {
            pop = algorithm.evolvePopulation(pop);
        }

        stopwatch.stop();

        System.out.println("Finished. Took " + stopwatch);
        System.out.println("Final distance: " + pop.fittest(fitnessCalc).aptitude());
        System.out.println("Solution:");
        System.out.println(pop.fittest(fitnessCalc));
    }
}
