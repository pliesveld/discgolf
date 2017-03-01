package com.pliesveld.discgolf.domain;

public class Score {
    private Tee tee;

    private Basket basket;

    private int strokes;

    private int par;

    public Score() {}

    public Score(int par, int strokes) {
        this.par = par;
        this.strokes = strokes;
    }

    public Tee getTee() { return tee; }

    public void setTee(Tee tee) { this.tee = tee; }

    public Basket getBasket() { return basket; }

    public void setBasket(Basket basket) { this.basket = basket; }

    public int getStrokes() { return strokes; }

    public void setStrokes(int strokes) { this.strokes = strokes; }

    public int getPar() { return par; }

    public void setPar(int par) { this.par = par; }

    public int getScore() { return this.getStrokes() - this.getPar(); }
}
