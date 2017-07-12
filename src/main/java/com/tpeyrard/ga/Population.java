package com.tpeyrard.ga;

public interface Population {
    Individual individualAt(int index);

    Individual fittest(FitnessComputation fitnessCalc);

    int size();

    void saveIndividual(int index, Individual indiv);
}
