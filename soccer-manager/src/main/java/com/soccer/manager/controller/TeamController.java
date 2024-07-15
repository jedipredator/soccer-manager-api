package com.soccer.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.manager.domain.dto.request.TeamUpdateDTO;
import com.soccer.manager.domain.dto.response.TeamResponseDTO;
import com.soccer.manager.facade.TeamFacade;

@CrossOrigin
@RestController
public class TeamController {

	private TeamFacade teamFacade;
	
	@Autowired
	public TeamController(TeamFacade teamFacade) {
		this.teamFacade = teamFacade;
	}
	
	@PatchMapping(value = "/teams")
    public ResponseEntity<TeamResponseDTO> updateTeamNameAndCountry(@RequestHeader("Authorization") String jwtToken,
    																@RequestBody TeamUpdateDTO teamUpdateDto) {
		return ResponseEntity.ok(teamFacade.updateTeamName(jwtToken, teamUpdateDto));
	}
}
