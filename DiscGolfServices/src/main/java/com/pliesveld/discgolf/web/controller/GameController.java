package com.pliesveld.discgolf.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pliesveld.discgolf.domain.*;
import com.pliesveld.discgolf.exception.GameException;
import com.pliesveld.discgolf.repository.CourseRepository;
import com.pliesveld.discgolf.repository.GameRepository;
import com.pliesveld.discgolf.repository.PlayerRepository;
import com.pliesveld.discgolf.service.GameEvent;
import com.pliesveld.discgolf.service.GameUpdateEvent;
import com.pliesveld.discgolf.web.controller.base.AbstractDiscGolfController;

import java.util.ArrayList;
import java.util.List;

import static com.pliesveld.discgolf.domain.GameStatus.PLAYING;

@RestController
@RequestMapping("/game")
public class GameController extends AbstractDiscGolfController {

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<?> handlePlayerById(@PathVariable("id") String gameId) {
        final Game game = gameRepository.findOne(gameId);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }

    @PostMapping
    public ResponseEntity<?> handleNewGame(@RequestParam(name = "players")List<String> _players, @RequestParam(name = "course") String courseName) {

        final Course course = courseRepository.findByName(courseName);
        if (course == null) throw new GameException("Invalid course");

        final List<Player> players = new ArrayList<>();
        _players.stream().forEach(p -> {
            final Player player = playerRepository.findOne(p);
            if (player != null) {
                players.add(player);
            }
        });

        if (players.size() == 0) {
            throw new GameException("Not enough players");
        }

        final Game game = gameRepository.save(new Game(course, players));

        if (game == null) throw new GameException("Failed to create new game.");

        publisher.publishEvent(new GameEvent(game));
        return ResponseEntity.ok(game);
    }

    @DeleteMapping(path = "/id/{id}")
    public ResponseEntity<?> handleDeleteGame(@PathVariable("id") String gameId) {
        final Game game = gameRepository.findOne(gameId);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        gameRepository.delete(gameId);
        return ResponseEntity.ok(game);
    }

    static public class ScoreDTO {
        private int strokes;

        public ScoreDTO() {}

        public int getStrokes() { return strokes; }

        public void setStrokes(int strokes) { this.strokes = strokes; }
    }

    @PostMapping("{gameId}/player/{playerName}/score")
    public ResponseEntity<?> handleScorePost(
            @PathVariable("gameId") String gameId,
            @PathVariable("playerName") String playerName,
            @RequestBody ScoreDTO score) {

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

        scoreCard.record(score.getStrokes());
        final Game updatedGame = gameRepository.save(game);
        publisher.publishEvent(new GameUpdateEvent(updatedGame, scoreCard));
        return ResponseEntity.ok(updatedGame);
    }
}
