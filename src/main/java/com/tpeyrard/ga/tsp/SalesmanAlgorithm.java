package com.tpeyrard.ga.tsp;

import com.tpeyrard.ga.FitnessComputation;
import com.tpeyrard.ga.GeneticAlgorithm;
import com.tpeyrard.ga.Individual;

public class SalesmanAlgorithm extends GeneticAlgorithm {

    public SalesmanAlgorithm(FitnessComputation fitnessCalc) {
        super(fitnessCalc);
    }

    @Override
    public Individual crossover(Individual firstIndividual, Individual secondIndividual, double crossoverRate) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void mutate(Individual offspring) {
        throw new UnsupportedOperationException();
    }
}
