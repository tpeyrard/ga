package com.tpeyrard.ga.tsp;

import com.google.common.base.Stopwatch;
import com.tpeyrard.ga.FitnessComputation;
import com.tpeyrard.ga.GeneticAlgorithm;
import com.tpeyrard.ga.Population;

public class Main {
    public static void main(String[] args) {

        final Stopwatch stopwatch = Stopwatch.createStarted();

        TourManager tourManager = new TourManager();
        tourManager.addCity(new City(60, 200));
        tourManager.addCity(new City(180, 200));
        tourManager.addCity(new City(80, 180));
        tourManager.addCity(new City(140, 180));
        tourManager.addCity(new City(20, 160));
        tourManager.addCity(new City(100, 160));
        tourManager.addCity(new City(200, 160));
        tourManager.addCity(new City(140, 140));
        tourManager.addCity(new City(40, 120));
        tourManager.addCity(new City(100, 120));
        tourManager.addCity(new City(180, 100));
        tourManager.addCity(new City(60, 80));
        tourManager.addCity(new City(120, 80));
        tourManager.addCity(new City(180, 60));
        tourManager.addCity(new City(20, 40));
        tourManager.addCity(new City(100, 40));
        tourManager.addCity(new City(200, 40));
        tourManager.addCity(new City(20, 20));
        tourManager.addCity(new City(60, 20));
        tourManager.addCity(new City(160, 20));

        FitnessComputation fitnessCalc = new SalesmanFitness();

        // Initialize population
        Population pop = Tours.newRandomPopulation(50, tourManager);
        int fittest = pop.fittest(fitnessCalc).aptitude();
        System.out.println("Initial distance: " + fittest);

        GeneticAlgorithm algorithm = new SalesmanAlgorithm(fitnessCalc, tourManager);

        for (int i = 0; i < 1000; i++) {
            pop = algorithm.evolvePopulation(pop);
        }

        stopwatch.stop();

        System.out.println("Finished. Took " + stopwatch);
        System.out.println("Final distance: " + pop.fittest(fitnessCalc).aptitude());
        System.out.println("Solution:");
        System.out.println(pop.fittest(fitnessCalc));
    }
}
