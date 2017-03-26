package com.pliesveld.discgolf.service.events;

import com.pliesveld.discgolf.persistence.domain.Game;
import com.pliesveld.discgolf.common.domain.ScoreCard;

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
