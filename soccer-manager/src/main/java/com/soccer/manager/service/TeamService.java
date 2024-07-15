package com.soccer.manager.service;

import com.soccer.manager.domain.dto.request.TeamUpdateDTO;
import com.soccer.manager.domain.repo.entity.Team;

public interface TeamService {

	Team updateTeamName(TeamUpdateDTO teamUpdateDto);
	
	Team save(Team team);
}
