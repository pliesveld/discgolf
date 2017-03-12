package com.pliesveld.discgolf.service.events;

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
