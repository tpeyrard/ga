package com.tpeyrard.ga.tsp;

import com.tpeyrard.ga.FitnessComputation;
import com.tpeyrard.ga.Individual;
import com.tpeyrard.ga.Population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Tours implements Population {
    private final List<Tour> tours;
    private final TourManager tourManager;

    private Tours(TourManager tourmanager, List<Tour> tours) {
        tourManager = tourmanager;
        this.tours = tours;
    }

    private Tours(int individuals, TourManager tourManager) {
        this(tourManager, new ArrayList<>(individuals));
        for (int i = 0; i < individuals; i++) {
            tours.add(Tour.newRandomTour(tourManager));
        }
    }

    public static Tours newRandomPopulation(int populationSize, TourManager tourManager) {
        return new Tours(populationSize, tourManager);
    }

    public static Tours newEmptyPopulation(TourManager tourManager, int size) {
        return new Tours(tourManager, new ArrayList<>(Collections.nCopies(size, null)));
    }

    @Override
    public Individual individualAt(int index) {
        return tours.get(index);
    }

    @Override
    public Individual fittest(FitnessComputation fitnessCalc) {
        return Collections.max(tours, Comparator.comparingDouble(tour -> tour.fitness(fitnessCalc)));
    }

    @Override
    public int size() {
        return tours.size();
    }

    @Override
    public void saveIndividual(int index, Individual indiv) {
        tours.set(index, ((Tour) indiv));
    }

    @Override
    public Population emptyPopulation(int size) {
        return Tours.newEmptyPopulation(tourManager, size);
    }

}
