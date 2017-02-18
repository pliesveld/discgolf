package com.pliesveld.discgolf.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pliesveld.discgolf.domain.Player;

@Repository
public interface PlayerRepository extends MongoRepository<Player, Long> {
    public Player findByName(String name);
}
