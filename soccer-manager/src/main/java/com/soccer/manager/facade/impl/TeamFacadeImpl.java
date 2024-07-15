package com.soccer.manager.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.soccer.manager.domain.dto.request.TeamUpdateDTO;
import com.soccer.manager.domain.dto.response.TeamResponseDTO;
import com.soccer.manager.domain.mapper.TeamMapper;
import com.soccer.manager.facade.TeamFacade;
import com.soccer.manager.service.AuthorizationService;
import com.soccer.manager.service.TeamService;

@Component
@Transactional
public class TeamFacadeImpl implements TeamFacade {

	private AuthorizationService authorizationService;
	private TeamService teamService;
	private TeamMapper teamMapper;
	
	@Autowired
	public TeamFacadeImpl(AuthorizationService authorizationService, TeamService teamService, TeamMapper teamMapper) {
		this.authorizationService = authorizationService;
		this.teamService = teamService;
		this.teamMapper = teamMapper;
	}

	@Override
	public TeamResponseDTO updateTeamName(String jwtToken, TeamUpdateDTO teamUpdateDto) {
		authorizationService.checkIfRequestIsAllowed(jwtToken, teamUpdateDto);
		return teamMapper.mapToTeamResponseDto(teamService.updateTeamName(teamUpdateDto));
	}	
}
