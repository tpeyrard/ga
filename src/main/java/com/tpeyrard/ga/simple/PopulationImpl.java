package com.tpeyrard.ga.simple;

import com.tpeyrard.ga.FitnessComputation;
import com.tpeyrard.ga.Individual;
import com.tpeyrard.ga.Population;

import static com.tpeyrard.ga.simple.Genome.newRandomIndividual;

public class PopulationImpl implements Population {

    private final Individual[] individuals;

    private PopulationImpl(int populationSize) {
        this.individuals = new Genome[populationSize];
    }

    private PopulationImpl(int populationSize, boolean initialise) {
        individuals = new Genome[populationSize];
        if (initialise) {
            randomIndividuals();
        }
    }

    public static PopulationImpl newRandomPopulation(int populationSize) {
        return new PopulationImpl(populationSize, true);
    }

    public static PopulationImpl emptyPopulation(int populationSize) {
        return new PopulationImpl(populationSize);
    }

    private void randomIndividuals() {
        for (int i = 0; i < size(); i++) {
            saveIndividual(i, newRandomIndividual());
        }
    }

    @Override
    public Individual individualAt(int index) {
        return individuals[index];
    }

    @Override
    public Individual fittest(FitnessComputation fitnessCalc) {
        Individual fittest = individuals[0];
        for (int i = 0; i < size(); i++) {
            if (fittest.fitness(fitnessCalc) <= individualAt(i).fitness(fitnessCalc)) {
                fittest = individualAt(i);
            }
        }
        return fittest;
    }

    @Override
    public int size() {
        return individuals.length;
    }

    @Override
    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }
}