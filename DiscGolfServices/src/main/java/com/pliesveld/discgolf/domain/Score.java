package com.pliesveld.discgolf.domain;

public class Score extends Hole {

    private int strokes;

    public Score() {}

    public Score(Hole hole, int strokes) {
        super(hole);
        this.strokes = strokes;
    }

    public int getStrokes() { return strokes; }

    public void setStrokes(int strokes) { this.strokes = strokes; }

    public int getScore() { return this.getStrokes() - this.getPar(); }
}
