package com.tpeyrard.ga;

public interface Individual {
    int genomeSize();

    int fitness(FitnessComputation fitnessCalc);

    void geneFrom(int index, Individual fromIndividual);

    int aptitude();
}
