package com.pliesveld.discgolf.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import com.pliesveld.discgolf.exception.GameException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document
public class ScoreCard {
    public ScoreCard() {}

    @Id
    private String id;

    private Course course;

    private List<Score> strokesList = new ArrayList<>();

    private int currentHole;

    @CreatedDate
    private Instant createdOn;

    @LastModifiedDate
    private Instant lastUpdated;

    public ScoreCard(Course course) {
        super();
        this.course = course;
        course.getHoleList().forEach(hole -> {getStrokesList().add(new Score(hole,-1));});
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Score> getStrokesList() { return strokesList; }

    public void setStrokesList(List<Score> strokesList) { this.strokesList = strokesList; }

    public void setCurrentHole(int currentHole) { this.currentHole = currentHole; }

    public int getCurrentHole() { return currentHole; }

    public void record(int strokes) {
        final int currentHole = this.currentHole;
        final List<Hole> holeList = getCourse().getHoleList();
        final List<Score> scoreList = getStrokesList();

        if ( (currentHole < 0 || currentHole > 17 )
                || (holeList.size() <= currentHole )
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
