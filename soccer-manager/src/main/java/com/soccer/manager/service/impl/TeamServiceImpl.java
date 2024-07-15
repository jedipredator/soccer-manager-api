package com.soccer.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.manager.domain.dto.request.TeamUpdateDTO;
import com.soccer.manager.domain.repo.TeamRepository;
import com.soccer.manager.domain.repo.entity.Team;
import com.soccer.manager.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	private TeamRepository teamRepository;
	
	@Autowired
	public TeamServiceImpl(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}
	
	@Override
	public Team updateTeamName(TeamUpdateDTO teamUpdateDto) {
		var team = teamRepository.getById(teamUpdateDto.getId());
		team.setName(teamUpdateDto.getName());
		team.setCountry(teamUpdateDto.getCountry());
		return teamRepository.save(team);
	}

	@Override
	public Team save(Team team) {
		return teamRepository.save(team);
	}

}
