package com.pliesveld.discgolf.web.domain;

import com.pliesveld.discgolf.common.domain.Color;
import com.pliesveld.discgolf.common.domain.GameMode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class NewGame {

    @NotNull
    private String course;

    @NotNull
    @NotEmpty
    private Collection<String> players;

    private GameMode mode = GameMode.STROKE_PLAY;

    private Color defaultTee = Color.WHITE;

    private Color defaultBasket = Color.WHITE;

    private Map<String, Color> playerTee = new LinkedHashMap<>();

    private Map<String, Color> playerBasket = new LinkedHashMap<>();

    public NewGame() {}

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Collection<String> getPlayers() {
        return players;
    }

    public void setPlayers(Collection<String> players) {
        this.players = players;
    }

    public GameMode getMode() {
        return mode;
    }

    public void setMode(GameMode mode) {
        this.mode = mode;
    }

    public Color getDefaultTee() {
        return defaultTee;
    }

    public void setDefaultTee(Color defaultTee) {
        this.defaultTee = defaultTee;
    }

    public Color getDefaultBasket() {
        return defaultBasket;
    }

    public void setDefaultBasket(Color defaultBasket) {
        this.defaultBasket = defaultBasket;
    }

    public Map<String,Color> getPlayerTee() {
        return playerTee;
    }

    public void setPlayerTee(Map<String,Color> playerTee) {
        this.playerTee = playerTee;
    }

    public Map<String,Color> getPlayerBasket() {
        return playerBasket;
    }

    public void setPlayerBasket(Map<String,Color> playerBasket) {
        this.playerBasket = playerBasket;
    }
}
