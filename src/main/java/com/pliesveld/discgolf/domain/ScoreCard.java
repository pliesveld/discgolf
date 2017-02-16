package com.pliesveld.discgolf.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

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

    @CreatedDate
    private Instant createdOn;

    @LastModifiedDate
    private Instant lastUpdated;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Score> getStrokesList() { return strokesList; }

    public void setStrokesList(List<Score> strokesList) { this.strokesList = strokesList; }

    public void record(Hole hole, int strokes) {
        if (getStrokesList().size() < 18) {
            final Score score = new Score(hole, strokes);
            getStrokesList().add(score);
        }
    }

    public int computeScore() {
        return strokesList.stream().mapToInt(Score::getScore).sum();
    }
}
