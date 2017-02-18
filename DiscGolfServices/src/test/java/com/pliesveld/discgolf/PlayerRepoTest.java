package com.pliesveld.discgolf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pliesveld.discgolf.datalayer.PlayerRepository;
import com.pliesveld.discgolf.domain.Player;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerRepoTest {

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
}
