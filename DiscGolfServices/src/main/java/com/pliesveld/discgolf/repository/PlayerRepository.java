package com.pliesveld.discgolf.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pliesveld.discgolf.domain.Player;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
    public Player findByName(String name);

    public Page<Player> findAllByNameStartsWith(String name, Pageable pageable);
}
