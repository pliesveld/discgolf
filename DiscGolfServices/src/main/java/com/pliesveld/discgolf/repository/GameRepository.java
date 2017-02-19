package com.pliesveld.discgolf.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pliesveld.discgolf.domain.Game;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
}
