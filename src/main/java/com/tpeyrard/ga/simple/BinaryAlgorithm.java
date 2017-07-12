package com.tpeyrard.ga.simple;

import com.tpeyrard.ga.Algorithm;
import com.tpeyrard.ga.FitnessComputation;
import com.tpeyrard.ga.Individual;
import com.tpeyrard.ga.Population;

public final class BinaryAlgorithm implements Algorithm {

    private static final double UNIFORM_RATE = 0.5;
    private static final double MUTATION_RATE = 0.015;
    private static final int TOURNAMENT_SIZE = 5;
    private static final boolean ELITISM = true;
    private final FitnessComputation fitnessCalc;

    public BinaryAlgorithm(FitnessComputation fitnessCalc) {
        this.fitnessCalc = fitnessCalc;
    }

    @Override
    public PopulationImp evolvePopulation(PopulationImp pop) {
        final PopulationImp newPopulation = PopulationImp.emptyPopulation(pop.size());

        // Keep our best individual
        if (ELITISM) {
            newPopulation.saveIndividual(0, pop.fittest(fitnessCalc));
        }


        int elitismOffset;
        if (ELITISM) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }

        for (int i = elitismOffset; i < pop.size(); i++) {
            final Individual indiv1 = tournamentSelection(pop);
            final Individual indiv2 = tournamentSelection(pop);
            final Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }

        mutatePopulation(newPopulation, elitismOffset);

        return newPopulation;
    }

    private static void mutatePopulation(Population newPopulation, int elitismOffset) {
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(((ByteIndividual) newPopulation.individualAt(i)));
        }
    }


    private static Individual crossover(Individual firstIndividual, Individual secondIndividual) {
        Individual newSol = ByteIndividual.newIndividual();
        for (int i = 0; i < firstIndividual.genomeSize(); i++) {
            // Crossover
            if (Math.random() <= UNIFORM_RATE) {
                newSol.geneFrom(i, firstIndividual);
            } else {
                newSol.geneFrom(i, secondIndividual);
            }
        }
        return newSol;
    }

    private static void mutate(ByteIndividual indiv) {
        for (int i = 0; i < indiv.genomeSize(); i++) {
            if (Math.random() <= MUTATION_RATE) {
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i, gene);
            }
        }
    }


    private Individual tournamentSelection(PopulationImp pop) {
        final PopulationImp tournament = PopulationImp.emptyPopulation(TOURNAMENT_SIZE);

        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.individualAt(randomId));
        }

        return tournament.fittest(fitnessCalc);
    }
}