package com.soccer.manager.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.soccer.manager.domain.dto.request.PlayerUpdateDTO;
import com.soccer.manager.domain.dto.request.TeamUpdateDTO;
import com.soccer.manager.domain.dto.request.TransferListBuyRequestDTO;
import com.soccer.manager.domain.dto.request.TransferListRequestDTO;
import com.soccer.manager.domain.repo.entity.Player;
import com.soccer.manager.domain.repo.entity.User;
import com.soccer.manager.security.jwt.JwtTokenUtil;
import com.soccer.manager.service.AuthorizationService;
import com.soccer.manager.service.UserService;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	private UserService userService;
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	public AuthorizationServiceImpl(UserService userService, JwtTokenUtil jwtTokenUtil) {
		this.userService = userService;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@Override
	public void checkIfRequestIsAllowed(String jwtToken, TeamUpdateDTO teamUpdateDto) {
		var username = jwtTokenUtil.getUsernameFromToken(jwtToken.substring(7));
		var user = userService.getUserByUsername(username);
		if (!user.getTeam().getId().equals(teamUpdateDto.getId())) {
			throw new AccessDeniedException("Username " + username 
											+ " tried to update team with id " + teamUpdateDto.getId()); 
		}
	}

	@Override
	public void checkIfRequestIsAllowed(String jwtToken, PlayerUpdateDTO playerUpdateDto) throws AccessDeniedException {
		var username = jwtTokenUtil.getUsernameFromToken(jwtToken.substring(7));
		var user = userService.getUserByUsername(username);
		
		var playersIds = user.getTeam().getPlayers().stream()
							.map(Player::getId)
							.collect(Collectors.toSet());
				
		if (!playersIds.contains(playerUpdateDto.getId())) {
			throw new AccessDeniedException("Username " + username 
					+ " tried to update player with id " + playerUpdateDto.getId()); 
		}
	}

	@Override
	public void checkIfRequestIsAllowed(String jwtToken, TransferListRequestDTO transferListRequestDTO) throws AccessDeniedException {
		var username = jwtTokenUtil.getUsernameFromToken(jwtToken.substring(7));
		var user = userService.getUserByUsername(username);
		
		var playersIds = user.getTeam().getPlayers().stream()
							.map(Player::getId)
							.collect(Collectors.toSet());
				
		if (!playersIds.contains(transferListRequestDTO.getPlayerId())) {
			throw new AccessDeniedException("Username " + username 
					+ " tried to update player with id " + transferListRequestDTO.getPlayerId()); 
		}	
	}

	@Override
	public User checkIfRequestIsAllowed(String jwtToken, TransferListBuyRequestDTO transferListBuyRequestDto) {
		var username = jwtTokenUtil.getUsernameFromToken(jwtToken.substring(7));
		var user = userService.getUserByUsername(username);
		
		var playersIds = user.getTeam().getPlayers().stream()
							.map(Player::getId)
							.collect(Collectors.toSet());
	
		// This time we throw exception if user has tried to buy its own player
		if (playersIds.contains(transferListBuyRequestDto.getPlayerId())) {
			throw new RuntimeException("Username " + username 
					+ " tried to buy its player with id " + transferListBuyRequestDto.getId());
		}
		
		return user;
	}
}
