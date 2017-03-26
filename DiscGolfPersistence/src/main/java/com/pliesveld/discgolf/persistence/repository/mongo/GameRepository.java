package com.pliesveld.discgolf.persistence.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pliesveld.discgolf.persistence.domain.Game;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
}
