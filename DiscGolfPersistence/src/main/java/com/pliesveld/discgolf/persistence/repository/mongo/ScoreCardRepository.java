package com.pliesveld.discgolf.persistence.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pliesveld.discgolf.common.domain.ScoreCard;

@Repository
public interface ScoreCardRepository extends MongoRepository<ScoreCard, String> {
}
