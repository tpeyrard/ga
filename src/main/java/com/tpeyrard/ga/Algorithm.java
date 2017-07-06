package com.tpeyrard.ga;

public class Algorithm {

    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    public static Population evolvePopulation(Population pop) {
        Population newPopulation = Population.emptyPopulation(pop.size());

        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, pop.fittest());
        }


        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }

        for (int i = elitismOffset; i < pop.size(); i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }

        mutatePopulation(newPopulation, elitismOffset);

        return newPopulation;
    }

    private static void mutatePopulation(Population newPopulation, int elitismOffset) {
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.individualAt(i));
        }
    }


    private static Individual crossover(Individual firstIndividual, Individual secondIndividual) {
        Individual newSol = new Individual();
        for (int i = 0; i < firstIndividual.size(); i++) {
            // Crossover
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, firstIndividual.geneAt(i));
            } else {
                newSol.setGene(i, secondIndividual.geneAt(i));
            }
        }
        return newSol;
    }

    private static void mutate(Individual indiv) {
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i, gene);
            }
        }
    }


    private static Individual tournamentSelection(Population pop) {
        Population tournament = Population.emptyPopulation(tournamentSize);

        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.individualAt(randomId));
        }

        return tournament.fittest();
    }
}