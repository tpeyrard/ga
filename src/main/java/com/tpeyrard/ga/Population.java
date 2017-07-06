package com.tpeyrard.ga;

public class Population {

    private final Individual[] individuals;

    private Population(int populationSize) {
        this.individuals = new Individual[populationSize];
    }

    private Population(int populationSize, boolean initialise) {
        individuals = new Individual[populationSize];
        if (initialise) {
            randomIndividuals();
        }
    }

    public static Population newRandomPopulation(int populationSize) {
        return new Population(populationSize, true);
    }

    public static Population emptyPopulation(int populationSize) {
        return new Population(populationSize);
    }

    private void randomIndividuals() {
        for (int i = 0; i < size(); i++) {
            Individual newIndividual = new Individual();
            newIndividual.generateIndividual();
            saveIndividual(i, newIndividual);
        }
    }

    public Individual individualAt(int index) {
        return individuals[index];
    }

    public Individual fittest() {
        Individual fittest = individuals[0];
        for (int i = 0; i < size(); i++) {
            if (fittest.fitness() <= individualAt(i).fitness()) {
                fittest = individualAt(i);
            }
        }
        return fittest;
    }

    public int size() {
        return individuals.length;
    }

    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }
}