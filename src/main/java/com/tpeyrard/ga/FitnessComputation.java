package com.tpeyrard.ga;

import com.tpeyrard.ga.simple.ByteIndividual;

public interface FitnessComputation {
    int getFitness(ByteIndividual individual);

    int maxFitness();
}
