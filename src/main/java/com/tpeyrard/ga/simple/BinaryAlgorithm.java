package com.tpeyrard.ga.simple;

import com.tpeyrard.ga.FitnessComputation;
import com.tpeyrard.ga.GeneticAlgorithm;
import com.tpeyrard.ga.Individual;
import com.tpeyrard.ga.Population;

public final class BinaryAlgorithm implements GeneticAlgorithm {

    private static final double UNIFORM_RATE = 0.5;
    private static final double MUTATION_RATE = 0.015;
    private static final int TOURNAMENT_SIZE = 5;
    private static final boolean ELITISM = true;
    private final FitnessComputation fitnessCalc;

    public BinaryAlgorithm(FitnessComputation fitnessCalc) {
        this.fitnessCalc = fitnessCalc;
    }

    @Override
    public Population evolvePopulation(Population pop) {
        final PopulationImpl newPopulation = PopulationImpl.emptyPopulation(pop.size());

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

            final Genome newIndiv = crossover(indiv1, indiv2);

            mutate(newIndiv);

            newPopulation.saveIndividual(i, newIndiv);
        }

        return newPopulation;
    }

    private static Genome crossover(Individual firstIndividual, Individual secondIndividual) {
        final Genome newSol = Genome.newIndividual();
        for (int i = 0; i < firstIndividual.genomeSize(); i++) {
            if (Math.random() <= UNIFORM_RATE) {
                newSol.withGeneFrom(i, firstIndividual);
            } else {
                newSol.withGeneFrom(i, secondIndividual);
            }
        }
        return newSol;
    }

    private static void mutate(Genome indiv) {
        for (int i = 0; i < indiv.genomeSize(); i++) {
            if (Math.random() <= MUTATION_RATE) {
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i, gene);
            }
        }
    }


    private Individual tournamentSelection(Population pop) {
        final PopulationImpl tournament = PopulationImpl.emptyPopulation(TOURNAMENT_SIZE);

        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.individualAt(randomId));
        }

        return tournament.fittest(fitnessCalc);
    }
}