package com.tpeyrard.ga;

import com.tpeyrard.ga.simple.Genome;

public interface FitnessComputation {
    int getFitness(Genome individual);

    int maxFitness();
}
