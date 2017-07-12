package com.tpeyrard.ga.simple;

import com.tpeyrard.ga.Algorithm;
import com.tpeyrard.ga.FitnessComputation;

public class Main {
    public static void main(String[] args) {

        // Set a candidate solution
        final FitnessComputation fitnessCalc = BinaryFitness.newSolution("1111000000000000000000000000000000000000000000000000000000001111");

        // Create an initial population
        PopulationImp myPop = PopulationImp.newRandomPopulation(50);

        final Algorithm algorithm = new BinaryAlgorithm(fitnessCalc);
        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        while (myPop.fittest(fitnessCalc).fitness(fitnessCalc) < fitnessCalc.maxFitness()) {
            generationCount++;
            System.out.println("Generation: " + generationCount + " - Fittest: " + myPop.fittest(fitnessCalc).fitness(fitnessCalc));
            myPop = algorithm.evolvePopulation(myPop);
        }

        System.out.println("--------------------------------------------------------------------");
        System.out.println("Solution found after " + generationCount + " generations");
        System.out.println("Genome: " + myPop.fittest(fitnessCalc));
        System.out.println("--------------------------------------------------------------------");

    }
}
