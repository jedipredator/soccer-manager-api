package com.soccer.manager.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.soccer.manager.domain.repo.entity.Player;
import com.soccer.manager.domain.repo.entity.Team;
import com.soccer.manager.service.PlayerGeneratorService;
import com.soccer.manager.service.TeamGeneratorService;

import static com.soccer.manager.domain.repo.entity.PlayerType.GOALKEEPER;
import static com.soccer.manager.domain.repo.entity.PlayerType.DEFENDER;
import static com.soccer.manager.domain.repo.entity.PlayerType.MIDFIELDER;
import static com.soccer.manager.domain.repo.entity.PlayerType.ATTACKER;

@Service
public class TeamGeneratorServiceImpl implements TeamGeneratorService {

	private PlayerGeneratorService playerGeneratorService;

	@Value("${team.goalkeepers.number}")
	private int numberOfGoalkeepers;

	@Value("${team.defenders.number}")
	private int numberOfDefenders;

	@Value("${team.midfielders.number}")
	private int numberOfMidfielders;

	@Value("${team.attackers.number}")
	private int numberOfAttackers;

	@Value("${team.budget.amount}")
	private long budgetAmount;
	
	@Value("${team.budget.currency}")
	private String budgetCurrency;
	
	@Autowired
	public TeamGeneratorServiceImpl(PlayerGeneratorService playerGeneratorService) {
		this.playerGeneratorService = playerGeneratorService;
	}

	@Override
	public Team generateTeam() {
		var team = new Team();

		team.setPlayers(generatePlayers());
		
		setPlayersTeam(team);
		
		team.setBudgetAmount(BigDecimal.valueOf(budgetAmount));
		team.setBudgetCurrency(budgetCurrency);
		
		team.setName("Edit team name");
		team.setCountry("Edit team country");
		
		return team;
	}

	private void setPlayersTeam(Team team) {
		team.getPlayers().forEach(player -> player.setTeam(team));
	}

	private Set<Player> generatePlayers() {
		Set<Player> players = new HashSet<>();

		players.addAll(generateGoalkeepers());
		players.addAll(generateDefenders());
		players.addAll(generateMidfielders());
		players.addAll(generateAttackers());

		return players;
	}

	private Set<Player> generateAttackers() {
		Set<Player> attackers = new HashSet<>();
		
		for (int i = 0; i < numberOfAttackers; i++) {
			attackers.add(playerGeneratorService.generatePlayer(ATTACKER));
		}
		
		return attackers;
	}

	private Set<Player> generateMidfielders() {
		Set<Player> midfielders = new HashSet<>();
		
		for (int i = 0; i < numberOfMidfielders; i++) {
			midfielders.add(playerGeneratorService.generatePlayer(MIDFIELDER));
		}
		
		return midfielders;
	}

	private Set<Player> generateDefenders() {
		Set<Player> defenders = new HashSet<>();
		
		for (int i = 0; i < numberOfDefenders; i++) {
			defenders.add(playerGeneratorService.generatePlayer(DEFENDER));
		}
		
		return defenders;
	}

	private Set<Player> generateGoalkeepers() {
		Set<Player> goalkeepers = new HashSet<>();
		
		for (int i = 0; i < numberOfGoalkeepers; i++) {
			goalkeepers.add(playerGeneratorService.generatePlayer(GOALKEEPER));
		}
		
		return goalkeepers;
	}
}
