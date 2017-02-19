package com.pliesveld.discgolf.service;

import com.pliesveld.discgolf.domain.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameEvent {
    private static final Logger LOG = LogManager.getLogger();

    private final Game game;

    public GameEvent(Game game) {
        this.game = game;
    }

    public Game getGame() { return game; }
}
