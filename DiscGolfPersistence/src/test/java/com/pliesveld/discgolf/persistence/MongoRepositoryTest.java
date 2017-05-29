package com.pliesveld.discgolf.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import com.pliesveld.discgolf.persistence.domain.Player;
import com.pliesveld.discgolf.persistence.repository.mongo.CourseRepository;
import com.pliesveld.discgolf.persistence.repository.mongo.GameRepository;
import com.pliesveld.discgolf.persistence.repository.mongo.PlayerRepository;
import com.pliesveld.discgolf.persistence.repository.mongo.ScoreCardRepository;
import org.junit.Test;
import static org.junit.Assert.*;

public class MongoRepositoryTest extends AbstractMongoTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ScoreCardRepository scoreCardRepository;

    @Test
    public void givenCourseRepo_shouldLoad() throws Exception {
        assertNotNull(courseRepository);
        assertNotNull(gameRepository);
        assertNotNull(playerRepository);
        assertNotNull(scoreCardRepository);
    }

    @Test
    public void givenPlayer_shouldPersist() throws Exception {
        Player player = new Player();
        player.setName("TestPlayer");
        Player ret = playerRepository.save(player);
        assertNotNull(ret);
        assertNotNull(ret.getId());
        String id = ret.getId();
        assertNotNull(playerRepository.findOne(id));
        assertNotNull(playerRepository.findByName("TestPlayer"));
    }
}
