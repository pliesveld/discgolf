package com.pliesveld.discgolf.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pliesveld.discgolf.domain.ScoreCard;

@Repository
public interface ScoreCardRepository extends MongoRepository<ScoreCard, Long> {
}
