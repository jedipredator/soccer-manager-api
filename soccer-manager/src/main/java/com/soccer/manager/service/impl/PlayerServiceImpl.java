package com.soccer.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.manager.domain.dto.request.PlayerUpdateDTO;
import com.soccer.manager.domain.repo.PlayerRepository;
import com.soccer.manager.domain.repo.entity.Player;
import com.soccer.manager.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	private PlayerRepository playerRepository;
	
	@Autowired
	public PlayerServiceImpl(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
	
	@Override
	public Player updateNameAndCountry(PlayerUpdateDTO playerUpdateDto) {
		var player = playerRepository.getById(playerUpdateDto.getId());
		player.setFirstName(playerUpdateDto.getFirstName());
		player.setLastName(playerUpdateDto.getLastName());
		player.setCountry(playerUpdateDto.getCountry());
		return player;
	}
}
