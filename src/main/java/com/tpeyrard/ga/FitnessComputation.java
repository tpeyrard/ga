package com.tpeyrard.ga;

public interface FitnessComputation {
    double getFitness(Individual individual);

    int maxFitness();
}
