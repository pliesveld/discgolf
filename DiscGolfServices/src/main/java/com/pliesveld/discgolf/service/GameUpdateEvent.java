package com.pliesveld.discgolf.service;

import org.springframework.context.ApplicationEvent;

import com.pliesveld.discgolf.domain.Game;
import com.pliesveld.discgolf.domain.ScoreCard;

public class GameUpdateEvent extends GameEvent {
    private final ScoreCard scoreCard;
    public GameUpdateEvent(Game game, ScoreCard scoreCard) {
        super(game);
        this.scoreCard = scoreCard;
    }

    public ScoreCard getScoreCard() {
        return scoreCard;
    }
}
