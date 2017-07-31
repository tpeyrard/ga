package com.tpeyrard.ga;

public interface FitnessComputation {
    int getFitness(Individual individual);

    int maxFitness();
}
