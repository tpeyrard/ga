package com.tpeyrard.ga.tsp;

import com.tpeyrard.ga.FitnessComputation;
import com.tpeyrard.ga.Individual;
import org.assertj.core.util.Lists;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Tour implements Individual {
    private final List<City> cities;
    private final TourManager tourManager;
    private double fitness = 0.0;
    private int distance = 0;

    private Tour(TourManager tourManager) {
        this.tourManager = tourManager;
        this.cities = Lists.newArrayList(tourManager.cities());

        Collections.shuffle(this.cities);
    }

    public static Tour newRandomTour(TourManager tourManager) {
        return new Tour(tourManager);
    }

    @Override
    public int genomeSize() {
        return tourManager.numberOfCities();
    }

    @Override
    public double fitness(FitnessComputation fitnessCalc) {
        if (fitness == 0.0) {
            fitness = fitnessCalc.getFitness(this);
        }
        return fitness;
    }

    @Override
    public void withGeneFrom(int index, Individual fromIndividual) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int aptitude() {
        if (distance == 0.0) {
            for (int i = 0; i < cities.size() - 1; i++) {
                distance += cities.get(i).distanceTo(cities.get(i + 1));
            }
            distance += cities.get(cities.size() - 1).distanceTo(cities.get(0));
        }
        return distance;
    }

    @Override
    public String toString() {
        return cities.stream().map(City::toString).collect(Collectors.joining(" | "));
    }
}
