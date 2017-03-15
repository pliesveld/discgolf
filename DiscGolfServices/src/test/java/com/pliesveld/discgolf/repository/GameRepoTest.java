package com.pliesveld.discgolf.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pliesveld.discgolf.config.BaseMongoTest;
import com.pliesveld.discgolf.domain.*;
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
