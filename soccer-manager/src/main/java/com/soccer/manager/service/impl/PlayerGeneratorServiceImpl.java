package com.soccer.manager.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.soccer.manager.domain.repo.entity.Player;
import com.soccer.manager.domain.repo.entity.PlayerType;
import com.soccer.manager.service.PlayerGeneratorService;
import com.soccer.manager.service.RandomGeneratorService;

@Service
public class PlayerGeneratorServiceImpl implements PlayerGeneratorService {

	private RandomGeneratorService randomGeneratorService;

	@Value("${player.initialValue}")
	private long initialValue;
	
	@Autowired
	public PlayerGeneratorServiceImpl(RandomGeneratorService randomGeneratorService) {
		this.randomGeneratorService = randomGeneratorService;
	}
	
	@Override
	public Player generatePlayer(PlayerType playerType) {
		var player = new Player();
		
		player.setFirstName("Edit player's first name");
		player.setLastName("Edit player's last name");
		player.setCountry("Edit player's country");
		player.setAge(randomGeneratorService.generatePlayerAge());
		player.setValue(BigDecimal.valueOf(initialValue));
		player.setPlayerType(playerType);	
		
		return player;
	}
}
