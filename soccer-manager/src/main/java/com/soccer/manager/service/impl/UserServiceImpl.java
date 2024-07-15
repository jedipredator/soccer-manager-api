package com.soccer.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.manager.domain.repo.UserRepository;
import com.soccer.manager.domain.repo.entity.User;
import com.soccer.manager.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User getUser(String userId) {
		var userOptional = userRepository.findById(userId);
		
		if (userOptional.isEmpty()) {
			throw new RuntimeException("User not found, user id: " + userId);
		}
		
		return userOptional.get();
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
}
