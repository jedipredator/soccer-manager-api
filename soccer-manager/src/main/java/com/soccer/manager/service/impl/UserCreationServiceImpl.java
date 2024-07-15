package com.soccer.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.soccer.manager.domain.dto.request.UserCreationRequestDTO;
import com.soccer.manager.domain.repo.entity.User;
import com.soccer.manager.service.UserCreationService;
import com.soccer.manager.service.UserService;

@Service
public class UserCreationServiceImpl implements UserCreationService {

	private UserService userService;

	@Autowired
	public UserCreationServiceImpl(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public User create(UserCreationRequestDTO user) {
		var newUser = new User();
		newUser.setUsername(user.getUsername());
		var bcryptEncoder = new BCryptPasswordEncoder();
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return newUser;
	}
	
	@Override
	public User save(User user) {
		return userService.save(user);
	}
}
