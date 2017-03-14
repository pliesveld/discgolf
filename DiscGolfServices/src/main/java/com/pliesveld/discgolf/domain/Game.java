package com.pliesveld.discgolf.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.pliesveld.discgolf.exception.GameException;

import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.*;

@Document
public class Game {

    @Id
    @GeneratedValue
    private String id;

    private GameStatus gameStatus = GameStatus.NEW;

    @DBRef
    private Course course;

    @DBRef
    private Collection<Player> players = new ArrayList<>();

    private Map<String, ScoreCard> scores = new LinkedHashMap<>();

    @CreatedDate
    private Instant createdOn;

    @LastModifiedDate
    private Instant lastUpdated;

    public Game() {}

    public Game(Course course, Player player) {
        super();
        this.setCourse(course);
        this.getPlayers().add(player);
    }

    public Game(Course course, List<Player> players) {
        super();
        this.setCourse(course);
        getPlayers().addAll(players);
    }

    public String getId() { return id; }

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

    public ScoreCard scoreCardOf(String playerName) {
        ScoreCard card = scores.get(playerName);
        if (card == null) {
            throw new GameException(String.format("Could not find player %s in Game %s", playerName, getId()));
        }
        return card;
    }
}
