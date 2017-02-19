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

import com.pliesveld.discgolf.domain.Course;
import com.pliesveld.discgolf.domain.Game;
import com.pliesveld.discgolf.domain.Player;
import com.pliesveld.discgolf.exception.GameException;
import com.pliesveld.discgolf.repository.CourseRepository;
import com.pliesveld.discgolf.repository.GameRepository;
import com.pliesveld.discgolf.repository.PlayerRepository;
import com.pliesveld.discgolf.service.GameEvent;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

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

}
