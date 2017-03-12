package com.pliesveld.discgolf.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pliesveld.discgolf.domain.Player;
import com.pliesveld.discgolf.repository.PlayerRepository;
import com.pliesveld.discgolf.web.controller.base.AbstractDiscGolfController;

@RestController
@RequestMapping("/player")
public class PlayerController extends AbstractDiscGolfController {

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<Player> handlePlayerById(@PathVariable("id") String playerId) {
        final Player player = playerRepository.findOne(playerId);
        if (player == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(player);
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<Player> handlePlayerByName(@PathVariable("name") String name){
        final Player player = playerRepository.findByName(name);
        if (player == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(player);
    }

    @GetMapping(path = "/find/{name}")
    public HttpEntity<PagedResources<Player>> handlePlayerSearch(@PathVariable("name") String name,
                                                         Pageable pageable, PagedResourcesAssembler assembler) {
        final Page<Player> players = playerRepository.findAllByNameStartsWith(name, pageable);
        return new ResponseEntity<>(assembler.toResource(players), HttpStatus.OK);
    }

    @GetMapping
    public HttpEntity<PagedResources<Player>> handlePlayerList(Pageable pageable, PagedResourcesAssembler assembler) {
        final Page<Player> players = playerRepository.findAll(pageable);
        return new ResponseEntity<>(assembler.toResource(players), HttpStatus.OK);
    }

}
