package com.soccer.manager.facade;

import com.soccer.manager.domain.dto.request.TeamUpdateDTO;
import com.soccer.manager.domain.dto.response.TeamResponseDTO;

public interface TeamFacade {

	TeamResponseDTO updateTeamName(String jwtToken, TeamUpdateDTO teamUpdateDto);
}
