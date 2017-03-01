package com.pliesveld.discgolf.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pliesveld.discgolf.config.BaseMongoTest;
import com.pliesveld.discgolf.domain.Basket;
import com.pliesveld.discgolf.domain.Player;
import com.pliesveld.discgolf.domain.Score;
import com.pliesveld.discgolf.domain.ScoreCard;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ScoreCardRepoTest extends BaseMongoTest {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private ScoreCardRepository scoreCardRepository;

	@Test
	public void givenContext_shouldLoad() {}

	@Test
	public void givenScoreCard_whenSave_thenCorrect() {

		final String playerName = "AverageGolfer";
		Player newPlayer = new Player();
        newPlayer.setName(playerName);
		playerRepository.save(newPlayer);

		ScoreCard scoreCard = new ScoreCard();
		int basket = 3;

        scoreCard.getStrokesList().addAll(Arrays.asList(new Score(basket,3),new Score(basket,3),new Score(basket,3),new Score(basket,3),new Score(basket,3),new Score(basket,3),new Score(basket,3),new Score(basket,3),new Score(basket,3),new Score(basket,3),new Score(basket,3),new Score(basket,3),new Score(basket,3),new Score(basket,3),new Score(basket,3),new Score(basket,3),new Score(basket,3),new Score(basket,3)));
        ScoreCard savedScoreCard = scoreCardRepository.save(scoreCard);

        assertNotNull(savedScoreCard);
        assertNotNull(savedScoreCard.getStrokesList());
        assertEquals(18, savedScoreCard.getStrokesList().size());
        assertEquals(0,savedScoreCard.computeScore());
	}
}
