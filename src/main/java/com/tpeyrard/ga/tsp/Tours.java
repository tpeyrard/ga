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

    private Tours(int individuals, TourManager tourManager) {
        this.tours = new ArrayList<>(individuals);
        this.tourManager = tourManager;
        for (int i = 0; i < individuals; i++) {
            tours.add(Tour.newRandomTour(tourManager));
        }
    }

    public static Tours newRandomPopulation(int numberOfCities, TourManager tourManager) {
        return new Tours(numberOfCities, tourManager);
    }

    public static Tours newEmptyPopulation(TourManager tourManager) {
        return new Tours(0, tourManager);
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
        throw new UnsupportedOperationException();
    }

    @Override
    public Population emptyPopulation(int size) {
        return Tours.newEmptyPopulation(tourManager);
    }

    public TourManager tourManager() {
        return tourManager;
    }
}
