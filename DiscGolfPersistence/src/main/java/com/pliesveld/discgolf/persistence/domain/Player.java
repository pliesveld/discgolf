package com.pliesveld.discgolf.persistence.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import com.pliesveld.discgolf.common.domain.ScoreCard;

import javax.persistence.GeneratedValue;

@Document
public class Player {

    @Id
    @GeneratedValue
    private String id;

    @Indexed
    private String name;

    @DBRef
    private Game currentGame;

    public Player() {}

    public String getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public Game getCurrentGame() { return currentGame; }

    public void setCurrentGame(Game currentGame) { this.currentGame = currentGame; }

    public ScoreCard scoreCard() {
        Assert.notNull(currentGame, "Player has no current game.");
        Assert.notEmpty(currentGame.getPlayers(), "Current game has no players");
        Assert.notEmpty(currentGame.getScores(), "Current game has no scorecards");
        Assert.isTrue(currentGame.getScores().containsKey(name), "Player is not a member of the current game");
        return currentGame.getScores().get(name);
    }
}

