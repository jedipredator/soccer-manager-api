package com.soccer.manager.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.soccer.manager.domain.dto.request.PlayerUpdateDTO;
import com.soccer.manager.domain.dto.response.PlayerResponseDTO;
import com.soccer.manager.domain.mapper.PlayerMapper;
import com.soccer.manager.facade.PlayerFacade;
import com.soccer.manager.service.AuthorizationService;
import com.soccer.manager.service.PlayerService;

@Component
@Transactional
public class PlayerFacadeImpl implements PlayerFacade {

	private AuthorizationService authorizationService;
	private PlayerService playerService;
	private PlayerMapper playerMapper;
	
	@Autowired
	public PlayerFacadeImpl(AuthorizationService authorizationService, PlayerService playerService, PlayerMapper playerMapper) {
		this.authorizationService = authorizationService;
		this.playerService = playerService;
		this.playerMapper = playerMapper;
	}
	
	@Override
	public PlayerResponseDTO updateNameAndCountry(String jwtToken, PlayerUpdateDTO playerUpdateDto) {
		authorizationService.checkIfRequestIsAllowed(jwtToken, playerUpdateDto);
		return playerMapper.mapToPlayerResponseDto(playerService.updateNameAndCountry(playerUpdateDto));
	}

}
