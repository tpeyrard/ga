package com.tpeyrard.ga.tsp;

import com.tpeyrard.ga.FitnessComputation;
import com.tpeyrard.ga.Individual;

public class Tour implements Individual {
    @Override
    public int genomeSize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int fitness(FitnessComputation fitnessCalc) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void geneFrom(int index, Individual fromIndividual) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int aptitude() {
        throw new UnsupportedOperationException();
    }
}
