package com.tpeyrard.ga.tsp;

import com.tpeyrard.ga.FitnessComputation;
import com.tpeyrard.ga.Individual;
import com.tpeyrard.ga.Population;

public class Tours implements Population {
    @Override
    public Individual individualAt(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Individual fittest(FitnessComputation fitnessCalc) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveIndividual(int index, Individual indiv) {
        throw new UnsupportedOperationException();
    }
}
