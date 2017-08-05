package com.tpeyrard.ga.tsp;

public final class City {
    public static final City NO_CITY = new City(Integer.MIN_VALUE, Integer.MIN_VALUE, -1);
    private final int x;
    private final int y;
    private final int id;


    public City(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public double distanceTo(City city) {
        final int xDistance = Math.abs(this.x - city.x);
        final int yDistance = Math.abs(this.y - city.y);

        return Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
