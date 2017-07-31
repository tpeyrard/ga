package com.tpeyrard.ga.simple;

import com.tpeyrard.ga.FitnessComputation;
import com.tpeyrard.ga.GeneticAlgorithm;
import com.tpeyrard.ga.Individual;

public final class BinaryAlgorithm extends GeneticAlgorithm {

    public BinaryAlgorithm(FitnessComputation fitnessCalc) {
        super(fitnessCalc);
    }

    @Override
    public Individual crossover(Individual firstIndividual, Individual secondIndividual, double crossoverRate) {
        final Individual newSol = Genome.newIndividual();
        for (int i = 0; i < firstIndividual.genomeSize(); i++) {
            if (Math.random() <= crossoverRate) {
                newSol.withGeneFrom(i, firstIndividual);
            } else {
                newSol.withGeneFrom(i, secondIndividual);
            }
        }
        return newSol;
    }

    @Override
    public void mutate(Individual genome) {
        for (int i = 0; i < genome.genomeSize(); i++) {
            if (Math.random() <= MUTATION_RATE) {
                byte gene = (byte) Math.round(Math.random());
                ((Genome) genome).setGene(i, gene);
            }
        }
    }

}