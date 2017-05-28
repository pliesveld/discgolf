package com.pliesveld.discgolf.test.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pliesveld.discgolf.persistence.domain.Course;
import com.pliesveld.discgolf.persistence.domain.Game;
import com.pliesveld.discgolf.persistence.domain.Player;
import com.pliesveld.discgolf.persistence.repository.mongo.CourseRepository;
import com.pliesveld.discgolf.persistence.repository.mongo.GameRepository;
import com.pliesveld.discgolf.persistence.repository.mongo.PlayerRepository;
import com.pliesveld.discgolf.test.config.BaseMongoTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class GameRepoTest extends BaseMongoTest {

    @Autowired
	private CourseRepository courseRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private GameRepository gameRepository;

	@Test
	public void givenContext_shouldLoad() {}

	@Test
	public void givenGame_whenSave_thenCorrect() {
		Player player = newPlayer();
		Course course = newCourse();

		Game game = new Game(course, Arrays.asList(player));
		game.setId(UUID.randomUUID().toString());
		Game savedGame = gameRepository.save(game);

		assertNotNull(savedGame);
		assertNotNull(savedGame.getCourse());
		assertNotNull(savedGame.getPlayers());
		assertEquals(1, savedGame.getPlayers().size());
	}

//    @Test
//	public void givenGame_whenSave_shouldHaveScoreCard() {
//		Player player = newPlayer();
//		Course course = newCourse();
//
//		Game game = new Game(course, Arrays.asList(player));
//		game.getScores().put(player.getName(), new ScoreCard(course));
//		Game savedGame = gameRepository.save(game);
//		ScoreCard scoreCard = savedGame.getScores().get(player.getName());
//		assertNotNull(scoreCard);
//	}





}
