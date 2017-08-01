package com.tpeyrard.ga.tsp;

public final class City {
    public static final City NO_CITY = new City(Integer.MIN_VALUE, Integer.MIN_VALUE);
    private final int x;
    private final int y;


    public City() {
        this((int) (Math.random() * 200), (int) (Math.random() * 200));
    }

    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public double distanceTo(City city) {
        final int xDistance = Math.abs(this.x - city.x);
        final int yDistance = Math.abs(this.y - city.y);

        return Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
    }

    @Override
    public String toString() {
        return this.x + ", " + this.y;
    }
}
