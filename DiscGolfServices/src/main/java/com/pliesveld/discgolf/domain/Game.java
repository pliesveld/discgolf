package com.pliesveld.discgolf.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import java.time.Instant;
import java.util.*;

@Document
public class Game {

    @Id
    @GeneratedValue
    private String id;

    private GameStatus gameStatus = GameStatus.NEW;

    private Course course;

    private Collection<Player> players = new ArrayList<>();

    private Map<String, ScoreCard> scores = new HashMap<>();

    @CreatedDate
    private Instant createdOn;

    @LastModifiedDate
    private Instant lastUpdated;

    public Game(Course course, List<Player> players) {
        super();
        this.setCourse(course);
        getPlayers().addAll(players);
    }

    public GameStatus getGameStatus() { return gameStatus; }

    public void setGameStatus(GameStatus gameStatus) { this.gameStatus = gameStatus; }

    public Course getCourse() { return course; }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Collection<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Collection<Player> players) {
        this.players = players;
    }

    public Map<String,ScoreCard> getScores() { return scores; }

    public void setScores(Map<String,ScoreCard> scores) { this.scores = scores; }

    public Instant getCreatedOn() { return createdOn; }

    public Instant getLastUpdated() { return lastUpdated; }
}
