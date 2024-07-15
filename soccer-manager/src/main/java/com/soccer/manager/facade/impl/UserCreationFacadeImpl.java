package com.soccer.manager.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.soccer.manager.domain.dto.request.UserCreationRequestDTO;
import com.soccer.manager.domain.dto.response.UserCreationResponseDTO;
import com.soccer.manager.domain.mapper.UserCreationMapper;
import com.soccer.manager.error.UserAlreadyExistException;
import com.soccer.manager.facade.UserCreationFacade;
import com.soccer.manager.service.TeamGeneratorService;
import com.soccer.manager.service.UserCreationService;
import com.soccer.manager.service.UserService;

@Component
@Transactional
public class UserCreationFacadeImpl implements UserCreationFacade {
	
	private UserCreationService userCreationService;
	private UserService userService;
	private TeamGeneratorService teamGeneratorService;
	private UserCreationMapper userMapper;
	
	@Autowired
	public UserCreationFacadeImpl(UserCreationService userCreationService,
									UserService userService,
									TeamGeneratorService teamGeneratorService,
									UserCreationMapper userMapper) {
		this.userCreationService = userCreationService;
		this.userService = userService;
		this.teamGeneratorService = teamGeneratorService;
		this.userMapper = userMapper;
	}

	@Override
	public UserCreationResponseDTO create(UserCreationRequestDTO userRequestDto) {
		if (userService.getUserByUsername(userRequestDto.getUsername()) != null) {
			throw new UserAlreadyExistException("User with username " + userRequestDto.getUsername() + " already exists");
		}
		
		var newUser = userCreationService.create(userRequestDto);
		
		newUser.setTeam(teamGeneratorService.generateTeam());
		
		return userMapper.mapToUserCreationResponseDto(userCreationService.save(newUser));
	}
}
