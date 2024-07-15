package com.soccer.manager.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.soccer.manager.domain.dto.response.UserResponseDTO;
import com.soccer.manager.domain.mapper.UserMapper;
import com.soccer.manager.facade.UserFacade;
import com.soccer.manager.service.UserService;

@Component
@Transactional
public class UserFacadeImpl implements UserFacade {

	private UserService userService;
	private UserMapper userMapper;
	
	@Autowired
	public UserFacadeImpl(UserService userService, UserMapper userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
	}
	
	@Override
	public UserResponseDTO getUser(String userId) {
		return userMapper.mapToUserResponseDto(userService.getUser(userId));
	}
}
