package com.tpeyrard.ga;

public abstract class GeneticAlgorithm {
    protected static final boolean ELITISM = true;
    protected static final int ELITISM_OFFSET = ELITISM ? 1 : 0;
    protected static final double UNIFORM_RATE = 0.5;
    protected static final double MUTATION_RATE = 0.015;
    protected static final int TOURNAMENT_SIZE = 5;
    protected final FitnessComputation fitnessCalc;

    public GeneticAlgorithm(FitnessComputation fitnessCalc) {
        this.fitnessCalc = fitnessCalc;
    }

    public Population evolvePopulation(Population pop) {
        final Population newPopulation = pop.emptyPopulation(pop.size());

        if (ELITISM) {
            newPopulation.saveIndividual(0, pop.fittest(fitnessCalc));
        }

        for (int i = ELITISM_OFFSET; i < pop.size(); i++) {
            final Individual firstParent = tournamentSelection(pop, fitnessCalc, TOURNAMENT_SIZE);
            final Individual secondParent = tournamentSelection(pop, fitnessCalc, TOURNAMENT_SIZE);

            final Individual offspring = crossover(firstParent, secondParent, UNIFORM_RATE);

            mutate(offspring);

            newPopulation.saveIndividual(i, offspring);
        }

        return newPopulation;
    }

    protected abstract Individual crossover(Individual firstIndividual, Individual secondIndividual, double crossoverRate);

    protected abstract void mutate(Individual offspring);

    protected static Individual tournamentSelection(Population population, FitnessComputation fitnessCalc, int tournamentSize) {
        final Population tournament = population.emptyPopulation(tournamentSize);

        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * population.size());
            tournament.saveIndividual(i, population.individualAt(randomId));
        }

        return tournament.fittest(fitnessCalc);
    }
}
