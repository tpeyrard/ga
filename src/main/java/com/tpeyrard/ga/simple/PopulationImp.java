package com.tpeyrard.ga.simple;

import com.tpeyrard.ga.FitnessComputation;
import com.tpeyrard.ga.Individual;
import com.tpeyrard.ga.Population;

import static com.tpeyrard.ga.simple.ByteIndividual.newRandomIndividual;

public class PopulationImp implements Population {

    private final Individual[] individuals;

    private PopulationImp(int populationSize) {
        this.individuals = new ByteIndividual[populationSize];
    }

    private PopulationImp(int populationSize, boolean initialise) {
        individuals = new ByteIndividual[populationSize];
        if (initialise) {
            randomIndividuals();
        }
    }

    public static PopulationImp newRandomPopulation(int populationSize) {
        return new PopulationImp(populationSize, true);
    }

    public static PopulationImp emptyPopulation(int populationSize) {
        return new PopulationImp(populationSize);
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