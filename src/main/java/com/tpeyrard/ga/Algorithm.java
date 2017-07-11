package com.tpeyrard.ga;

public class Algorithm {

    private static final double UNIFORM_RATE = 0.5;
    private static final double MUTATION_RATE = 0.015;
    private static final int TOURNAMENT_SIZE = 5;
    private static final boolean ELITISM = true;

    public static Population evolvePopulation(Population pop) {
        final Population newPopulation = Population.emptyPopulation(pop.size());

        // Keep our best individual
        if (ELITISM) {
            newPopulation.saveIndividual(0, pop.fittest());
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
            mutate(newPopulation.individualAt(i));
        }
    }


    private static Individual crossover(Individual firstIndividual, Individual secondIndividual) {
        Individual newSol = new Individual();
        for (int i = 0; i < firstIndividual.genomeSize(); i++) {
            // Crossover
            if (Math.random() <= UNIFORM_RATE) {
                newSol.setGene(i, firstIndividual.geneAt(i));
            } else {
                newSol.setGene(i, secondIndividual.geneAt(i));
            }
        }
        return newSol;
    }

    private static void mutate(Individual indiv) {
        for (int i = 0; i < indiv.genomeSize(); i++) {
            if (Math.random() <= MUTATION_RATE) {
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i, gene);
            }
        }
    }


    private static Individual tournamentSelection(Population pop) {
        final Population tournament = Population.emptyPopulation(TOURNAMENT_SIZE);

        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.individualAt(randomId));
        }

        return tournament.fittest();
    }
}