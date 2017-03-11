package com.pliesveld.discgolf.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pliesveld.discgolf.domain.Game;
import com.pliesveld.discgolf.domain.GameStatus;
import com.pliesveld.discgolf.domain.Player;
import com.pliesveld.discgolf.domain.ScoreCard;
import com.pliesveld.discgolf.exception.GameException;
import com.pliesveld.discgolf.repository.CourseRepository;
import com.pliesveld.discgolf.repository.GameRepository;
import com.pliesveld.discgolf.repository.PlayerRepository;
import com.pliesveld.discgolf.service.GameUpdateEvent;

import static com.pliesveld.discgolf.domain.GameStatus.PLAYING;

@RestController
@RequestMapping("/game/{gameId}")
public class ScoreController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/player/{playerName}/strokes")
    public ResponseEntity<?> handleScorePost(
            @PathVariable("gameId") String gameId,
            @PathVariable("playerName") String playerName,
            @RequestParam("strokes") int strokes) {

        final Game game = gameRepository.findOne(gameId);

        if (game == null) {
            throw new GameException("Invalid game.");
        }

        final GameStatus gameStatus = game.getGameStatus();
        switch (gameStatus) {
            case NEW:
                game.setGameStatus(PLAYING);
                break;
            case PLAYING:
                break;
            case COMPLETED:
            case ABANDONED:
                throw new GameException("Invalid game state: " + gameStatus);
        }

        final Player player = playerRepository.findByName(playerName);

        if (player == null) {
            throw new GameException("Player not found.");
        }

        final ScoreCard scoreCard = player.getScoreCard();

        if (scoreCard == null) {
            return ResponseEntity.notFound().build();
        }

        scoreCard.record(strokes);
        final Game updatedGame = gameRepository.save(game);
        publisher.publishEvent(new GameUpdateEvent(updatedGame, scoreCard));
        return ResponseEntity.ok(updatedGame);
    }
}
