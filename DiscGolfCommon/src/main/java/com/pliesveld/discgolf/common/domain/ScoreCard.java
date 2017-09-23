package com.pliesveld.discgolf.common.domain;

import java.util.ArrayList;
import java.util.List;

import com.pliesveld.discgolf.common.exception.GameException;

public class ScoreCard {

    private List<Score> strokesList = new ArrayList<>();

    private int currentHole;

    private Color tee;

    private Color basket;

    public ScoreCard() {}

    public List<Score> getStrokesList() { return strokesList; }

    public void setStrokesList(List<Score> strokesList) { this.strokesList = strokesList; }

    public void setCurrentHole(int currentHole) { this.currentHole = currentHole; }

    public int getCurrentHole() { return currentHole; }

    public Color getTee() { return tee; }

    public void setTee(Color tee) { this.tee = tee; }

    public Color getBasket() { return basket; }

    public void setBasket(Color basket) { this.basket = basket; }

    public void record(int strokes) {
        final int currentHole = this.currentHole;


        if ( currentHole < 0 || currentHole > 17) {
            throw new GameException("Could not record game; currentHole is invalid.");
        }

        Score score = new Score(3, strokes);
        strokesList.add(score);
        setCurrentHole(currentHole + 1);
    }

    public int computeScore() {
        return strokesList.stream().filter(score -> score.getStrokes() != -1).mapToInt(Score::getScore).sum();
    }

    @Override
    public String toString() {
        return "ScoreCard{" + "strokesList=" + strokesList + ", currentHole=" + currentHole + ", tee=" + tee + ", basket=" + basket + '}';
    }
}
