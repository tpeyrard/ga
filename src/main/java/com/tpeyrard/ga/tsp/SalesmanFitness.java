package com.tpeyrard.ga.tsp;

import com.tpeyrard.ga.FitnessComputation;
import com.tpeyrard.ga.Individual;

public class SalesmanFitness implements FitnessComputation {

    @Override
    public double getFitness(Individual individual) {
        return 1 / (double) individual.aptitude();
    }

    @Override
    public int maxFitness() {
        throw new UnsupportedOperationException();
    }
}
