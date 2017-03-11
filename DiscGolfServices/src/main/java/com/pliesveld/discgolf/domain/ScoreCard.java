package com.pliesveld.discgolf.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.pliesveld.discgolf.exception.GameException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document
public class ScoreCard {

    @Id
    private String id;

    private List<Score> strokesList = new ArrayList<>();

    private int currentHole;

    @CreatedDate
    private Instant createdOn;

    @LastModifiedDate
    private Instant lastUpdated;

    public ScoreCard() {}

    public List<Score> getStrokesList() { return strokesList; }

    public void setStrokesList(List<Score> strokesList) { this.strokesList = strokesList; }

    public void setCurrentHole(int currentHole) { this.currentHole = currentHole; }

    public int getCurrentHole() { return currentHole; }

    public void record(int strokes) {
        final int currentHole = this.currentHole;
        final List<Score> scoreList = getStrokesList();

        if ( (currentHole < 0 || currentHole > 17 )
                || (scoreList.size() <= currentHole) ) {
            throw new GameException("Could not record game; currentHole is invalid.");
        }

        scoreList.get(currentHole).setStrokes(strokes);
        setCurrentHole(currentHole + 1);
    }

    public int computeScore() {
        return strokesList.stream().filter(score -> score.getStrokes() != -1).mapToInt(Score::getScore).sum();
    }


}
