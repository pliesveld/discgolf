package com.pliesveld.discgolf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.pliesveld.discgolf.common.domain.Color;
import com.pliesveld.discgolf.common.domain.ScoreCard;
import com.pliesveld.discgolf.persistence.domain.Course;
import com.pliesveld.discgolf.persistence.domain.Game;
import com.pliesveld.discgolf.persistence.domain.Player;
import com.pliesveld.discgolf.common.exception.GameException;
import com.pliesveld.discgolf.common.exception.PlayerException;
import com.pliesveld.discgolf.persistence.repository.mongo.GameRepository;
import com.pliesveld.discgolf.persistence.repository.mongo.PlayerRepository;
import com.pliesveld.discgolf.persistence.repository.mongo.ScoreCardRepository;

import javax.validation.constraints.NotNull;
import java.util.Collections;

@Service
public class GameService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ScoreCardRepository scoreCardRepository;

    @Autowired
    private GameRepository gameRepository;


    //    boolean isCurrentlyPlaying(@NotNull Player player) throws PlayerException;
//
//    void beginGame(@NotNull Player player, @NotNull Course course) throws GameException;
//
//    void abortGame(@NotNull Player player) throws GameException;
//
//    void recordNextHole(@NotNull Player player, int strokes) throws GameException;

    public Player startGame(Game game, Player player) {

        player.setCurrentGame(game);
        player = playerRepository.save(player);

        String scoreCardId = game.getId().toString() + "_" + player.getId();
        ScoreCard scoreCard = scoreCardRepository.findOne(scoreCardId);

        if (scoreCard == null) {
            scoreCard = new ScoreCard();
            scoreCard.setBasket(Color.WHITE);
            scoreCard.setTee(Color.WHITE);
            scoreCard.setCurrentHole(0);
        }

        game.getScores().putIfAbsent(player.getName(), scoreCard);

        gameRepository.save(game);

        return player;

    }
}
