package com.soccer.manager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.soccer.manager.domain.repo.entity.Player;
import com.soccer.manager.domain.repo.entity.PlayerType;

@SpringBootTest
class TeamGeneratorServiceTest {

	@Autowired
	private TeamGeneratorService teamGeneratorService;
	
	@Test
	void testGenerationOfTeam() {
		var team = teamGeneratorService.generateTeam();
		
		List<Player> goalkeepers = team.getPlayers().stream()
									.filter(player -> player.getPlayerType() == PlayerType.GOALKEEPER)
									.collect(Collectors.toList());
		
		List<Player> defenders = team.getPlayers().stream()
									.filter(player -> player.getPlayerType() == PlayerType.DEFENDER)
									.collect(Collectors.toList());
		
		List<Player> midfielders = team.getPlayers().stream()
									.filter(player -> player.getPlayerType() == PlayerType.MIDFIELDER)
									.collect(Collectors.toList());
		
		List<Player> attackers =  team.getPlayers().stream()
									.filter(player -> player.getPlayerType() == PlayerType.ATTACKER)
									.collect(Collectors.toList());
		
		boolean eachPlayerCostsMilion = team.getPlayers().stream()
											.map(Player::getValue)
											.allMatch(value -> BigDecimal.valueOf(1_000_000).compareTo(value) == 0);
		
		assertEquals(20, team.getPlayers().size());
		assertEquals(3, goalkeepers.size());
		assertEquals(6, defenders.size());
		assertEquals(6, midfielders.size());
		assertEquals(5, attackers.size());
		assertTrue(eachPlayerCostsMilion);
	}
}
