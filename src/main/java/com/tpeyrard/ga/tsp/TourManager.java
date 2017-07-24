package com.tpeyrard.ga.tsp;

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
}
