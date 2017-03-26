package com.pliesveld.discgolf.test.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pliesveld.discgolf.persistence.domain.Player;
import com.pliesveld.discgolf.persistence.repository.mongo.PlayerRepository;
import com.pliesveld.discgolf.test.config.BaseMongoTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PlayerRepoTest extends BaseMongoTest {

	@Autowired
	private PlayerRepository playerRepository;

	@Test
	public void givenContext_shouldLoad() {}

	@Test
	public void givenPlayer_whenSave_thenCorrect() {

		final String playerName = "Player1";
		Player newPlayer = new Player();
        newPlayer.setName(playerName);
		playerRepository.save(newPlayer);

		Player player = playerRepository.findByName(playerName);
		assertNotNull(player);
		assertEquals(playerName, player.getName());
	}


	@Test
	public void givenPlayers_findByName_thenCorrect() {

		List<Player> newPlayers = new ArrayList<>(100);
		for(int i = 0; i < 100; i++) {
			final String playerName = "findPlayerByName_" + UUID.randomUUID().toString();
			Player newPlayer = new Player();
			newPlayer.setName(playerName);
			newPlayers.add(newPlayer);
		}
		playerRepository.save(newPlayers);

		Page<Player> players = playerRepository.findAllByNameStartsWith("findPlayerByName_", new PageRequest(0,100));
		assertNotNull(players);
		assertEquals(100, players.getTotalElements());
	}
}
