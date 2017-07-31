package com.tpeyrard.ga;

public interface Individual {
    int genomeSize();

    double fitness(FitnessComputation fitnessCalc);

    void withGeneFrom(int index, Individual fromIndividual);

    int aptitude();
}
