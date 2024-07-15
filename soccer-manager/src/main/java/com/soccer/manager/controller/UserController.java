package com.soccer.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.manager.domain.dto.response.UserResponseDTO;
import com.soccer.manager.facade.UserFacade;

@CrossOrigin
@RestController
public class UserController {

	private UserFacade userFacade;
	
	@Autowired
	public UserController(UserFacade userFacade) {
		this.userFacade = userFacade;
	}
	
	@GetMapping(value = "/users/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String userId) {
		return ResponseEntity.ok(userFacade.getUser(userId));
	}
}
