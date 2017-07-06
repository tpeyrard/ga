package com.tpeyrard.ga;

public class Main {
    public static void main(String[] args) {

        // Set a candidate solution
        FitnessCalc.setSolution("1111000000000000000000000000000000000000000000000000000000001111");

        // Create an initial population
        Population myPop = Population.newRandomPopulation(50);

        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        while (myPop.fittest().fitness() < FitnessCalc.maxFitness()) {
            generationCount++;
            System.out.println("Generation: " + generationCount + " Fittest: " + myPop.fittest().fitness());
            myPop = Algorithm.evolvePopulation(myPop);
        }
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(myPop.fittest());

    }
}
