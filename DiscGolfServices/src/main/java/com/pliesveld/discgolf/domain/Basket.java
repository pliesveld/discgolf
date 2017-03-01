package com.pliesveld.discgolf.domain;

public class Hole {
    private int par;

    private int distance;

    public Hole() {}

    /**
     * Instantiates a new Hole.
     *
     * @param par      the par, or average number of strokes expected
     * @param distance the distance in yards to the basket
     */
    public Hole(int par, int distance) {
        this.par = par;
        this.distance = distance;
    }

    public Hole(Hole hole) {
        this.par = hole.par;
        this.distance = hole.distance;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
