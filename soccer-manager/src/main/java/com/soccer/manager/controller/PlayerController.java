package com.soccer.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.manager.domain.dto.request.PlayerUpdateDTO;
import com.soccer.manager.domain.dto.response.PlayerResponseDTO;
import com.soccer.manager.facade.PlayerFacade;

@CrossOrigin
@RestController
public class PlayerController {

	private PlayerFacade playerFacade;
	
	@Autowired
	public PlayerController(PlayerFacade playerFacade) {
		this.playerFacade = playerFacade;
	}
	
	@PatchMapping(value = "/players")
    public ResponseEntity<PlayerResponseDTO> updateNameAndCountry(@RequestHeader("Authorization") String jwtToken,
    																@RequestBody PlayerUpdateDTO playerUpdateDto) {
		return ResponseEntity.ok(playerFacade.updateNameAndCountry(jwtToken, playerUpdateDto));
	}
}
