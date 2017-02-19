package com.pliesveld.discgolf.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pliesveld.discgolf.config.BaseMongoTest;
import com.pliesveld.discgolf.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

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

    @Test
	public void givenGame_whenSave_shouldHaveScoreCard() {
		Player player = newPlayer();
		Course course = newCourse();

		Game game = new Game(course, Arrays.asList(player));
		game.getScores().put(player.getName(), new ScoreCard(course));
		Game savedGame = gameRepository.save(game);
		ScoreCard scoreCard = savedGame.getScores().get(player.getName());
		assertNotNull(scoreCard);
	}


	private Player newPlayer() {
		Player player1 = new Player();
		player1.setName("Player1");
		return playerRepository.save(player1);
	}

	private Course newCourse() {
		Course course = new Course();
		course.setName("SampleGameCourse");
		List<Hole> holeList = Arrays.asList(newHole(),  newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole(), newHole());
		course.setHoleList(holeList);
		return courseRepository.save(course);
	}

	private Hole newHole() {
		Hole hole = new Hole();
		hole.setPar(3);
		hole.setDistance(250);
        return hole;
	}
}
