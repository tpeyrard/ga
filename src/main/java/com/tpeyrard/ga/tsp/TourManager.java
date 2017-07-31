package com.tpeyrard.ga.tsp;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public final class TourManager {
    private final List<City> destinationCities;

    public TourManager() {
        this.destinationCities = new ArrayList<>();
    }

    public void addCity(City city) {
        this.destinationCities.add(city);
    }

    public City city(int index) {
        return destinationCities.get(index);
    }

    public int numberOfCities() {
        return destinationCities.size();
    }

    public List<City> cities() {
        return ImmutableList.copyOf(destinationCities);
    }
}
