package com.soccer.manager.security.jwt;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.manager.domain.dto.request.UserCreationRequestDTO;
import com.soccer.manager.domain.dto.response.UserCreationResponseDTO;
import com.soccer.manager.facade.UserCreationFacade;

@CrossOrigin
@RestController
public class JwtAuthenticationController {

	private JwtService jwtService;
	private UserCreationFacade userCreationFacade;
	
	@Autowired
	public JwtAuthenticationController(JwtService jwtService, UserCreationFacade userCreationFacade) {
		this.jwtService = jwtService;
		this.userCreationFacade = userCreationFacade;
	}
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		var jwtResponse = jwtService.createAuthenticationToken(authenticationRequest);
		
		if (jwtResponse == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(jwtResponse);
	}

	@PostMapping(value = "/sign-up")
	public ResponseEntity<UserCreationResponseDTO> saveUser(@Valid @RequestBody UserCreationRequestDTO userRequestDto) {
		return new ResponseEntity<>(userCreationFacade.create(userRequestDto), HttpStatus.CREATED);
	}
}
