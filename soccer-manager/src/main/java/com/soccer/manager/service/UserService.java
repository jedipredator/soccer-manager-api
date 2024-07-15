package com.soccer.manager.service;

import com.soccer.manager.domain.repo.entity.User;

public interface UserService {

	User getUser(String userId);
	
	User getUserByUsername(String username);

	User save(User user);
}
